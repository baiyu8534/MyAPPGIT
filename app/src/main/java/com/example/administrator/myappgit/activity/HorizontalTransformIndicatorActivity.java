package com.example.administrator.myappgit.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.example.administrator.myappgit.R;
import com.example.administrator.myappgit.adapter.ShowListFragmentPagerAdapter;
import com.example.administrator.myappgit.fragment.WeatherFragment;
import com.example.administrator.myappgit.ui.HorizontalTransformIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HorizontalTransformIndicatorActivity extends BaseActivity {
    @BindView(R.id.iv_bg)
    ImageView mIvBg;
    @BindView(R.id.hti_tab)
    HorizontalTransformIndicator mHtiTab;
    @BindView(R.id.vp)
    ViewPager mVp;

    private int selectPosition;

    private List<Fragment> mFragments = new ArrayList<>();
    private ShowListFragmentPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_transform_indicator);
        ButterKnife.bind(this);
        selectPosition = getIntent().getIntExtra("position", 0);
        initView();
    }

    private void initView() {
        mFragments.add(new WeatherFragment());
        mFragments.add(new WeatherFragment());
        mFragments.add(new WeatherFragment());
        mFragments.add(new WeatherFragment());
//        mFragments.add(new ZhiHuFragment());

        mPagerAdapter = new ShowListFragmentPagerAdapter(getSupportFragmentManager(), this, mFragments);
        mVp.setAdapter(mPagerAdapter);
        mVp.setCurrentItem(selectPosition);
        mVp.setOffscreenPageLimit(3);

        mHtiTab.initialization(mVp, "tab_cv_", R.layout.item_transform_indicator, selectPosition);
        mHtiTab.setOnHItemClickListener(new HorizontalTransformIndicator.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    finishAfterTransition();
                } else {
                    finish();
                }
            }
        });
    }
}
