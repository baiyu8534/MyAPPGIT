package com.example.administrator.myappgit.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.administrator.myappgit.R;
import com.example.administrator.myappgit.utils.ScreenUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestActivity3 extends BaseActivity {

    @BindView(R.id.ll_content)
    LinearLayout mLlContent;
    @BindView(R.id.hsv)
    HorizontalScrollView mHsv;
    @BindView(R.id.iv_bg)
    ImageView mIvBg;
    @BindView(R.id.vp)
    ViewPager mVp;

    private int count = 4;
    private String preId = "tab_cv_";


    private List<RelativeLayout> itemViews = new ArrayList<>();
    private int mScreenWidth;
    private int mPosition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3);
        ButterKnife.bind(this);
        mPosition = getIntent().getIntExtra("position", 0);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        final FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) mLlContent.getLayoutParams();

        layoutParams.setMargins(-mScreenWidth * mPosition, layoutParams.topMargin, layoutParams.rightMargin, layoutParams.bottomMargin);


        mLlContent.setLayoutParams(layoutParams);

        mHsv.postDelayed(new Runnable() {
            @Override
            public void run() {

                layoutParams.setMargins(0, 0, 0, 0);
                mLlContent.setLayoutParams(layoutParams);

                mHsv.scrollBy(mScreenWidth * mPosition, 0);

            }
        }, 10);
    }

    private void initView() {
        mScreenWidth = ScreenUtil.getScreenWidth(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            for (int i = 0; i < count; i++) {

                RelativeLayout item = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.item_transform_indicator, mLlContent, false);

                ViewGroup.LayoutParams rl = item.getLayoutParams();
                rl.width = mScreenWidth;
                item.setLayoutParams(rl);

                CardView cv_item = item.findViewById(R.id.cv_item);

                cv_item.setTransitionName(preId + i);

                mLlContent.addView(item);
            }
        }
    }
}
