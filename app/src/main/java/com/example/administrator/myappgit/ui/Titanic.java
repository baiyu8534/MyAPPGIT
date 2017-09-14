package com.example.administrator.myappgit.ui;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Build;
import android.view.animation.LinearInterpolator;

/**
 * Created by baiyu on 2017/2/12.
 */
public class Titanic {

    private AnimatorSet animatorSet;
    private Animator.AnimatorListener animatorListener;

    public Animator.AnimatorListener getAnimatorListener() {
        return animatorListener;
    }

    public void setAnimatorListener(Animator.AnimatorListener animatorListener) {
        this.animatorListener = animatorListener;
    }

    public void startView(final WaveBackGroundVIew view){
        final Runnable animate = new Runnable() {

            @Override
            public void run() {


                System.out.println("run开始运行！！！！！！！！！！！");
                view.setSinking(true);

                // horizontal animation. 200 = wave.png width
                ObjectAnimator maskXAnimator = ObjectAnimator.ofFloat(view, "maskX", 0, 1500);
                maskXAnimator.setRepeatCount(ValueAnimator.INFINITE);
                //波动速度
                maskXAnimator.setDuration(2000);
                maskXAnimator.setStartDelay(0);

                int h = view.getHeight();

                // vertical animation
                // maskY = 0 -> wave vertically centered
                // repeat mode REVERSE to go back and forth
//                ObjectAnimator maskYAnimator = ObjectAnimator.ofFloat(view, "maskY", h, - h);
//                maskYAnimator.setRepeatCount(ValueAnimator.INFINITE);
//                maskYAnimator.setRepeatMode(ValueAnimator.REVERSE);
//                //上升速度
//                maskYAnimator.setDuration(3500);
//                maskYAnimator.setStartDelay(0);

                // now play both animations together
                animatorSet = new AnimatorSet();
//                animatorSet.playTogether(maskXAnimator, maskYAnimator);
                animatorSet.play(maskXAnimator);
                animatorSet.setInterpolator(new LinearInterpolator(){
                    @Override
                    public float getInterpolation(float input) {
//                        System.out.println("getInterpolation::::"+input);
                        return super.getInterpolation(input);
                    }
                });
                animatorSet.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        System.out.println("onAnimationStart");
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        System.out.println("end");
                        view.setSinking(false);

                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                            view.postInvalidate();
                        } else {
                            view.postInvalidateOnAnimation();
                        }

                        animatorSet = null;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        System.out.println("eonAnimationCancelnd");
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                        System.out.println("enonAnimationRepeatd");
                    }
                });


                if (animatorListener != null) {
                    animatorSet.addListener(animatorListener);
                }
                System.out.println("start");
                animatorSet.start();
                System.out.println("start");
                System.out.println("maskX::::"+view.getMaskX());
                System.out.println("maskY::::"+view.getMaskY());

            }
        };
//        animate.run();

        if (!view.isSetUp()) {
            view.setAnimationSetupCallback(new WaveBackGroundVIew.AnimationSetupCallback() {
                @Override
                public void onSetupAnimation(WaveBackGroundVIew waveBackGroundVIew) {
                    animate.run();
                }
            });
//            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
//                view.postInvalidate();
//            } else {
//                view.postInvalidateOnAnimation();
//            }
        } else {
            animate.run();
        }
    }

    public void start(final TitanicTextView textView) {

        final Runnable animate = new Runnable() {
            @Override
            public void run() {

                textView.setSinking(true);

                // horizontal animation. 200 = wave.png width
                ObjectAnimator maskXAnimator = ObjectAnimator.ofFloat(textView, "maskX", 0, 1500);
                maskXAnimator.setRepeatCount(ValueAnimator.INFINITE);
                //波动速度
                maskXAnimator.setDuration(1000);
                maskXAnimator.setStartDelay(0);

                int h = textView.getHeight();

                // vertical animation
                // maskY = 0 -> wave vertically centered
                // repeat mode REVERSE to go back and forth
                ObjectAnimator maskYAnimator = ObjectAnimator.ofFloat(textView, "maskY", h, - h);
                maskYAnimator.setRepeatCount(ValueAnimator.INFINITE);
                maskYAnimator.setRepeatMode(ValueAnimator.REVERSE);
                //上升速度
                maskYAnimator.setDuration(3500);
                maskYAnimator.setStartDelay(0);

                // now play both animations together
                animatorSet = new AnimatorSet();
                animatorSet.playTogether(maskXAnimator, maskYAnimator);
//                textView.setMaskY(50);
//                animatorSet.play(maskXAnimator);
                animatorSet.setInterpolator(new LinearInterpolator());
                animatorSet.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        textView.setSinking(false);

                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                            textView.postInvalidate();
                        } else {
                            textView.postInvalidateOnAnimation();
                        }

                        animatorSet = null;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });


                if (animatorListener != null) {
                    animatorSet.addListener(animatorListener);
                }

                animatorSet.start();
            }
        };



        if (!textView.isSetUp()) {
            textView.setAnimationSetupCallback(new TitanicTextView.AnimationSetupCallback() {
                @Override
                public void onSetupAnimation(final TitanicTextView target) {
                    animate.run();
                }
            });
        } else {
            animate.run();
        }
    }

    public void cancel() {
        if (animatorSet != null) {
            animatorSet.cancel();
        }
    }
}
