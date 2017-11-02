package com.maverick.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.maverick.R;
import com.maverick.adapter.PearBottomFragmentAdapter;
import com.maverick.base.BaseFragment2;
import com.maverick.bean.PearItemInfo;
import com.maverick.bean.PearVideoDetailBean;
import com.maverick.bean.PearVideoDetailInfoVideo;
import com.maverick.presenter.BasePresenter;
import com.maverick.presenter.PearBottomFragmentPresenter;
import com.maverick.presenter.implView.IPearBottomFragmentView;
import com.maverick.type.PearItemType;

import java.util.List;

/**
 * Created by Administrator on 2017/10/31.
 */

public class PearBottomFragment extends BaseFragment2 implements IPearBottomFragmentView {

    public static final String EXTRA_IMAGE = "EXTRA_IMAGE";

    private static final int spanCount = 2;

    private PearBottomFragmentPresenter mPresenter;
    private PearBottomFragmentAdapter mAdapter;

    public static PearBottomFragment newInstance(PearVideoDetailBean info) {
        PearBottomFragment fragment = new PearBottomFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_IMAGE, info);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        mPresenter = new PearBottomFragmentPresenter(getContext(), this);
        return mPresenter;
    }

    @Override
    protected int getRootViewId() {
        return R.layout.fragment_pear_bottom;
    }

    @Override
    protected void onInitView(View view) {
        RecyclerView mRecyclerView = findView(R.id.recyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), spanCount);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new PearBottomFragmentAdapter(getContext());
        mRecyclerView.setAdapter(mAdapter);

        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {

            @Override
            public int getSpanSize(int position) {
                if (PearItemType.TITLE == mAdapter.getItemViewType(position) || PearItemType.DETAIL == mAdapter.getItemViewType(position)) {
                    return spanCount;
                }
                return 1;
            }
        });

        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int position = parent.getChildAdapterPosition(view);

//                if (mAdapter != null && mAdapter.isHeader()) {
//                    if (position > 0) {
//                        position--;
//                    } else {
//                        return;
//                    }
//                }

                outRect.bottom = getResources().getDimensionPixelSize(R.dimen.x2);
                outRect.top = getResources().getDimensionPixelSize(R.dimen.y9);
                if (position % 2 == 0) {
                    outRect.right = getResources().getDimensionPixelSize(R.dimen.y1);
                } else {
                    outRect.left = getResources().getDimensionPixelSize(R.dimen.y1);
                }
            }
        });
    }

    @Override
    protected void onInitData(Bundle savedInstanceState) {
        mPresenter.loadData((PearVideoDetailBean) getArguments().getSerializable(EXTRA_IMAGE));
    }

    @Override
    public void onShowSuccessView(List<PearItemInfo> list) {
        mAdapter.setData(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onShowEmptyView() {

    }

    @Override
    public void onShowErrorView() {

    }

    @Override
    public void onShowVideoView(List<PearVideoDetailInfoVideo> list) {
        if (getActivity() != null && getActivity() instanceof OnListener) {
            OnListener listener = (OnListener) getActivity();
            listener.playVideo(list);
        }
    }

    public interface OnListener {
        void playVideo(List<PearVideoDetailInfoVideo> list);
    }
}