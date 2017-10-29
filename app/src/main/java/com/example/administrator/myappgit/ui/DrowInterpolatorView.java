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
        mValueAnimator = ValueAnimator.ofObject(mFloatEvaluator, 0, 1000);


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

    private class MyDecelerateInterpolator implements Interpolator {
        // FIXME: 2017/10/28 需要自定义下，效果做好点。。
        float mFactor = 0.1f;

        @Override
        public float getInterpolation(float input) {
            if (BuildConfig.DEBUG) Log.d("MyDecelerateInterpolato", "input:" + input);
            float result;
//            if (mFactor == 1.0f) {
//                Log.d(TAG, "getInterpolation: aa");
//                result = (float) (1.0f - Math.pow((1.0f - input), 2 * mFactor));
//            } else {
//                Log.d(TAG, "getInterpolation: bb");
//                result = (float) (1.0f - (1.0f - input) * (1.0f - input));
//            }

            if (mFactor == 0.11f) {
                result = (float)(1.0f - (1.0f - input) * (1.0f - input));
            } else {
                result = (float)(Math.pow((1.0f - input), 2 * mFactor));
            }
            return result;
        }
    }
}
