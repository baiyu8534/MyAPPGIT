package com.example.administrator.myappgit.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

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

    private int mWidth, mHeight;
    private float sWidth, sHeight;
    private float sLeft, sTop, sRight, sBottom;
    private float sCenterX, sCenterY;

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
        int height = (int) (width * 0.33f);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(0x00000000);
        canvas.drawPath(sPath, paint); // 画出田径场

        paint.reset();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w; // 视图自身宽度
        mHeight = h; // 视图自身高度
        sLeft = sTop = 0; // 田径场 左和上的坐标
        sRight = mWidth; // 田径场 右占自身的全部
        sBottom = mHeight * 0.8f; // 田径场底部 占全身的百分之八十， 下面预留百分之二十的空间画按钮阴影。
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
    }
}
