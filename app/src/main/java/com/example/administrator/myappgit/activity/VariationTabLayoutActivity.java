package com.example.administrator.myappgit.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.administrator.myappgit.R;
import com.example.administrator.myappgit.adapter.ShowListFragmentPagerAdapter;
import com.example.administrator.myappgit.fragment.WeatherFragment;
import com.example.administrator.myappgit.ui.VariationTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.administrator.myappgit.utils.UIUtil.colorResId2ColorInt;

/**
 * 文件名：VariationTabLayoutActivity
 * 描述：演示VariationTabLayout
 * 作者：白煜
 * 时间：2017/10/20 0020
 * 版权：
 */

public class VariationTabLayoutActivity extends BaseActivity {

    @BindView(R.id.vtl)
    VariationTabLayout mVtl;
    @BindView(R.id.vp)
    ViewPager mVp;

    private List<Fragment> mFragments;
    private ShowListFragmentPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_variation_tablayout_layout);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mFragments = new ArrayList<>();
        mFragments.add(new WeatherFragment());
        mFragments.add(new WeatherFragment());
        mFragments.add(new WeatherFragment());
        mFragments.add(new WeatherFragment());
//        mFragments.add(new ZhiHuFragment());

        mPagerAdapter = new ShowListFragmentPagerAdapter(getSupportFragmentManager(), this, mFragments);
        mVp.setAdapter(mPagerAdapter);
        mVp.setOffscreenPageLimit(3);

        //        下面就来解释一下TabGravity和TabMode，
//        TabGravity:放置Tab的Gravity,有GRAVITY_CENTER 和 GRAVITY_FILL两种效果。
//        顾名思义，一个是居中，另一个是尽可能的填充（注意，GRAVITY_FILL需要和MODE_FIXED一起使用才有效果）
//        TabMode:布局中Tab的行为模式（behavior mode），有两种值：MODE_FIXED 和 MODE_SCROLLABLE。
//        MODE_FIXED:固定tabs，并同时显示所有的tabs。
//        MODE_SCROLLABLE：可滚动tabs，显示一部分tabs，在这个模式下能包含长标签和大量的tabs，最好用于用户不需要直接比较tabs。
        mVtl.setTabMode(TabLayout.MODE_FIXED);
        mVtl.setTabGravity(TabLayout.GRAVITY_CENTER);


        //设置字体颜色，int不是resID
//        mTabShowList.setTabTextColors(
//                colorResId2ColorInt(R.color.colorPrimaryDark),
//                colorResId2ColorInt(R.color.colorPrimary));
        mVtl.setTabTextColors(colorResId2ColorInt(mContext, R.color.colorWhite),
                colorResId2ColorInt(mContext, R.color.colorWhite));
        mVtl.setSelectedTabIndicatorColor(colorResId2ColorInt(mContext, R.color.colorPrimaryDark));
//        mTabShowList.setBackgroundColor(colorResId2ColorInt(ShowListActivity.this, itemBG[mPosition % 3]));
        mVtl.setupWithViewPager(mVp);
    }
}
