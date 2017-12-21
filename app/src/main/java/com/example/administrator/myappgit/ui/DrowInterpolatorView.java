package com.example.administrator.myappgit.ui;

import android.animation.FloatEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;

import com.example.administrator.myappgit.BuildConfig;

import static android.content.ContentValues.TAG;

/**
 * Created by baiyu on 2017/10/29.
 * 用来测试插值器的。。简单的花小球移动动画
 */

public class DrowInterpolatorView extends View {

    private Paint mPaint;

    private FloatEvaluator mFloatEvaluator;

    private ValueAnimator mValueAnimator;

    float Y = -1;

    float X = -1;

    public DrowInterpolatorView(Context context) {
        super(context);
    }

    public DrowInterpolatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DrowInterpolatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d(TAG, "onSizeChanged: ");

        mPaint = new Paint();
        mFloatEvaluator = new FloatEvaluator();
        mValueAnimator = ValueAnimator.ofObject(mFloatEvaluator, 0, 600);


        mValueAnimator.setDuration(3000);
        mValueAnimator.setInterpolator(new MyDecelerateInterpolator());

        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Y = (float) animation.getAnimatedValue();
                X = animation.getCurrentPlayTime();
                invalidate();
            }
        });
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d(TAG, "onLayout: ");

        mValueAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, "onDraw: ");
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(0xff4CAF50);


//        canvas.drawLine(X * 2, Y, X * 2, Y, mPaint);
        canvas.drawCircle(X / 5, Y, 20, mPaint);
//        canvas.save();
//        canvas.restore();
    }

    static public class MyDecelerateInterpolator implements Interpolator {
        // FIXME: 2017/10/28 需要自定义下，效果做好点。。
        float mFactor = 0.1f;

        @Override
        public float getInterpolation(float input) {
            if (BuildConfig.DEBUG) Log.d("MyDecelerateInterpolato", "input:" + input);
            float result;
            // TODO: 2017/10/31 0031 这个比较符合要求了。。但是应为尾巴的长度是根据速度来的，所以到后面尾巴就看不见了。。前面速度快尾巴显示的时间短，不明显，和预定效果还有出入
            //下面这个就是这个插值器的曲线
            //http://zuotu.91maths.com/#W3sidHlwZSI6MCwiZXEiOiIxKnNpbigzLjE0MTU5MjYqMSp4KSIsImNvbG9yIjoiIzAwMDAwMCJ9LHsidHlwZSI6MCwiZXEiOiJzaW4oMy4xNDE1OTI2KjEvMyooeCsxLzYqMy4xNDE1OTI2KSkiLCJjb2xvciI6IiMwMDAwMDAifSx7InR5cGUiOjEwMDAsIndpbmRvdyI6WyItMC44Nzk3ODUxMjg4MzU2NDExIiwiMi4yOTQwNDI5OTYxNjQzNDY3IiwiLTAuMTk2NTI5MzI3NTcyODQyMDMiLCIxLjc1NjU5NTY3MjQyNzE2MjciXX1d
            if (input < 0.2618f) {
                result = (float) Math.sin(Math.PI * 0.9f * input);
            } else {
                result = (float) Math.sin(Math.PI * 1f / 3 * (input + 1f / 6 * Math.PI));
            }

            return result;
        }
    }
}
