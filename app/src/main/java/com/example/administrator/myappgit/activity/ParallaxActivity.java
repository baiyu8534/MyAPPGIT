package com.example.administrator.myappgit.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myappgit.R;
import com.example.administrator.myappgit.utils.UIUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 文件名：ParallaxActivity
 * 描述：视差滚动
 * 作者：白煜
 * 时间：2017/9/25 0025
 * 版权：
 */

public class ParallaxActivity extends BaseActivity {
    @BindView(R.id.iv_view_pager_bg)
    ImageView mIvViewPagerBg;
    @BindView(R.id.vp_parallax)
    ViewPager mVpParallax;

    private float prePosition = 0;
    private float prepositionOffset = 0;

    int scrollByX = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parallax_layout);
        ButterKnife.bind(this);
        initView();
        initViewListener();
    }

    private void initView() {
        mVpParallax.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                TextView view = (TextView) LayoutInflater.from(mContext).inflate(R.layout.temp_textview_layout, container, false);
                view.setText(position + "");
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(container.getChildAt(0));
            }
        });

//        Glide.with(mContext).load(R.drawable.vp_bg).into(mIvViewPagerBg);
        mIvViewPagerBg.setImageResource(R.drawable.vp_bg);
        mIvViewPagerBg.scrollTo((-1 * 900), 0);
    }

    private void initViewListener() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mVpParallax.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    mIvViewPagerBg.scrollBy((scrollX - oldScrollX) / 2, 0);
                }
            });
        }

//        mVpParallax.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                int temp = 0;
//                if(positionOffset > prepositionOffset) {
//                    temp = (int) (100 * (positionOffset - prepositionOffset));
//                }else{
//                    temp = (int) (100 * (prepositionOffset - positionOffset));
//                }
//
//                if (temp > 0 && temp < 100) {
//                    scrollByX = temp * 3;
//                }
//                prepositionOffset = positionOffset;
//                if (position + positionOffset > 0 && position + positionOffset < 1 && position + positionOffset -
//                        prePosition > 0) {
////                    System.out.println("0->1::::scrollByX::" + scrollByX);
//                    mIvViewPagerBg.scrollBy(scrollByX, 0);
//                    prePosition = position + positionOffset;
//                } else if (position + positionOffset > 1 && position + positionOffset < 2 && position + positionOffset -
//                        prePosition > 0) {
////                    System.out.println("1->2::::scrollByX::" + scrollByX);
//                    mIvViewPagerBg.scrollBy(scrollByX, 0);
//                    prePosition = position + positionOffset;
//                } else if (position + positionOffset > 2 && position + positionOffset < 3 && position + positionOffset -
//                        prePosition > 0) {
////                    System.out.println("2->3::::scrollByX::" + scrollByX);
//                    mIvViewPagerBg.scrollBy(scrollByX, 0);
//                    prePosition = position + positionOffset;
//                } else if (position + positionOffset > 2 && position + positionOffset < 3 && position + positionOffset -
//                        prePosition < 0) {
////                    System.out.println("3->2::::scrollByX::" + scrollByX);
//                    mIvViewPagerBg.scrollBy(-scrollByX, 0);
//
//                    prePosition = position + positionOffset;
//                } else if (position + positionOffset > 1 && position + positionOffset < 2 && position + positionOffset -
//                        prePosition < 0) {
////                    System.out.println("2->1::::scrollByX::" + scrollByX);
//                    mIvViewPagerBg.scrollBy(-scrollByX, 0);
//
//                    prePosition = position + positionOffset;
//                } else if (position + positionOffset > 0 && position + positionOffset < 1 && position + positionOffset -
//                        prePosition < 0) {
////                    System.out.println("1->0::::scrollByX::" + scrollByX);
//                    mIvViewPagerBg.scrollBy(-scrollByX, 0);
//                    prePosition = position + positionOffset;
//                }
//            }
//
//            @Override
//            public void onPageSelected(int i) {
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int i) {
//
//            }
//        });
    }

    @Override
    protected void noNetworkConnFail() {
        UIUtil.snackNewWorkErrorMessage(mIvViewPagerBg, getString(R.string.alert_message_no_network_conn));
    }
}
