package com.example.administrator.myappgit.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by baiyu on 2017/4/22.
 */
public class WaveView extends View {

    private int mViewWidth;
    private int mViewHeight;

    /**
     * 水位线
     */
    private float mLeveLine;

    private float mWaveHeight = 20;

    private float mWaveWidth = 200;

    private float mLeftSide;

    private float mMoveLen;
    private float mMoveLen2;

    public static final float SPEED = 10f;

    private List<Point> mPointList;
    private List<Point> mPointList2;
    private Paint mPaint;
    private Paint mPaint2;

    private Path mWavePath;
    private Path mWavePath2;
    private boolean isMeasured = false;

    private Timer timer;
    private MyTimerTask mTask;

    Handler updataHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mMoveLen += SPEED;
            mMoveLen2 += SPEED / 3 * 2;
            //mLeveLine -= 0.1f;
            if (mLeveLine < 0) {
                mLeveLine = 0;
            }

            for (int i = 0; i < mPointList.size(); i++) {

                mPointList.get(i).setX(mPointList.get(i).getX() + SPEED);
                mPointList2.get(i).setX(mPointList2.get(i).getX() + SPEED / 3 * 2);

                switch (i % 4) {

                    case 0:
                    case 2:
                        mPointList.get(i).setY(mLeveLine);
                        mPointList2.get(i).setY(mLeveLine);

                        break;
                    case 1:
                        mPointList.get(i).setY(mLeveLine + mWaveHeight);
                        mPointList2.get(i).setY(mLeveLine + mWaveHeight);
                        break;
                    case 3:
                        mPointList.get(i).setY(mLeveLine - mWaveHeight);
                        mPointList2.get(i).setY(mLeveLine - mWaveHeight);
                        break;

                }
                if (mMoveLen >= mWaveWidth) {
                    mMoveLen = 0;
                    resetPoints();
                }
                if (mMoveLen2 >= mWaveWidth) {
                    mMoveLen2 = 0;
                    resetPoints2();
                }
                invalidate();
            }
        }
    };

    private void resetPoints() {
        mLeftSide = -mWaveWidth;
        for (int i = 0; i < mPointList.size(); i++) {
            mPointList.get(i).setX(i * mWaveWidth / 4 - mWaveWidth);
        }

    }

    private void resetPoints2() {
        mLeftSide = -mWaveWidth;

        for (int i = 0; i < mPointList2.size(); i++) {
            mPointList2.get(i).setX(i * mWaveWidth / 4 - mWaveWidth);
        }
    }

    public WaveView(Context context) {
        super(context);
        init();
    }

    private void init() {
        mPointList = new ArrayList<>();
        mPointList2 = new ArrayList<>();

        timer = new Timer();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(0x00bcd4);
        mPaint.setAlpha(200);


        mPaint2 = new Paint();
        mPaint2.setAntiAlias(true);
        mPaint2.setStyle(Paint.Style.FILL);
        mPaint2.setColor(0x0097a7);
        mPaint2.setAlpha(150);

        mWavePath = new Path();
        mWavePath2 = new Path();
    }

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        start();
    }

    private void start() {
        if (mTask != null) {
            mTask.cancel();
            mTask = null;
        }
        mTask = new MyTimerTask(updataHandler);
        timer.schedule(mTask, 0, 10);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!isMeasured) {
            isMeasured = true;
            mViewHeight = getMeasuredHeight();
            mViewWidth = getMeasuredWidth();

            mLeveLine = mViewHeight * 0.95f;

//            mWaveHeight = mWaveWidth / 2.5f;

            mWaveWidth = mViewWidth * 1.8f;

            mWaveHeight = mWaveWidth / 20f;

            mLeftSide = -mWaveWidth;

            int n = (int) Math.round(mViewWidth / mWaveWidth + 0.5);

            for (int i = 0; i < (4 * n + 5); i++) {
                float x = i * mWaveWidth / 4 - mWaveWidth;
                float y = 0;
                switch (i % 4) {
                    case 0:
                    case 2:
                        y = mLeveLine;
                        break;
                    case 1:
                        y = mLeveLine + mWaveHeight;
                        break;
                    case 3:
                        y = mLeveLine - mWaveHeight;
                        break;

                }
                mPointList.add(new Point(x, y));
                mPointList2.add(new Point(x, y));
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {

        mWavePath.reset();
        mWavePath2.reset();
        int i = 0;
        mWavePath.moveTo(mPointList.get(0).getX(), mPointList.get(0).getY());

        for (; i < mPointList.size() - 2; i += 2) {
            mWavePath.quadTo(mPointList.get(i + 1).getX(), mPointList.get(i + 1).getY(), mPointList.get(i + 2).getX()
                    , mPointList.get(i + 2).getY());
        }
        mWavePath.lineTo(mPointList.get(i).getX(), 0);
        mWavePath.lineTo(mLeftSide, 0);
        mWavePath.close();

        float excursion = mWaveWidth / 8;

        mWavePath2.moveTo(mPointList2.get(0).getX() - excursion, mPointList.get(0).getY());

        i = 0;

        for (; i < mPointList2.size() - 2; i += 2) {
//            System.out.println(i % 4 < 2 ? -2 * mWaveHeight : 2 * mWaveHeight);
            mWavePath2.quadTo(mPointList2.get(i + 1).getX() - excursion, mPointList2.get(i + 1).getY() + (i % 4 < 2
                            ? -2 * mWaveHeight : 2 * mWaveHeight),
                    mPointList2.get(i + 2).getX() - excursion
                    , mPointList2.get(i + 2).getY());

        }
        mWavePath2.lineTo(mPointList2.get(i).getX() - excursion, 0);
        mWavePath2.lineTo(mLeftSide, 0);
        mWavePath2.close();
        canvas.drawPath(mWavePath, mPaint);
        canvas.drawPath(mWavePath2, mPaint2);

        super.onDraw(canvas);
    }

    private class MyTimerTask extends TimerTask {
        Handler handler;

        public MyTimerTask(Handler handler) {
            this.handler = handler;
        }

        @Override
        public void run() {
            handler.sendMessage(handler.obtainMessage());
        }
    }

    private class Point {
        private float x;
        private float y;

        public float getX() {
            return x;
        }

        public float getY() {
            return y;
        }

        public void setX(float x) {
            this.x = x;
        }

        public void setY(float y) {
            this.y = y;
        }

        public Point(float x, float y) {
            this.x = x;
            this.y = y;
        }

    }
}
