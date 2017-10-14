package com.maverick.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.text.TextUtils;
import android.util.Log;

import com.maverick.bean.SisterDetailInfo;
import com.maverick.bean.SisterInfo;
import com.maverick.global.Tag;
import com.maverick.imodel.ISisterFragmentModel;
import com.maverick.model.SisterFragmentModel;
import com.maverick.presenter.implView.ISisterFragmentView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/9/30.
 */
public class SisterFragmentPresenter extends BasePresenter {

    private String TAG = getClass().getSimpleName();

    private Context mContext;
    private ISisterFragmentView mView;
    private final ISisterFragmentModel mModel;
    private int mPage = 1;
    private SisterDetailInfo mSisterDetailInfo;

    public SisterFragmentPresenter(Context context, ISisterFragmentView view) {
        this.mContext = context;
        this.mView = view;
        this.mModel = new SisterFragmentModel();
    }

    @Override
    public void release() {
        if (mModel != null) {
            mModel.release();
        }
    }

    public void refreshData(SisterDetailInfo sisterDetailInfo) {
        mSisterDetailInfo = sisterDetailInfo;

        if (sisterDetailInfo == null) {
            mView.onShowErrorView();
            return;
        }

        mModel.requestData(sisterDetailInfo.getType(), sisterDetailInfo.getTitle(), 1, new ISisterFragmentModel.OnResultListener() {

            @Override
            public void onSuccess(List<SisterInfo> list) {
                if (list != null && list.size() >= 1) {
//                    for (int i = 0; i < list.size(); i++) {
//                        Log.e(TAG, "" + list.get(i).getImage2());
//                        SisterInfo sisterInfo = list.get(i);
////                        if (TextUtils.equals(sisterInfo.getType(), Tag.SISTER_VIDEO)) {
////                            sisterInfo.setVideo_image(getNetVideoBitmap(sisterInfo.getVideo_uri()));
////                        }
//                    }
                    mView.onShowSuccessView(list);
                } else {
                    mView.onShowEmptyView();
                }
                mPage = 1;
            }

            @Override
            public void onFail() {
                mView.onShowErrorView();
            }
        });
    }

    /**
     * 服务器返回url，通过url去获取视频的第一帧
     * Android 原生给我们提供了一个MediaMetadataRetriever类
     * 提供了获取url视频第一帧的方法,返回Bitmap对象
     *
     * @param videoUrl
     * @return
     */
    public static Bitmap getNetVideoBitmap(String videoUrl) {
        Bitmap bitmap = null;

        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            //根据url获取缩略图
            retriever.setDataSource(videoUrl, new HashMap());
            //获得第一帧图片
            bitmap = retriever.getFrameAtTime();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            retriever.release();
        }
        return bitmap;
    }

    public void loadMoreData() {

        if (mSisterDetailInfo == null) {
            mView.onLoadMoreFail();
            return;
        }

        mModel.requestData(mSisterDetailInfo.getType(), mSisterDetailInfo.getTitle(), mPage + 1, new ISisterFragmentModel.OnResultListener() {

            @Override
            public void onSuccess(List<SisterInfo> list) {
                //此接口固定20条数据
                mView.onLoadMoreSuccess(list, list.size() >= 20);
                mPage++;
            }

            @Override
            public void onFail() {
                mView.onLoadMoreFail();
            }
        });
    }

    public int getRandom(String ding) {
        int dingCount = getString2Int(ding);
        return (int) (Math.random() * dingCount);
    }

    public int getString2Int(String str) {

        try {
            return Integer.parseInt(str);
        } catch (Exception e) {

        }
        return 0;
    }
}
