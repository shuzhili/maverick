package com.maverick;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.maverick.adapter.MainActivityAdapter;
import com.maverick.base.BaseActivity;
import com.maverick.base.BaseFragment;
import com.maverick.bean.ButtonInfo;
import com.maverick.global.ActivityCode;
import com.maverick.type.FragmentType;
import com.umeng.socialize.UMShareAPI;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SwipeBackLayout;

/**
 * Created by limingfei on 2017/9/25.
 */
public class MainActivity2 extends BaseActivity {

    private TextView mTitle;
    private CoordinatorLayout mCoordinatorLayout;
    private AppBarLayout mAppBarLayout;
    private Toolbar mToolbar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_sister:
                    mViewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_caricature:
                    mViewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_pear:
                    mViewPager.setCurrentItem(2);
                    return true;
                case R.id.navigation_joke:
                    mViewPager.setCurrentItem(3);
                    return true;
                case R.id.navigation_my:
                    mViewPager.setCurrentItem(4);
                    return true;
            }
            return false;
        }
    };
    private List<ButtonInfo> mList;
    private BottomNavigationView mBottomNavigationView;
    private AppBarLayout.Behavior mBehavior;
    private ViewPager mViewPager;
    private MainActivityAdapter mMainActivityAdapter;

    @Override
    protected com.maverick.presenter.BasePresenter onCreatePresenter() {
        return null;
    }

    @Override
    protected int getRootViewId() {
        return R.layout.activity_main2;
    }

    @Override
    protected void onInitView() {
        // 设置滑动方向
        getSwipeBackLayout().setEdgeOrientation(SwipeBackLayout.STATE_IDLE); // EDGE_LEFT(默认),EDGE_ALL

        mCoordinatorLayout = findView(R.id.coordinatorLayout);
        mAppBarLayout = findView(R.id.appbar);

        mToolbar = findView(R.id.toolbar_actionbar);
        mTitle = new TextView(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mTitle.setTextAppearance(android.R.style.TextAppearance_Material_Widget_ActionBar_Title);
        } else {
            mTitle.setTextAppearance(MainActivity2.this, android.R.style.TextAppearance_Material_Widget_ActionBar_Title);
        }
        mTitle.setTextColor(ContextCompat.getColor(MainActivity2.this, R.color.textColorPrimary));
        Toolbar.LayoutParams mTitleLP = new Toolbar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mTitleLP.gravity = Gravity.CENTER;
        mTitle.setVisibility(View.GONE);
        mToolbar.addView(mTitle, mTitleLP);
        setSupportActionBar(mToolbar);

        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(mPermissionList, 123);
            }
        }

        mViewPager = findViewById(R.id.vp_main);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setSupportActionBarTitle(mList.get(position).getName());

                // 将当前的页面对应的底部标签设为选中状态
                mBottomNavigationView.getMenu().getItem(position).setChecked(true);

                CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) mAppBarLayout.getLayoutParams();
                if (mBehavior != null) {
                    layoutParams.setBehavior(mBehavior);
                }

                if (mList.get(position).getType() == FragmentType.MY) {
                    mBehavior = (AppBarLayout.Behavior) layoutParams.getBehavior();
                    if (mBehavior != null) {
                        mBehavior.setTopAndBottomOffset(0);
                    }
                    layoutParams.setBehavior(null);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        mAppBarLayout.setElevation(getResources().getDimension(R.dimen.card_elevation));
                    }
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        mAppBarLayout.setElevation(0);
                    }
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void setSupportActionBarTitle(CharSequence title) {
        if (getSupportActionBar() == null || TextUtils.isEmpty(title)) {
            return;
        }
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("");
        mTitle.setText(title);
        mTitle.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onInitData(Bundle savedInstanceState) {
        mList = new ArrayList<>();
        mList.add(getButtonInfo(getString(R.string.fragment_sister), 0, FragmentType.SISTER));
//        mList.add(getButtonInfo(getString(R.string.fragment_beauty), 0, FragmentType.BEAUTY));
        mList.add(getButtonInfo(getString(R.string.fragment_caricature), 0, FragmentType.CARICATURE));
//        mList.add(getButtonInfo(getString(R.string.fragment_sina), 0, FragmentType.SINA));
        mList.add(getButtonInfo(getString(R.string.fragment_pear), 0, FragmentType.PEAR));
        mList.add(getButtonInfo(getString(R.string.fragment_joke), 0, FragmentType.JOKE));
        mList.add(getButtonInfo(getString(R.string.fragment_my), 0, FragmentType.MY));

        mMainActivityAdapter = new MainActivityAdapter(getSupportFragmentManager());
        mMainActivityAdapter.setList(mList);
        mViewPager.setAdapter(mMainActivityAdapter);

        setSupportActionBarTitle(mList.get(0).getName());
    }

    public ButtonInfo getButtonInfo(String name, int iconId, int type) {
        ButtonInfo buttonInfo = new ButtonInfo();
        buttonInfo.setName(name);
        buttonInfo.setIconId(iconId);
        buttonInfo.setType(type);
        return buttonInfo;
    }

    private long currentTime;

    @Override
    public void onBackPressedSupport() {
        if (getSupportFragmentManager().getPrimaryNavigationFragment() != null && ((BaseFragment) (getSupportFragmentManager().getPrimaryNavigationFragment())).onBackPressed()) {
            return;
        }

        if ((System.currentTimeMillis() - currentTime) > 2000) {
            currentTime = System.currentTimeMillis();
            Toast.makeText(this, R.string.exit_app, Toast.LENGTH_SHORT).show();
            return;
        } else {
            finish();
        }

        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ActivityCode.RESULT_CODE_THEME && requestCode == ActivityCode.REQUEST_CODE_THEME) {
            recreate();
        }
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }
}
