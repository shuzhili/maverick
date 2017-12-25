package com.maverick.theme.view;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import cntv.themelibrary.ThemeHelper;
import cntv.themelibrary.Themeable;

/**
 * Created by limingfei on 2017/12/25.
 */

public class ThemeableSettingsCaption extends AppCompatTextView implements Themeable {
    public ThemeableSettingsCaption(Context context) {
        this(context, null);
    }

    public ThemeableSettingsCaption(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ThemeableSettingsCaption(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void refreshTheme(ThemeHelper themeHelper) {
        setTextColor(themeHelper.getSubTextColor());
    }
}
