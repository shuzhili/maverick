package com.maverick.theme.view;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import cntv.themelibrary.ThemeHelper;
import cntv.themelibrary.Themeable;

/**
 * Created by Administrator on 2017/12/25.
 */

public class ThemeableMyTitle extends AppCompatTextView implements Themeable {

    public ThemeableMyTitle(Context context) {
        this(context, null);
    }

    public ThemeableMyTitle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ThemeableMyTitle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void refreshTheme(ThemeHelper themeHelper) {
        setTextColor(themeHelper.getTextColor());
    }
}