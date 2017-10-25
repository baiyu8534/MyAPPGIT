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
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.administrator.myappgit.BuildConfig;

/**
 * 文件名：BublesSwitchView
 * 描述：http://www.ui.cn/detail/292734.html的SwitchButton的效果
 * 作者：白煜
 * 时间：2017/10/23 0023
 * 版权：
 */

public class BublesSwitchView extends View {
    // TODO: 2017/10/25 0025 还可以优化下，比如自定义插值器，设置笔的渲染图片，小球速度的动态设置（和控件大小有关）

    private final Paint paint = new Paint();
    /**
     * 田径场的path
     */
    private final Path sPath = new Path();

    /**
     * 控件的宽高比
     */
    private float crown = 0.4f;

    private int mWidth, mHeight; //控件自身宽高
    private float sWidth, sHeight; //背景田径图宽高 和控件宽高一样
    private float sLeft, sTop, sRight, sBottom; //画背景田径场需要的左上右下
    private float sCenterX, sCenterY;

    private float halfHeight; //一半高度

    private float cRadius; //圆的半径
    private float cPercent = 0.7f; //圆半径 相对于f一半高度的百分比
    private float spacePercent = 0.3f; //圆与边框距离 相对于一半高度的百分比
    // F false T true ....
    private float cFCenterX, cCenterY; //圆心坐标 未选中状态
    private float cTCenterX; //圆心坐标 选中状态

    /**
     * 未选中状态 尾巴（椭圆)的左上右下
     */
    private float oFLeft, oFTop, oFRight, oFBotton;

    /**
     * 选中状态下 尾巴（椭圆）的左上右下
     */
    private float oTLeft, oTTop, oTRight, oTBotton;

    /**
     * 控件的开关状态
     */
    private boolean select = false;

    /**
     * 标识动画是否正在播放
     */
    private boolean animIsStart = false;

    /**
     * 圆实时的移动距离
     */
    private float moveX = -1;
    /**
     * 数值动画，用来产生圆移动距离时中间的渐变数值
     */
    private ValueAnimator mValueAnimator;

    /**
     * 数值动画变化的百分比 0 --> 1
     */
    private float mAnimatedFraction;

    /**
     * 动画执行时间
     */
    private long time = 500;
    /**
     * 动画执行的当前时间
     */
    private long mCurrentPlayTime;

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
        int height = (int) (width * crown);
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

        //画圆和后面的尾巴
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(0xffffffff);


        //因为是实时绘制，用数值动画，开始时刷新绘制要先画一个圆，要不会闪一下
        if (animIsStart && moveX == -1) {
            //开始（点击后，动画启动）
            mValueAnimator.start();
            if (!select) {
                canvas.drawCircle(cFCenterX, cCenterY, cRadius, paint);
            } else {
                canvas.drawCircle(cTCenterX, cCenterY, cRadius, paint);
            }
        } else if (animIsStart) {
            //过程中（圆移动过程中）
            if (!select) {
                canvas.drawCircle(cFCenterX + moveX, cCenterY, cRadius, paint);
                canvas.save();
                RectF oval;
                //实际移动距离过一半后尾巴要缩小
                //因为不管控件多大（即小球走过的距离不定），但是动画执行时间一样，导致控件越大，小球看起来走的越快
                //mAnimatedFraction * mWidth / (time / 100) 需要和小球移动的速度有关，即速度越大，尾巴越长（速度：mWidth / (time / 100) 。time /
                // 100是让数值变大，尾巴明显）
                if (moveX < (mWidth - mHeight) / 2) {
                    oval = new RectF(
                            oFLeft + moveX - mAnimatedFraction * mWidth / (time / 100),
                            oFTop,
                            oFRight + moveX + mAnimatedFraction * mWidth / (time / 100),
                            oFBotton);
                } else {
                    oval = new RectF(
                            oFLeft + moveX - (mAnimatedFraction * mWidth / (time / 100)) + 2 * ((mAnimatedFraction - 0.5f) * mWidth / (time / 100)),
                            oFTop,
                            oFRight + moveX + (mAnimatedFraction * mWidth / (time / 100)) - 2 * ((mAnimatedFraction - 0.5f) * mWidth / (time / 100)),
                            oFBotton);
                }
                canvas.drawArc(oval, 90, 180, false, paint);//小弧形
            } else {
                canvas.drawCircle(cTCenterX - moveX, cCenterY, cRadius, paint);
                canvas.save();
                RectF oval;
                //实际移动距离过一半后尾巴要缩小
                if (moveX < (mWidth - mHeight) / 2) {
                    oval = new RectF(
                            oTLeft - moveX - mAnimatedFraction * mWidth / (time / 100),
                            oTTop,
                            oTRight - moveX + mAnimatedFraction * mWidth / (time / 100),
                            oTBotton);
                } else {
                    oval = new RectF(
                            oTLeft - moveX - (mAnimatedFraction * mWidth / (time / 100)) + 2 * ((mAnimatedFraction - 0.5f) * mWidth / (time / 100)),
                            oTTop,
                            oTRight - moveX + (mAnimatedFraction * mWidth / (time / 100)) - 2 * ((mAnimatedFraction - 0.5f) * mWidth / (time / 100)),
                            oTBotton);
                }
                canvas.drawArc(oval, 90, -180, false, paint);//小弧形
            }
        } else {
            //结束 （动画结束后）
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

        //初始化数值
        mWidth = w; // 视图自身宽度
        mHeight = h; // 视图自身高度
        sLeft = sTop = 0; // 田径场 左和上的坐标
        sRight = mWidth; // 田径场 右占自身的全部
        sBottom = mHeight; // 田径场底部
        sWidth = sRight - sLeft; // 田径场的宽度
        sHeight = sBottom - sTop; // 田径场的高度
//        sCenterX = (sRight + sLeft) / 2; // 田径场的X轴中心坐标
//        sCenterY = (sBottom + sTop) / 2; // 田径场的Y轴中心坐标

        halfHeight = sHeight / 2;

        cFCenterX = halfHeight;
        cTCenterX = sWidth - halfHeight;
        cCenterY = halfHeight;
        cRadius = halfHeight * cPercent;

        oFLeft = halfHeight * spacePercent;
        oFTop = halfHeight * spacePercent;
        oFRight = sHeight - halfHeight * spacePercent;
        oFBotton = sHeight - halfHeight * spacePercent;

        oTLeft = sWidth - sHeight + halfHeight * spacePercent;
        oTTop = halfHeight * spacePercent;
        oTRight = sWidth - halfHeight * spacePercent;
        oTBotton = sHeight - halfHeight * spacePercent;


        RectF sRectF = new RectF(sLeft, sTop, sBottom, sBottom);
        sPath.arcTo(sRectF, 90, 180);
        sRectF.left = sRight - sBottom;
        sRectF.right = sRight;
        sPath.arcTo(sRectF, 270, 180);
        sPath.close();    // path准备田径场的路径


        mValueAnimator = ValueAnimator.ofObject(new FloatEvaluator(), 0, (float) (mWidth - mHeight));
        // TODO: 2017/10/25 0025 可以自定义一个插值器 ，返回和网页上相同的数值变化率
        // 用Android实现的这几个长用的插值器效果不行
//        mValueAnimator.setInterpolator(new AccelerateInterpolator(2));
        mValueAnimator.setDuration(time);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                mCurrentPlayTime = animation.getCurrentPlayTime();
                mAnimatedFraction = animation.getAnimatedFraction();
                if (BuildConfig.DEBUG) Log.d("BublesSwitchView", "mAnimatedFraction:" + mAnimatedFraction);
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
                //确保点击后动画在执行过程中不可以重复的点击
                //动画结束后才改变状态
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
                //确保点击后动画在执行过程中不可以重复的点击
                if (!animIsStart) {
                    animIsStart = true;
                    invalidate();
                }
                break;
        }

        return super.onTouchEvent(event);
    }


    //=====================================================================================================
    //历史版本
    /*@Override
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
                canvas.save();
//                画笑脸弧线
                RectF oval1;
//                if (mCurrentPlayTime < time/2) {
//                    System.out.println("mAnimatedFraction * mWidth / (time * 100)::"+mAnimatedFraction * mWidth / (time / 10));
//                    System.out.println("mCurrentPlayTime * mWidth / (time * 100)::"+mCurrentPlayTime * mWidth / (time * 10));
//                    oval1 = new RectF(
//                            ((sHeight / 2 * 0.3f) + moveX) - mCurrentPlayTime * mWidth / (time * 10),
//                            sHeight / 2 * 0.3f,
//                            sHeight - sHeight / 2 * 0.3f + moveX + mCurrentPlayTime * mWidth / (time * 10),
//                            sHeight - sHeight / 2 * 0.3f);
//                } else {
//                    System.out.println("mAnimatedFraction * mWidth / (time * 100)::"+mAnimatedFraction * mWidth / (time / 10));
//                    System.out.println("mCurrentPlayTime * mWidth / (time * 100)::"+mCurrentPlayTime * mWidth / (time * 10));
//                    oval1 = new RectF(
//                            ((sHeight / 2 * 0.3f) + moveX) - (mCurrentPlayTime * mWidth / (time * 10)) + ((mCurrentPlayTime -
 t ime/2) * mWidth / (time *10)),
//                            sHeight / 2 * 0.3f,
//                            ((sHeight - sHeight / 2 * 0.3f) + moveX) + (mCurrentPlayTime * mWidth / (time * 10)) - (
(mCurrentPlayTime - time/2) * mWidth / (time *10)),
//                            sHeight - sHeight / 2 * 0.3f);
//                }
                System.out.println("moveX::"+moveX);
                System.out.println("mWidth - mHeight::"+(mWidth - mHeight));
                if (moveX < (mWidth - mHeight)/2) {
//                    System.out.println("mAnimatedFraction * mWidth / (time * 100)::"+mAnimatedFraction * mWidth / (time / 500));
//                    System.out.println("mCurrentPlayTime * mWidth / (time * 100)::"+mCurrentPlayTime * mWidth / (time * 5));
                    oval1 = new RectF(
                            ((sHeight / 2 * 0.3f) + moveX) - mAnimatedFraction * mWidth / (time / 100),
                            sHeight / 2 * 0.3f,
                            ((sHeight - sHeight / 2 * 0.3f) + moveX) + mAnimatedFraction * mWidth / (time / 100),
                            sHeight - sHeight / 2 * 0.3f);
                } else {
//                    System.out.println("mAnimatedFraction * mWidth / (time * 100)::"+mAnimatedFraction * mWidth / (time / 10));
//                    System.out.println("mAnimatedFraction)::"+mAnimatedFraction);
//                    System.out.println("mCurrentPlayTime * mWidth / (time * 100)::"+mCurrentPlayTime * mWidth / (time * 10));
                    oval1 = new RectF(
                            ((sHeight / 2 * 0.3f) + moveX) - (mAnimatedFraction * mWidth / (time / 100)) + 2*(
                            (mAnimatedFraction - 0.5f) * mWidth / (time /100)),
                            sHeight / 2 * 0.3f,
                            ((sHeight - sHeight / 2 * 0.3f) + moveX) + (mAnimatedFraction * mWidth / (time / 100)) - 2*(
                            (mAnimatedFraction - 0.5f) * mWidth / (time /100)),
                            sHeight - sHeight / 2 * 0.3f);
                }
                canvas.drawArc(oval1, 90, 180, false, paint);//小弧形

            } else {
                canvas.drawCircle(sWidth - sHeight / 2 - moveX, sHeight / 2, sHeight / 2 * 0.7f, paint);
                canvas.save();
//                画笑脸弧线
                RectF oval1;
                if (moveX < (mWidth - mHeight)/2) {
                    oval1 = new RectF(
                            ((sWidth - sHeight + (sHeight / 2 * 0.3f)) - moveX) - mAnimatedFraction * mWidth / (time / 100),
                            sHeight / 2 * 0.3f,
                            ((sWidth - sHeight / 2 * 0.3f) - moveX) + mAnimatedFraction * mWidth / (time / 100),
                            sHeight - sHeight / 2 * 0.3f);
                } else {
                    oval1 = new RectF(
                            ((sWidth - sHeight + (sHeight / 2 * 0.3f)) - moveX) - (mAnimatedFraction * mWidth / (time / 100)) +
                             2*((mAnimatedFraction - 0.5f) * mWidth / (time /100)),
                            sHeight / 2 * 0.3f,
                            ((sWidth - sHeight / 2 * 0.3f) - moveX) + (mAnimatedFraction * mWidth / (time / 100)) - 2*(
                            (mAnimatedFraction - 0.5f) * mWidth / (time /100)),
                            sHeight - sHeight / 2 * 0.3f);
                }
                canvas.drawArc(oval1, 90, -180, false, paint);//小弧形
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


    }*/


}
