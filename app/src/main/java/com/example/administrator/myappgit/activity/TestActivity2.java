package com.example.administrator.myappgit.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.administrator.myappgit.R;
import com.example.administrator.myappgit.adapter.GuidePagerAdapter;
import com.example.administrator.myappgit.utils.ScreenUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestActivity2 extends BaseActivity {
    @BindView(R.id.tl)
    TabLayout mTl;

    @BindView(R.id.fl)
    LinearLayout mFl;
    @BindView(R.id.hsv)
    HorizontalScrollView mHsv;
    @BindView(R.id.rl1)
    RelativeLayout mRl1;
    @BindView(R.id.rl2)
    RelativeLayout mRl2;
    @BindView(R.id.rl3)
    RelativeLayout mRl3;
    @BindView(R.id.rl4)
    RelativeLayout mRl4;


    private List<ImageView> mImageViews = new ArrayList<>();
    private GuidePagerAdapter mGuidePagerAdapter;
    private int mPosition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        ButterKnife.bind(this);
        mPosition = getIntent().getIntExtra("position", 0);
        initData();
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mHsv.postDelayed(new Runnable() {
            @Override
            public void run() {
                switch (mPosition) {
                    case 0:
                        mHsv.scrollTo(0, 0);
                        break;
                    case 1:
//                        mHsv.scrollTo(mRl1.getRight(), 0);
                        mHsv.scrollBy(mRl1.getRight(), 0);
                        break;
                    case 2:
//                        mHsv.scrollTo(mRl2.getRight(), 0);
                        mHsv.scrollBy(mRl2.getRight(), 0);
                        break;
                    case 3:
//                        mHsv.scrollTo(mRl3.getRight(), 0);
                        mHsv.scrollBy(mRl3.getRight(), 0);
                        break;
                }
            }
        },10);
    }

    private void initData() {
        int screenWidth = ScreenUtil.getScreenWidth(this);
        ViewGroup.LayoutParams rl1 = mRl1.getLayoutParams();
        ViewGroup.LayoutParams rl2 = mRl2.getLayoutParams();
        ViewGroup.LayoutParams rl3 = mRl3.getLayoutParams();
        ViewGroup.LayoutParams rl4 = mRl4.getLayoutParams();
        rl1.width = screenWidth;
        mRl1.setLayoutParams(rl1);

        rl2.width = screenWidth;
        mRl2.setLayoutParams(rl2);

        rl3.width = screenWidth;
        mRl3.setLayoutParams(rl3);

        rl4.width = screenWidth;
        mRl4.setLayoutParams(rl4);


//        for (int i = 0; i < 4; i++) {
//            ImageView imageView = new ImageView(this);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                imageView.setTransitionName("tab_iv_" + i);
//            }
//            mImageViews.add(imageView);
//        }
    }

    private void initView() {
//        mVp1.setOffscreenPageLimit(4);
//        mGuidePagerAdapter = new GuidePagerAdapter(mImageViews);
//        mVp1.setAdapter(mGuidePagerAdapter);
//        mVp1.setCurrentItem(mPosition);
    }
}
