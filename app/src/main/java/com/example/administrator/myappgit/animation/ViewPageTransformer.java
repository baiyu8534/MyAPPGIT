package com.example.administrator.myappgit.animation;

import android.os.Build;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by baiyu on 2017/4/25.
 * viewpage的一个滑动时的动画
 */
public class ViewPageTransformer implements ViewPager.PageTransformer {
    public static final float MAX_SCALE = 1;
    public static final float MIN_SCALE = 1;
    @Override
    public void transformPage(View page, float position) {

//        page.setScaleX(0.8f);
//        page.setScaleY(0.8f);
//        page.setAlpha(0.8f);
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
//            page.getParent().requestLayout();
//        }
        position = position < -1 ? -1 : position;
        position = position > 1 ? 1 : position;

        float tempScale = position < 0 ? 1 + position : 1 - position;

        float slope = (0.1f) / 1;
        float scaleValue = MIN_SCALE + tempScale * slope;
        page.setScaleX(scaleValue);
        page.setScaleY(scaleValue);
        page.setAlpha(scaleValue);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            page.getParent().requestLayout();
        }
    }
}
