package com.example.administrator.myappgit.ui;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.FloatEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

import com.example.administrator.myappgit.R;
import com.example.administrator.myappgit.utils.UIUtil;

/**
 * 文件名：BublesSwitchView
 * 描述：http://www.ui.cn/detail/292734.html的SwitchButton的效果
 * 作者：白煜
 * 时间：2017/10/23 0023
 * 版权：
 */

public class BublesSwitchView extends View {
    // TODO: 2017/10/25 0025 还可以优化下，比如自定义插值器，设置笔的渲染图片

    // FIXME: 2017/10/29 绘制效率问题。。。待解决。。感觉是慢。。很慢。。放到recycleView中测试下。。

    //c开头是小球相关属性  s开头是背景田径场属性  m开头是控件View的属性

    /**
     * 背景类型
     * COLOR, 纯色渐变
     * DRAWABLE; 选中和未选中的drawable之间的滑动切换
     */
    public static enum BgType {
        COLOR,
        BITMAP;

        private BgType() {
        }
    }

    /**
     * 背景类型 默认BgType.COLOR
     */
    private BgType mBgType = BgType.COLOR;

    private Context mContext;
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
    private float cPercent; //圆半径 相对于f一半高度的百分比
    private float spacePercent; //圆与边框距离 相对于一半高度的百分比
    // F false T true ....
    private float cFCenterX, cCenterY; //圆心坐标 未选中状态
    private float cTCenterX; //圆心坐标 选中状态

    /**
     * 尾巴的RectF
     */
    RectF oval;

    /**
     * 未选中状态 尾巴（椭圆)的左上右下
     */
    private float oFLeft, oFTop, oFRight, oFBotton;

    /**
     * 选中状态下 尾巴（椭圆）的左上右下
     */
    private float oTLeft, oTTop, oTRight, oTBotton;

    /**
     * 控件的开关状态（现在只是动画的类型标志（从未选中到选中 还是 选中到未选中））要用其他的来标识，select就还是开关的状态
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
     * 数值动画变化的百分比 0 --> 1 （没加插值器，就是平稳变化）
     */
    private float mAnimatedFraction;

    /**
     * 动画执行时间 随控件大小改变
     */
    private long time;
    /**
     * 动画执行的当前时间
     */
    private long mCurrentPlayTime;

    /**
     * 小球移动速度 用速度也不好搞。。
     */
    //private float speed = 700;

    /**
     * 不知道怎么形容的比例。。。
     */
    private float xPercent = 80;

    /**
     * 圆心偏移距离
     */
    private float cCenterOffset;

    /**
     * 小球移动速度的插值器
     */
    private Interpolator mInterpolator;

    /**
     * 小球移动时颜色改变的估值器
     */
    private ArgbEvaluator mArgbEvaluator;

    /**
     * 选中状态小球的颜色 ARGB格式
     */
    private int cSelectColer;
    private int cSelectColerTemp = 0;

    /**
     * 未选中状态小球的颜色
     */
    private int cUnSelectColer;
    private int cUnSelectColerTemp = 0;

    /**
     * 选中状态 背景 的颜色 ARGB格式
     */
    private int sSelectColer;
    private int sSelectColerTemp = 0;

    /**
     * 未选中状态 背景 的颜色
     */
    private int sUnSelectColer;
    private int sUnSelectColerTemp = 0;

    /**
     * paint 的图片渲染
     */
    private BitmapShader mShader;

    /**
     * shader的matrix
     */
    private Matrix mShaderMatrix;


    // TODO: 2017/10/29 看了一下textView的setTextColor 也是把set进去的color值保存在mTextColor中 而paint中设置的color是mCurTextColor ，不是同一个 ，在setTextColor（）中会先让mTextColor等于设置color，然后让mCurTextColor等于mTextColor。。貌似只用一个变量去存储textColor，直接改变后执行 invalidate();刷新显示后，颜色也不会变。反正我这个是这样，用一个存储，set过后 invalidate();颜色没变。。
    /**
     * 未选中时的背景drawable resId
     */
    private int sUnSelectBgDrawableResId;
    private int sUnSelectBgDrawableResIdTemp = 0;

    /**
     * 选中时的背景drawable resId
     */
    private int sSelectBgDrawableResId;
    private int sSelectBgDrawableResIdTemp = 0;

    /**
     * 小球移动时背景移动距离的估值器
     */
    private FloatEvaluator mFloatEvaluator;

    public interface OnCheckedChangeListener {
        void onCheckedChange(BublesSwitchView bublesSwitchView, boolean isSelected);
    }

    private OnClickListener mOnClickListener;

    private OnCheckedChangeListener mOnCheckedChangeListener;

    /**
     * 设置属性方法
     * ---------------------------
     **/

    /**
     * 获取选中状态
     *
     * @return
     */
    public boolean isSelect() {
        return select;
    }

    /**
     * 设置选中状态
     *
     * @param select
     */
    public void setSelect(boolean select) {
        this.select = select;
    }

    @Override
    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    /**
     * 监听选中状态的改变
     *
     * @param onCheckedChangeListener
     */
    public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        mOnCheckedChangeListener = onCheckedChangeListener;
    }

    /**
     * 设置是否可以点击
     *
     * @param enabled
     */
    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
    }

    /**
     * 设置小球运动速度的插值器
     *
     * @param interpolator
     */
    public void setInterpolator(Interpolator interpolator) {
        this.mInterpolator = interpolator;
    }

    /**
     * 设置小球的选中和未选中的颜色
     *
     * @param unSelectColorRes
     * @param selectColorRes
     */
    public void setCircleColors(int unSelectColorRes, int selectColorRes) {
        cUnSelectColerTemp = UIUtil.colorResId2ColorInt(mContext, unSelectColorRes);
        cSelectColerTemp = UIUtil.colorResId2ColorInt(mContext, selectColorRes);
    }

    /**
     * 设置背景的选中和未选中的颜色
     *
     * @param unSelectColorRes
     * @param selectColorRes
     */
    public void setBGColors(int unSelectColorRes, int selectColorRes) {
        sUnSelectColerTemp = UIUtil.colorResId2ColorInt(mContext, unSelectColorRes);
        sSelectColerTemp = UIUtil.colorResId2ColorInt(mContext, selectColorRes);
    }

    /**
     * 设置背景的选中和未选中的图片resId
     *
     * @param sUnSelectBgDrawableResId
     * @param sSelectBgDrawableResId
     */
    public void setBGDrawableResIds(int sUnSelectBgDrawableResId, int sSelectBgDrawableResId) {
        this.sUnSelectBgDrawableResIdTemp = sUnSelectBgDrawableResId;
        this.sSelectBgDrawableResIdTemp = sSelectBgDrawableResId;
    }

    /**
     * 设置背景的类型
     *
     * @param bgType
     */
    public void setBgType(BgType bgType) {
        mBgType = bgType;
    }

    /**
     * ------------------------
     * 设置属性方法结束
     */

    public BublesSwitchView(Context context) {
        super(context);
        mContext = context;
    }

    public BublesSwitchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public BublesSwitchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
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

        //因为是实时绘制，用数值动画，开始时刷新绘制要先画一个圆，要不会闪一下
        if (animIsStart && moveX == -1) {

            //开始（点击后，动画启动）
            mValueAnimator.start();
            if (!select) {

                if (mBgType == BgType.COLOR) {
                    drawStaticColorBG(canvas, sUnSelectColer);
                } else if (mBgType == BgType.BITMAP) {
                    drawStaticBitmapBG(canvas, 0);
                }
                drowStaticCircle(canvas, cUnSelectColer, cFCenterX, cCenterY);

            } else {

                if (mBgType == BgType.COLOR) {
                    drawStaticColorBG(canvas, sSelectColer);
                } else if (mBgType == BgType.BITMAP) {
                    drawStaticBitmapBG(canvas, mWidth);
                }
                drowStaticCircle(canvas, cSelectColer, cTCenterX, cCenterY);

            }
        } else if (animIsStart) {

            //过程中（圆移动过程中）
            if (!select) {

                if (mBgType == BgType.COLOR) {
                    drawStaticColorBG(canvas, (Integer) mArgbEvaluator.evaluate(mAnimatedFraction, sUnSelectColer, sSelectColer));
                } else if (mBgType == BgType.BITMAP) {
                    drawStaticBitmapBG(canvas, mFloatEvaluator.evaluate(mAnimatedFraction, 0, mWidth));
                }

                paint.setAntiAlias(true);
                paint.setStyle(Paint.Style.FILL);
                paint.setColor((Integer) mArgbEvaluator.evaluate(mAnimatedFraction, cUnSelectColer, cSelectColer));
                canvas.drawCircle(cFCenterX + moveX, cCenterY, cRadius, paint);
                canvas.save();
                //实际移动距离过一半后尾巴要缩小
                //因为不管控件多大（即小球走过的距离不定），但是动画执行时间一样，导致控件越大，小球看起来走的越快
                //mAnimatedFraction * mWidth / (time / 100) 需要和小球移动的速度有关，即速度越大，尾巴越长 速度：mWidth / (time / 100)
                //time / 100是让数值变大，尾巴明显
                if (moveX < (mWidth - mHeight) / 2) {
                    oval.left = oFLeft + moveX - mAnimatedFraction * mWidth / (time / xPercent);
                    oval.top = oFTop;
                    oval.right = oFRight + moveX + mAnimatedFraction * mWidth / (time / xPercent);
                    oval.bottom = oFBotton;
                } else {
                    oval.left = oFLeft + moveX - (mAnimatedFraction * mWidth / (time / xPercent)) +
                            2 * ((mAnimatedFraction - 0.5f) * mWidth / (time / xPercent));
                    oval.top = oFTop;
                    oval.right = oFRight + moveX + (mAnimatedFraction * mWidth / (time / xPercent)) -
                            2 * ((mAnimatedFraction - 0.5f) * mWidth / (time / xPercent));
                    oval.bottom = oFBotton;
                }
                canvas.drawArc(oval, 90, 180, false, paint);
                canvas.save();
                paint.reset();
            } else {

                if (mBgType == BgType.COLOR) {
                    drawStaticColorBG(canvas, (Integer) mArgbEvaluator.evaluate(mAnimatedFraction, sSelectColer, sUnSelectColer));
                } else if (mBgType == BgType.BITMAP) {
                    drawStaticBitmapBG(canvas, mFloatEvaluator.evaluate(mAnimatedFraction, mWidth, 0));
                }

                paint.setAntiAlias(true);
                paint.setStyle(Paint.Style.FILL);
                paint.setColor((Integer) mArgbEvaluator.evaluate(mAnimatedFraction, cSelectColer, cUnSelectColer));
                canvas.drawCircle(cTCenterX - moveX, cCenterY, cRadius, paint);
                canvas.save();
                //实际移动距离过一半后尾巴要缩小
                if (moveX < (mWidth - mHeight) / 2) {
                    oval.left = oTLeft - moveX - mAnimatedFraction * mWidth / (time / xPercent);
                    oval.top = oTTop;
                    oval.right = oTRight - moveX + mAnimatedFraction * mWidth / (time / xPercent);
                    oval.bottom = oTBotton;
                } else {
                    oval.left = oTLeft - moveX - (mAnimatedFraction * mWidth / (time / xPercent)) +
                            2 * ((mAnimatedFraction - 0.5f) * mWidth / (time / xPercent));
                    oval.top = oTTop;
                    oval.right = oTRight - moveX + (mAnimatedFraction * mWidth / (time / xPercent)) -
                            2 * ((mAnimatedFraction - 0.5f) * mWidth / (time / xPercent));
                    oval.bottom = oTBotton;
                }
                canvas.drawArc(oval, 90, -180, false, paint);
                canvas.save();
                paint.reset();
            }
        } else {
            //结束 （动画结束后）
            if (!select) {
                if (mBgType == BgType.COLOR) {
                    drawStaticColorBG(canvas, sUnSelectColer);
                } else if (mBgType == BgType.BITMAP) {
                    drawStaticBitmapBG(canvas, 0);
                }
                drowStaticCircle(canvas, cUnSelectColer, cFCenterX, cCenterY);
            } else {
                if (mBgType == BgType.COLOR) {
                    drawStaticColorBG(canvas, sSelectColer);
                } else if (mBgType == BgType.BITMAP) {
                    drawStaticBitmapBG(canvas, mWidth);
                }
                drowStaticCircle(canvas, cSelectColer, cTCenterX, cCenterY);
            }
        }
        canvas.restore();
    }

    /**
     * 画纯色背景田径场
     *
     * @param canvas
     * @param colorInt ARGB 0xAARRGGBB
     */
    private void drawStaticColorBG(Canvas canvas, int colorInt) {
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(colorInt);
        canvas.drawPath(sPath, paint); // 画出田径场
        canvas.save();
        paint.reset();
    }

    /**
     * 画图片背景田径场
     *
     * @param canvas
     * @param translateX 图片的横向偏移量
     */
    private void drawStaticBitmapBG(Canvas canvas, float translateX) {
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);

        mShaderMatrix.setTranslate(translateX, 0);
        mShader.setLocalMatrix(mShaderMatrix);

        paint.setShader(mShader);
        canvas.drawPath(sPath, paint); // 画出田径场
        canvas.save();
        paint.reset();
    }

    /**
     * 画静态的小球
     *
     * @param canvas
     * @param colorInt ARGB 0xAARRGGBB
     */
    private void drowStaticCircle(Canvas canvas, int colorInt, float centerX, float centerY) {
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(colorInt);
        canvas.drawCircle(centerX, centerY, cRadius, paint);
        canvas.save();
        paint.reset();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        oval = new RectF();
        mArgbEvaluator = new ArgbEvaluator();
        mShaderMatrix = new Matrix();
        mFloatEvaluator = new FloatEvaluator();

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
//        time = (long) (speed * 100f / mWidth * 4);
        cCenterOffset = halfHeight * 0.16f;
        cPercent = 0.64f;
        spacePercent = 1f - cPercent;

        // TODO: 2017/10/28 现在是写死的，但是也没什么。。还没想到好方法去根据控件大小动态设置的函数模型
        //动画执行时间 根据控件宽度来设置
        if (mWidth <= 200) {
            time = 450;
        } else if (mWidth <= 300) {
            time = 500;
        } else if (mWidth <= 500) {
            time = 550;
        } else if (mWidth <= 700) {
            time = 600;
        } else {
            time = 700;
        }

        cFCenterX = halfHeight - cCenterOffset;
        cTCenterX = sWidth - halfHeight + cCenterOffset;
        cCenterY = halfHeight;
        cRadius = halfHeight * cPercent;

        oFLeft = halfHeight * spacePercent - cCenterOffset;
        oFTop = halfHeight * spacePercent;
        oFRight = sHeight - halfHeight * spacePercent - cCenterOffset;
        oFBotton = sHeight - halfHeight * spacePercent;

        oTLeft = sWidth - sHeight + halfHeight * spacePercent + cCenterOffset;
        oTTop = halfHeight * spacePercent;
        oTRight = sWidth - halfHeight * spacePercent + cCenterOffset;
        oTBotton = sHeight - halfHeight * spacePercent;


        RectF sRectF = new RectF(sLeft, sTop, sBottom, sBottom);
        sPath.arcTo(sRectF, 90, 180);
        sRectF.left = sRight - sBottom;
        sRectF.right = sRight;
        sPath.arcTo(sRectF, 270, 180);
        sPath.close();    // path准备田径场的路径


        mValueAnimator = ValueAnimator.ofObject(new FloatEvaluator(), 0, cTCenterX - cFCenterX);
        // TODO: 2017/10/25 0025 可以自定义一个插值器 ，返回和网页上相同的数值变化率
        // 用Android实现的这几个长用的插值器效果不行
        // 默认插值器DecelerateInterpolator
        mValueAnimator.setInterpolator(new DecelerateInterpolator());
        mValueAnimator.setDuration(time);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                mCurrentPlayTime = animation.getCurrentPlayTime();
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
                //确保点击后动画在执行过程中不可以重复的点击
                //动画结束后才改变状态
                // FIXME: 2017/10/29 动画结束后才改变状态有点晚 现在的select只是标识了 动画的类型（从未选中到选中 还是 选中到未选中），改个名字。然后在点击事件中判断动画没开始的状态，点击了就改变选中状态，动画执行中即animIsStart == true不去改变状态即可
                animIsStart = false;
                moveX = -1;
                select = !select;
                if (mOnCheckedChangeListener != null) {
                    mOnCheckedChangeListener.onCheckedChange(BublesSwitchView.this, select);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
//        createShader();
    }

    /**
     * 在绘制之前 设置属性
     *
     * @param changed
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        //在绘制之前onDraw之前去设置属性
        if (mValueAnimator != null && mInterpolator != null) {
            mValueAnimator.setInterpolator(mInterpolator);
        }

        //小球默认选中和未选中的颜色
        cSelectColer = 0xffE91E63;
        cUnSelectColer = 0xff5D646E;

        //背景默选中和未选中的颜色
        sSelectColer = 0xff00796B;
        sUnSelectColer = 0xff000000;

        //背景默选中和未选中的的图片
        sSelectBgDrawableResId = R.drawable.day;
        sUnSelectBgDrawableResId = R.drawable.night;

        if (cSelectColerTemp != 0) {
            cSelectColer = cSelectColerTemp;
            cUnSelectColer = cUnSelectColerTemp;
        }

        if (sSelectColerTemp != 0) {
            sSelectColer = sSelectColerTemp;
            sUnSelectColer = sUnSelectColerTemp;
        }

        if (sSelectBgDrawableResIdTemp != 0) {
            sSelectBgDrawableResId = sSelectBgDrawableResIdTemp;
            sUnSelectBgDrawableResId = sUnSelectBgDrawableResIdTemp;
        }
        createShader();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                return true;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:
                //确保点击后动画在执行过程中不可以重复的点击
                if (!animIsStart) {
                    animIsStart = true;
                    invalidate();
                    if (mOnClickListener != null) {
                        mOnClickListener.onClick(this);
                    }
                }
                break;
        }
        return super.onTouchEvent(event);
    }


    /**
     * 默认的插值器
     */
    private class MyDecelerateInterpolator implements Interpolator {
        // FIXME: 2017/10/28 需要自定义下，效果做好点。。
        float mFactor = 0.1f;

        @Override
        public float getInterpolation(float input) {
            float result;
            if (mFactor == 1.0f) {
                result = (float) (1.0f - Math.pow((1.0f - input), 2 * mFactor));
            } else {
                result = (float) (1.0f - (1.0f - input) * (1.0f - input));
            }
            return result;
        }
    }

    /**
     * 初始化paint的shader
     */
    private void createShader() {

        //重新设置图片的宽高
        Bitmap unStlectBitmap = UIUtil.resetBitmapWidthAndHeight(
                BitmapFactory.decodeResource(getResources(), sUnSelectBgDrawableResId),
                mWidth, mHeight
        );
        Bitmap StlectBitmap = UIUtil.resetBitmapWidthAndHeight(
                BitmapFactory.decodeResource(getResources(), sSelectBgDrawableResId),
                mWidth, mHeight
        );

        //最终的bitmap
        Bitmap bitmap = Bitmap.createBitmap(mWidth * 2, mHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        Rect rect = new Rect();
        rect.left = 0;
        rect.top = 0;
        rect.right = mWidth;
        rect.bottom = mHeight;

        //拼接两个bitmap
        canvas.drawBitmap(unStlectBitmap, rect, rect, paint);
        canvas.drawBitmap(StlectBitmap, mWidth, 0, paint);

        mShader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP);
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
