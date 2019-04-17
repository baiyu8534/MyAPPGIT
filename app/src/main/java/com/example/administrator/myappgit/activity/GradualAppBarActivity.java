package com.example.administrator.myappgit.activity;

import android.animation.ArgbEvaluator;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.administrator.myappgit.R;
import com.example.administrator.myappgit.adapter.ShowListFragmentPagerAdapter;
import com.example.administrator.myappgit.fragment.WeatherFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.administrator.myappgit.utils.UIUtil.colorResId2ColorInt;

/**
 * appbar颜色渐变
 */
public class GradualAppBarActivity extends BaseActivity {

    @BindView(R.id.tl)
    TabLayout mTl;
    @BindView(R.id.rl_bar)
    RelativeLayout mRlBar;
    @BindView(R.id.vp)
    ViewPager mVp;

    private int[] itemBG = {
            R.color.mainRvItemBg1,
            R.color.colorAccent,
            R.color.mainRvItemBg2,
            R.color.mainRvItemBg3
    };


    private List<Fragment> mFragments = new ArrayList<>();
    private ShowListFragmentPagerAdapter mPagerAdapter;
    private int status_bar_height;
    private ArgbEvaluator mArgbEvaluator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_gradual_app_bar);
        ButterKnife.bind(this);
        initView();
        initListener();
    }

    int prePosition = 0;
    private void initListener() {
        mTl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                System.out.println("onTabSelected::::::::::::::::::::::::::::");
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                System.out.println("onPageScrolled::::positionOffset:::position:::" + positionOffset + ":::" + position);


                if (position + positionOffset > 0 && position + positionOffset < 1 && position + positionOffset -
                        prePosition > 0) {

//                    mRlBar.setBackgroundColor(colorResId2ColorInt(GradualAppBarActivity.this, itemBG[position])); //先设置第0页时还没有滑动时tablayout的颜色
                    int evaluate = (Integer) mArgbEvaluator.evaluate(positionOffset, colorResId2ColorInt
                            (GradualAppBarActivity.this, itemBG[position]), colorResId2ColorInt(GradualAppBarActivity.this,
                            itemBG[(position + 1)]));
                    mRlBar.setBackgroundColor(evaluate);//设置背景颜色为算出的两种颜色之间的过渡色


                } else if (position + positionOffset > 1 && position + positionOffset < 2 && position +
                        positionOffset - prePosition > 0) {

//                    mRlBar.setBackgroundColor(colorResId2ColorInt(GradualAppBarActivity.this, itemBG[position]));
                    int evaluate = (Integer) mArgbEvaluator.evaluate(positionOffset, colorResId2ColorInt
                            (GradualAppBarActivity.this, itemBG[position]), colorResId2ColorInt(GradualAppBarActivity.this,
                            itemBG[(position + 1)]));

                    mRlBar.setBackgroundColor(evaluate);//设置背景颜色为算出的两种颜色之间的过渡色


                } else if (position + positionOffset > 2 && position + positionOffset < 3 && position +
                        positionOffset - prePosition > 0) {

//                    mRlBar.setBackgroundColor(colorResId2ColorInt(GradualAppBarActivity.this, itemBG[position]));
                    int evaluate = (Integer) mArgbEvaluator.evaluate(positionOffset, colorResId2ColorInt
                            (GradualAppBarActivity.this, itemBG[position]), colorResId2ColorInt(GradualAppBarActivity.this,
                            itemBG[(position + 1)]));
                    mRlBar.setBackgroundColor(evaluate);//设置背景颜色为算出的两种颜色之间的过渡色


                }
//                else if (position + positionOffset > 2 && position + positionOffset < 3 && position +
//                        positionOffset - prePosition < 0) {
//                } else if (position + positionOffset > 1 && position + positionOffset < 2 && position +
//                        positionOffset - prePosition < 0) {
//                } else if (position + positionOffset > 0 && position + positionOffset < 1 && position +
//                        positionOffset - prePosition < 0) {
//                }

            }

            @Override
            public void onPageSelected(int position) {
                System.out.println("onPageSelected:::position:::" + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                System.out.println("onPageScrollStateChanged");
            }
        });
    }

    private void initView() {

        mArgbEvaluator = new ArgbEvaluator();

        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            status_bar_height = getResources().getDimensionPixelSize(resourceId);
        }
        mRlBar.setPadding(0, status_bar_height, 0, 0);


        mFragments.add(new WeatherFragment());
        mFragments.add(new WeatherFragment());
        mFragments.add(new WeatherFragment());
        mFragments.add(new WeatherFragment());

        mPagerAdapter = new ShowListFragmentPagerAdapter(getSupportFragmentManager(), this, mFragments);
        mVp.setAdapter(mPagerAdapter);
        mVp.setOffscreenPageLimit(3);

        mTl.setTabMode(TabLayout.MODE_FIXED);
        mTl.setTabGravity(TabLayout.GRAVITY_CENTER);
        mTl.setTabTextColors(colorResId2ColorInt(GradualAppBarActivity.this, R.color.color_gray_1),
                colorResId2ColorInt(GradualAppBarActivity.this, R.color.colorWhite));
        mTl.setSelectedTabIndicatorColor(colorResId2ColorInt(GradualAppBarActivity.this, R.color.colorWhite));
        mTl.setupWithViewPager(mVp);

        for (int i = 0; i < mTl.getTabCount(); i++) {
            mTl.getTabAt(i).setTag(itemBG[1]);
        }
    }
}
