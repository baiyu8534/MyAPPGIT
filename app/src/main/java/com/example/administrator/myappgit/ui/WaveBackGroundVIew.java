package com.example.administrator.myappgit.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.myappgit.R;


/**
 * Created by baiyu on 2017/4/17.
 */
public class WaveBackGroundVIew extends View {

    public interface AnimationSetupCallback {
        public void onSetupAnimation(WaveBackGroundVIew waveBackGroundVIew);
    }

    private AnimationSetupCallback animationSetupCallback;

    private Matrix shaderMatrix;

    private float maskX, maskY=50;
    // if true, the shader will display the wave
    private boolean sinking;
    // true after the first onSizeChanged
    private boolean setUp;

    // shader containing a repeated wave
    private BitmapShader shader;

    private Drawable wave;
    // (getHeight() - waveHeight) / 2
    private float offsetY;
    public Paint mPaint;

    public float getMaskX() {
        return maskX;
    }

    public void setMaskX(float maskX) {
        this.maskX = maskX;
    }

    public float getMaskY() {
        return maskY;
    }

    public void setMaskY(float maskY) {
        this.maskY = maskY;
    }

    public WaveBackGroundVIew(Context context) {
        super(context);
        init();

    }

    private void init() {
        shaderMatrix = new Matrix();
        mPaint = new Paint();
    }

    public WaveBackGroundVIew(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WaveBackGroundVIew(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setSinking(boolean sinking) {
        this.sinking = sinking;
    }

    public boolean isSetUp() {
        return setUp;
    }


    public void setAnimationSetupCallback(AnimationSetupCallback animationSetupCallback) {
        this.animationSetupCallback = animationSetupCallback;
    }



    @Override
    protected void onDraw(Canvas canvas) {


        Drawable d = getResources().getDrawable(R.drawable.hander);



        canvas.drawPaint(mPaint);
        d.setBounds(0,0,getWidth(),getHeight());
        d.draw(canvas);

        createShader();





//        createShader(canvas);
//        // modify text paint shader according to sinking state
        if (sinking && shader != null) {

            // first call after sinking, assign it to our paint
            if (mPaint.getShader() == null) {
                mPaint.setShader(shader);
            }

            // translate shader accordingly to maskX maskY positions
            // maskY is affected by the offset to vertically center the wave
            shaderMatrix.setTranslate(maskX, maskY);

            // assign matrix to invalidate the shader
            shader.setLocalMatrix(shaderMatrix);
        } else {
            mPaint.setShader(null);
        }

        if (!setUp) {
            setUp = true;
            if (animationSetupCallback != null) {
                animationSetupCallback.onSetupAnimation(WaveBackGroundVIew.this);
            }
        }

        super.onDraw(canvas);
    }



    private void createShader() {

        if (wave == null) {
            wave = getResources().getDrawable(R.drawable.wave);
        }

        int waveW = wave.getIntrinsicWidth();
        int waveH = wave.getIntrinsicHeight();

        Bitmap b = Bitmap.createBitmap(waveW, waveH, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);

        c.drawColor(Color.BLACK);

        wave.setBounds(0, 0, waveW, waveH);
        wave.draw(c);

        shader = new BitmapShader(b, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP);
        mPaint.setShader(shader);

        offsetY = (getHeight() - waveH) / 2;
    }

//    @Override
//    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//        super.onSizeChanged(w, h, oldw, oldh);
//        if (!setUp) {
//            setUp = true;
//            if (animationSetupCallback != null) {
//                animationSetupCallback.onSetupAnimation(WaveBackGroundVIew.this);
//            }
//        }
//    }
}
