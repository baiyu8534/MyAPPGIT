package com.example.administrator.myappgit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.myappgit.MainActivity;
import com.example.administrator.myappgit.R;
import com.example.administrator.myappgit.adapter.GuidePagerAdapter;
import com.example.administrator.myappgit.utils.TypefacesUtility;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by baiyu on 2017/4/23.
 * 引导页
 */
public class GuideActivity extends BaseActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    @BindView(R.id.vp)
    ViewPager mVp;
    @BindView(R.id.bt_guide)
    Button mBtGuide;
    @BindView(R.id.ll_point)
    LinearLayout mLlPoint;
    @BindView(R.id.point_red)
    View mPointRed;
    @BindView(R.id.tv1)
    TextView mTv1;
    @BindView(R.id.tv2)
    TextView mTv2;
    @BindView(R.id.tv3)
    TextView mTv3;
    @BindView(R.id.point_1)
    View mPoint1;

    private List<ImageView> imageViews;

    private int[] imageRes = {
            R.drawable.guide_1_s,
            R.drawable.guide_2_s,
            R.drawable.guide_3_s
    };
    public GuidePagerAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);

//        mTitanicTextView.setTypeface(Typefaces.get(this, "Satisfy-Regular.ttf"));

        initData();
        initView();
        initListener();
    }

    private void initListener() {
        mBtGuide.setOnClickListener(this);
    }

    private void initData() {
        imageViews = new ArrayList<>();
        for (int image : imageRes) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(image);
//            Glide.with(GuideActivity.this).load(image).into(imageView);
            imageViews.add(imageView);
        }
    }

    private void initView() {
        mTv1.setTypeface(TypefacesUtility.get(this, "Satisfy-Regular.ttf"));
        mTv2.setTypeface(TypefacesUtility.get(this, "Satisfy-Regular.ttf"));
        mTv3.setTypeface(TypefacesUtility.get(this, "Satisfy-Regular.ttf"));

        mTv1.setAlpha(0f);
        mTv1.setScaleX(0.8f);
        mTv1.setScaleY(0.8f);

        mTv1.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(500).start();

        mTv2.setAlpha(0f);
        mTv3.setAlpha(0f);

        mAdapter = new GuidePagerAdapter(imageViews);
        mVp.setAdapter(mAdapter);
        mVp.setOnPageChangeListener(this);

        mBtGuide.setClickable(false);
        mBtGuide.setVisibility(View.VISIBLE);
        mBtGuide.setAlpha(0);

        //viewpager全屏显示时不适合加动画
        //mVp.setPageTransformer(true,new GuideViewPageTransformer());


    }

    /**
     * 两个点之间的距离
     */
    private int width = 0;

    private boolean flag = false;

    private float prePosition = 0;

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        //字和按钮随着pager滑动而改变透明度

        if (width == 0) {
            width = mLlPoint.getChildAt(1).getLeft() - mLlPoint.getChildAt(0).getLeft();
        }
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(mPointRed.getMeasuredWidth(), mPointRed
                .getMeasuredHeight());

        params.leftMargin = (int) (position * width + positionOffset * width);
        mPointRed.setLayoutParams(params);

        //控制按钮的显示
        if (position == 2)
            mBtGuide.setClickable(true);

        if (position + positionOffset > 0 && position + positionOffset < 1 && position + positionOffset - prePosition > 0) {

            mTv1.setAlpha(1 - positionOffset);
            mTv1.setScaleX(1 - 0.2f * positionOffset);
            mTv1.setScaleY(1 - 0.2f * positionOffset);

            mTv2.setAlpha(positionOffset);
            mTv2.setScaleX(0.8f + 0.2f * positionOffset);
            mTv2.setScaleY(0.8f + 0.2f * positionOffset);

            mTv3.setAlpha(0);
            prePosition = position + positionOffset;
        } else if (position + positionOffset > 1 && position + positionOffset < 2 && position + positionOffset - prePosition > 0) {
            mTv2.setAlpha(1 - positionOffset);
            mTv2.setScaleX(1 - 0.2f * positionOffset);
            mTv2.setScaleY(1 - 0.2f * positionOffset);

            mTv3.setAlpha(positionOffset);
            mTv3.setScaleX(0.8f + 0.2f * positionOffset);
            mTv3.setScaleY(0.8f + 0.2f * positionOffset);

            mBtGuide.setAlpha(positionOffset);
            mBtGuide.setScaleX(0.8f + 0.2f * positionOffset);
            mBtGuide.setScaleY(0.8f + 0.2f * positionOffset);

            prePosition = position + positionOffset;
        } else if (position + positionOffset > 1 && position + positionOffset < 2 && position + positionOffset - prePosition < 0) {
            mTv3.setAlpha(1 - (1 - positionOffset));
            mTv3.setScaleX(1 - 0.2f * (1 - positionOffset));
            mTv3.setScaleY(1 - 0.2f * (1 - positionOffset));

            mTv2.setAlpha((1 - positionOffset));
            mTv2.setScaleX(0.8f + 0.2f * (1 - positionOffset));
            mTv2.setScaleY(0.8f + 0.2f * (1 - positionOffset));

            mBtGuide.setAlpha(1 - (1 - positionOffset));
            mBtGuide.setScaleX(1 - 0.2f * (1 - positionOffset));
            mBtGuide.setScaleY(1 - 0.2f * (1 - positionOffset));

            prePosition = position + positionOffset;
        } else if (position + positionOffset > 0 && position + positionOffset < 1 && position + positionOffset - prePosition < 0) {
            System.out.println("1 ---> 0");
            mTv2.setAlpha(1 - (1 - positionOffset));
            mTv2.setScaleX(1 - 0.2f * (1 - positionOffset));
            mTv2.setScaleY(1 - 0.2f * (1 - positionOffset));

            mTv1.setAlpha((1 - positionOffset));
            mTv1.setScaleX(0.8f + 0.2f * (1 - positionOffset));
            mTv1.setScaleY(0.8f + 0.2f * (1 - positionOffset));

            mTv3.setAlpha(0);

            prePosition = position + positionOffset;
        }

    }

    @Override
    public void onPageSelected(int position) {
        //pager滑动停止后才有动画
/*//控制按钮的显示
        if (position == 2) {
            //显示
            mBtGuide.setClickable(true);
            mBtGuide.setVisibility(View.VISIBLE);
            mBtGuide.animate()
                    .alpha(1)
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(500).start();
            mTv1.animate()
                    .alpha(1)
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(500).start();
            mTv3.animate()
                    .alpha(1f)
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(500).start();
            mTv2.animate()
                    .alpha(0)
                    .scaleX(0.8f)
                    .scaleY(0.8f)
                    .setDuration(500).start();
            mTv1.animate()
                    .alpha(0)
                    .scaleX(0.8f)
                    .scaleY(0.8f)
                    .setDuration(500).start();
        } else if (position == 1) {
            //隐藏
            mBtGuide.setClickable(false);
            mTv2.animate()
                    .alpha(1f)
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(500).start();
            mTv1.animate()
                    .alpha(0)
                    .scaleX(0.8f)
                    .scaleY(0.8f)
                    .setDuration(500).start();
            mTv3.animate()
                    .alpha(0)
                    .scaleX(0.8f)
                    .scaleY(0.8f)
                    .setDuration(500).start();
            mBtGuide.animate()
                    .alpha(0)
                    .scaleX(0.8f)
                    .scaleY(0.8f)
                    .setDuration(500).start();
        } else if (position == 0) {
            //隐藏
            mBtGuide.setClickable(false);
            mTv2.animate()
                    .alpha(0)
                    .scaleX(0.8f)
                    .scaleY(0.8f)
                    .setDuration(500).start();
            mTv3.animate()
                    .alpha(0)
                    .scaleX(0.8f)
                    .scaleY(0.8f)
                    .setDuration(500).start();
            mBtGuide.animate()
                    .alpha(0)
                    .scaleX(0.8f)
                    .scaleY(0.8f)
                    .setDuration(500).start();
            mTv1.animate()
                    .alpha(1)
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(500).start();
        }*/

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (!flag) {
            flag = true;
            mPoint1.setBackgroundResource(R.drawable.point_gray_bg);
        }
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(GuideActivity.this, MainActivity.class));

        finish();
    }
}
