package com.example.administrator.myappgit.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myappgit.R;

/**
 * UI相关的工具
 * Created by Administrator on 2017/7/17 0017.
 */

public class UIUtil {
    /**
     * 获取TextView宽度
     *
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
     *
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
     *
     * @param context
     * @param colorResId
     * @return
     */
    public static int colorResId2ColorInt(Context context, int colorResId) {
        return context.getResources().getColor(colorResId);
    }

    /**
     * 设置状态栏颜色
     *
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
     *
     * @param context
     * @return
     */
    public static int getActionSize(Context context) {

        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, typedValue, true))
            return TypedValue.complexToDimensionPixelSize(typedValue.data, context.getResources().getDisplayMetrics());
        return 0;

    }

    /**
     * bitmap转drawable
     *
     * @param context
     * @param bitmap
     * @return
     */
    public static Drawable Bitmap2Drawable(Context context, Bitmap bitmap) {
        return new BitmapDrawable(context.getResources(), bitmap);
    }

    /**
     * drawable转bitmap
     *
     * @param drawable
     * @return
     */
    public static Bitmap Drawable2Bitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), drawable.getOpacity()
                != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * 单位转换: dp -> px
     *
     * @param dp
     * @return
     */
    public static int dp2px(Context context, int dp) {
        return (int) (getDensity(context) * dp + 0.5);
    }

    /**
     * 单位转换:px -> dp
     *
     * @param px
     * @return
     */
    public static int px2dp(Context context, int px) {
        return (int) (px / getDensity(context) + 0.5);
    }

    /**
     * 屏幕密度
     */
    public static float sDensity = 0f;

    public static float getDensity(Context context) {
        if (sDensity == 0f) {
            sDensity = getDisplayMetrics(context).density;
        }
        return sDensity;
    }

    /**
     * DisplayMetrics
     *
     * @return
     */
    public static DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static void toastShort(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void toastLong(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void snackShort(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
    }

    public static void snackShort(View view, String message, View.OnClickListener listener) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
                .setAction(view.getContext().getString(R.string.confirm), listener)
                .show();
    }

    public static void snackLong(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }

    public static void snackLong(View view, String message, View.OnClickListener listener) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                .setAction(view.getContext().getString(R.string.confirm), listener)
                .show();
    }
}
