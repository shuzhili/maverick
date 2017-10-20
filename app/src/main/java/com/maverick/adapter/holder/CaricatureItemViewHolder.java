package com.maverick.adapter.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.maverick.R;
import com.maverick.WebActivity;
import com.maverick.bean.CaricatureInfo;
import com.maverick.hepler.BeanHelper;

/**
 * Created by limingfei on 2017/10/20.
 */
public class CaricatureItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final TextView title;
    private CaricatureInfo mInfo;
    private Context mContext;

    public CaricatureItemViewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.title);
        title.setOnClickListener(this);
    }

    public void bindData(Context context, CaricatureInfo info) {
        this.mInfo = info;
        this.mContext = context;
        if (!TextUtils.isEmpty(info.getTitle())) {
            title.setText(info.getTitle());
        } else {
            title.setText("");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title:
                if (mInfo == null) {
                    return;
                }

                WebActivity.launch(mContext, BeanHelper.getWebDetailInfo(mInfo));
                break;
        }
    }
}
