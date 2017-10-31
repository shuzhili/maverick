package com.maverick.presenter;

import android.content.Context;
import android.util.Log;

import com.maverick.bean.PearVideoDetailBean;
import com.maverick.bean.PearVideoDetailInfoData;
import com.maverick.imodel.IPearModel;
import com.maverick.model.PearModel;
import com.maverick.presenter.implView.IPearBottomFragmentView;

/**
 * Created by Administrator on 2017/10/31.
 */

public class PearBottomFragmentPresenter extends BasePresenter {

    private Context mContext;
    private IPearBottomFragmentView mView;
    private final IPearModel mModel;

    public PearBottomFragmentPresenter(Context context, IPearBottomFragmentView view) {
        this.mContext = context;
        this.mView = view;
        this.mModel = new PearModel();
    }

    @Override
    public void release() {
        if (mModel != null) {
            mModel.release();
        }
    }

    public void loadData(PearVideoDetailBean info) {
        if (info == null) {
            mView.onShowErrorView();
            return;
        }
        mModel.requestPearDetail(info.getContId(), new IPearModel.OnPearDetailListener() {
            @Override
            public void onSuccess(PearVideoDetailInfoData info) {
                Log.e("lmf", "onSuccess =" + info);
            }

            @Override
            public void onFail() {
                Log.e("lmf", "onFail =");
            }
        });
    }
}
