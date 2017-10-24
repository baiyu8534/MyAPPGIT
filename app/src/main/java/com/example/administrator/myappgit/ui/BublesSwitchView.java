package com.example.administrator.myappgit.ui;

import android.animation.Animator;
import android.animation.FloatEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

/**
 * 文件名：BublesSwitchView
 * 描述：http://www.ui.cn/detail/292734.html的SwitchButton的效果
 * 作者：白煜
 * 时间：2017/10/23 0023
 * 版权：
 */

public class BublesSwitchView extends View {

    private final Paint paint = new Paint();
    private final Path sPath = new Path();
    private final Path bsPath = new Path();

    private int mWidth, mHeight;
    private float sWidth, sHeight;
    private float sLeft, sTop, sRight, sBottom;
    private float sCenterX, sCenterY;

    private boolean select = false;

    private boolean animIsStart = false;

    private float moveX = -1;
    private ValueAnimator mValueAnimator;
    private float mAnimatedFraction;

    public BublesSwitchView(Context context) {
        super(context);
    }

    public BublesSwitchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BublesSwitchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = (int) (width * 0.35f);
        setMeasuredDimension(width, height);
    }


    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(0xff000000);
        canvas.drawPath(sPath, paint); // 画出田径场
        canvas.save();
        paint.reset();

        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(0xffffffff);


        if (animIsStart && moveX == -1) {
            //开始
            mValueAnimator.start();
            if (!select) {
                canvas.drawCircle(sHeight / 2, sHeight / 2, sHeight / 2 * 0.7f, paint);
            } else {
                canvas.drawCircle(sWidth - sHeight / 2, sHeight / 2, sHeight / 2 * 0.7f, paint);
            }
        } else if (animIsStart) {
            //过程中
            if (!select) {
                canvas.drawCircle(sHeight / 2 + moveX, sHeight / 2, sHeight / 2 * 0.7f, paint);
//                canvas.drawCircle(sHeight / 2, sHeight / 2, sHeight / 2 * 0.7f, paint);
//                canvas.save();
                //画笑脸弧线
//                RectF oval1 = new RectF(
//                        ((sHeight / 2 * 0.3f) + moveX) - mAnimatedFraction * 30,
//                        sHeight / 2 * 0.3f,
//                        sHeight - sHeight / 2 * 0.3f + moveX + mAnimatedFraction * 30,
//                        sHeight - sHeight / 2 * 0.3f);
//                canvas.drawArc(oval1, 90, 180, false, paint);//小弧形

            } else {
                canvas.drawCircle(sWidth - sHeight / 2 - moveX, sHeight / 2, sHeight / 2 * 0.7f, paint);
            }
        } else {
            //结束
            if (!select) {
                canvas.drawCircle(sHeight / 2, sHeight / 2, sHeight / 2 * 0.7f, paint);
            } else {
                canvas.drawCircle(sWidth - sHeight / 2, sHeight / 2, sHeight / 2 * 0.7f, paint);
            }
        }


        canvas.restore();


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w; // 视图自身宽度
        mHeight = h; // 视图自身高度
        sLeft = sTop = 0; // 田径场 左和上的坐标
        sRight = mWidth; // 田径场 右占自身的全部
        sBottom = mHeight; // 田径场底部 占全身的百分之八十， 下面预留百分之二十的空间画按钮阴影。
        sWidth = sRight - sLeft; // 田径场的宽度
        sHeight = sBottom - sTop; // 田径场的高度
        sCenterX = (sRight + sLeft) / 2; // 田径场的X轴中心坐标
        sCenterY = (sBottom + sTop) / 2; // 田径场的Y轴中心坐标

        RectF sRectF = new RectF(sLeft, sTop, sBottom, sBottom);
        sPath.arcTo(sRectF, 90, 180);
        sRectF.left = sRight - sBottom;
        sRectF.right = sRight;
        sPath.arcTo(sRectF, 270, 180);
        sPath.close();    // path准备田径场的路径


        mValueAnimator = ValueAnimator.ofObject(new FloatEvaluator(), 0, (float) (mWidth - mHeight));
        mValueAnimator.setInterpolator(new AccelerateInterpolator(2));
        mValueAnimator.setDuration(600);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mAnimatedFraction = animation.getAnimatedFraction();
                moveX = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        mValueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animIsStart = false;
                moveX = -1;
                select = !select;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                event.getX();
                return true;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:
                if (!animIsStart) {
                    animIsStart = true;
                    invalidate();
                }
                break;
        }

        return super.onTouchEvent(event);
    }


}
