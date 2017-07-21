package com.example.administrator.myappgit.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.TypedValue;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * UI相关的工具
 * Created by Administrator on 2017/7/17 0017.
 */

public class UIUtil {
    /**
     * 获取TextView宽度
     * @param textView
     * @return
     */
    public static int getTextWidth(TextView textView) {
        if (textView == null) {
            return 0;
        }
        Rect bounds = new Rect();
        String text = textView.getText().toString();
        Paint paint = textView.getPaint();
        paint.getTextBounds(text, 0, text.length(), bounds);
        int width = bounds.left + bounds.width();
        return width;
    }

    /**
     * 设置状态栏颜色
     * @param activity
     * @param colorInt
     */
    public static void setWindowStatusBarColor(Activity activity, int colorInt) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = activity.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(colorInt);

                //底部导航栏
                //window.setNavigationBarColor(activity.getResources().getColor(colorResId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * colorResId转化为Color的int值
     * @param context
     * @param colorResId
     * @return
     */
    public static int colorResId2ColorInt(Context context , int colorResId){
        return context.getResources().getColor(colorResId);
    }

    /**
     * 设置状态栏颜色
     * @param dialog
     * @param colorInt
     */
    public static void setWindowStatusBarColor(Dialog dialog, int colorInt) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = dialog.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(colorInt);

                //底部导航栏
                //window.setNavigationBarColor(activity.getResources().getColor(colorResId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取actionBarSize 像素值
     * @param context
     * @return
     */
    public static int getActionSize(Context context) {

        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, typedValue, true))
            return TypedValue.complexToDimensionPixelSize(typedValue.data, context.getResources().getDisplayMetrics());
        return 0;

    }
}
