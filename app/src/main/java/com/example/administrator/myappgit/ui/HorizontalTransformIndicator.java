package com.example.administrator.myappgit.ui;

import android.content.Context;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.administrator.myappgit.R;
import com.example.administrator.myappgit.utils.ScreenUtil;

/**
 * list的item横向变换成选项卡的 选项卡自定义view
 */
public class HorizontalTransformIndicator extends HorizontalScrollView implements ViewPager.OnPageChangeListener, View.OnTouchListener {

    private Context mContext;
    private ViewPager mViewPager;
    private String preTransitionId;
    private int firstPosition;

    private int itemLayoutResId;
    private int mScreenWidth;
    private int mCount;
    private LinearLayout mLinearLayout;

    private boolean initializable = false;

    public HorizontalTransformIndicator(Context context) {
        super(context);
    }

    public HorizontalTransformIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HorizontalTransformIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mScreenWidth = ScreenUtil.getScreenWidth(context);
    }

    public void initialization(ViewPager viewPager, String preTransitionId, int itemLayoutResId, int firstPosition) {
        mViewPager = viewPager;
        mCount = mViewPager.getAdapter().getCount();
        this.preTransitionId = preTransitionId;
        this.firstPosition = firstPosition;
        this.itemLayoutResId = itemLayoutResId;

        initView();
        initListener();
        initFirstPosition();
    }

    private void initFirstPosition() {
        final FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) mLinearLayout.getLayoutParams();

        layoutParams.setMargins(-mScreenWidth * firstPosition, layoutParams.topMargin, layoutParams.rightMargin, layoutParams.bottomMargin);

        mLinearLayout.setLayoutParams(layoutParams);

        postDelayed(new Runnable() {
            @Override
            public void run() {

                layoutParams.setMargins(0, 0, 0, 0);
                mLinearLayout.setLayoutParams(layoutParams);

                scrollBy(mScreenWidth * firstPosition, 0);

            }
        }, 10);
    }

    private void initListener() {
        mViewPager.addOnPageChangeListener(this);
        this.setOnTouchListener(this);
    }

    private void initView() {
        if (getChildCount() > 0)
            this.removeAllViews();

        mLinearLayout = new LinearLayout(mContext);
        mLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        mLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        for (int i = 0; i < mCount; i++) {

            RelativeLayout item = (RelativeLayout) LayoutInflater.from(mContext).inflate(itemLayoutResId, mLinearLayout, false);

            ViewGroup.LayoutParams rl = item.getLayoutParams();
            rl.width = mScreenWidth;
            item.setLayoutParams(rl);

            CardView cv_item = item.findViewById(R.id.cv_item);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cv_item.setTransitionName(preTransitionId + i);
            }
            mLinearLayout.addView(item);
        }
        addView(mLinearLayout);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (initializable) {
            float mTranslationX = mScreenWidth * (position + positionOffset);
            scrollTo((int) mTranslationX, 0);
            invalidate();
        } else {
            initializable = true;
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //不能滑动
        return true;
        //可以滑动
        //return false;
    }
}
