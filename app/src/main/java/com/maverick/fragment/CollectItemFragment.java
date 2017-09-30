package com.maverick.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.maverick.R;
import com.maverick.base.BaseFragment2;
import com.maverick.bean.CollectTabInfo;
import com.maverick.global.Tag;
import com.maverick.presenter.BasePresenter;

import cntv.greendaolibrary.dbbean.Collect;

/**
 * Created by limingfei on 2017/9/29.
 */
public class CollectItemFragment extends BaseFragment2 {

    public static final int STATE_EDIT = 1;
    public static final int STATE_NO_EDIT = 2;
    public int stateEdit = STATE_NO_EDIT;

    public static final int STATE_CHECK = 1;
    public static final int STATE_NO_ALL_CHECK = 2;
    public static final int STATE_ALL_CHECK = 3;
    public int checkState = STATE_NO_ALL_CHECK;

    private TextView title;

    public static CollectItemFragment newInstance(CollectTabInfo collectTabInfo) {
        CollectItemFragment fragment = new CollectItemFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable(Tag.KEY_INFO, collectTabInfo);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }

    @Override
    protected int getRootViewId() {
        return R.layout.fragment_collect_item;
    }

    @Override
    protected void onInitView(View view) {
        title = findView(R.id.title);
    }

    @Override
    protected void onInitData(Bundle savedInstanceState) {
        CollectTabInfo collectTabInfo = (CollectTabInfo) getArguments().getSerializable(Tag.KEY_INFO);
        if (!TextUtils.isEmpty(collectTabInfo.getTitle())) {
            title.setText(collectTabInfo.getTitle());
        }
    }

    public int getStateEdit() {
        return stateEdit;
    }

    public void setStateEdit(int stateEdit) {
        this.stateEdit = stateEdit;
        switch (stateEdit) {
            case STATE_EDIT:
                openEditState();
                break;
            case STATE_NO_EDIT:
                closeEditState();
                break;
        }
    }

    protected void closeEditState() {

    }

    protected void openEditState() {

    }

    public void delete() {

    }

    public int getCheckState() {
        return checkState;
    }

    public void setCheckState(int checkState) {
        this.checkState = checkState;
        switch (checkState) {
            case STATE_ALL_CHECK:
                selectorAll();
                break;
            case STATE_NO_ALL_CHECK:
                cancelAll();
                break;
        }

        if(mOnCollectItemFragmentListener != null) {
            mOnCollectItemFragmentListener.onCheckState(checkState);
        }
    }

    public void selectorAll() {
    }

    public void cancelAll() {
    }

    protected OnCollectItemFragmentListener mOnCollectItemFragmentListener;

    public void setOnCollectItemFragmentListener(OnCollectItemFragmentListener listener) {
        this.mOnCollectItemFragmentListener = listener;
    }

    public interface OnCollectItemFragmentListener {
        void onCheckState(int checkState);
    }
}