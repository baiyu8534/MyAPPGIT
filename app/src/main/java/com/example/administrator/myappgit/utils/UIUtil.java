package com.example.administrator.myappgit.utils;

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myappgit.R;
import com.example.administrator.myappgit.ui.TopFloatHintDialog;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

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
        snackShort(view, message, null, null);
    }

    public static void snackShort(View view, String message, String action, View.OnClickListener listener) {
        final Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
        if (!TextUtils.isEmpty(action) && listener == null) {
            snackbar.setAction(action, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    snackbar.dismiss();
                }
            });
        } else if ((!TextUtils.isEmpty(action) && listener != null)) {
            snackbar.setAction(action, listener);
        }
        snackbar.show();
    }

    //snackbar 只能显示一个按钮（官方建议） 想显示两个要自定义
//    public static void snackShort(View view, String message, String action1, View.OnClickListener action1listener
//            , String action2, View.OnClickListener action2listener) {
//        final Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
//        if (!TextUtils.isEmpty(action1) && action1listener == null) {
//            snackbar.setAction(action1, new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    snackbar.dismiss();
//                }
//            });
//        } else if ((!TextUtils.isEmpty(action1) && action1listener != null)) {
//            snackbar.setAction(action1, action1listener);
//        }
//
//        if (!TextUtils.isEmpty(action2) && action2listener == null) {
//            snackbar.setAction(action2, new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    snackbar.dismiss();
//                }
//            });
//        } else if (!TextUtils.isEmpty(action2) && action2listener != null) {
//            snackbar.setAction(action2, action2listener);
//        }
//        snackbar.show();
//    }

    public static void snackLong(View view, String message) {
        snackLong(view, message, null, null);
    }

    public static void snackLong(View view, String message, String action, View.OnClickListener listener) {
        final Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        if (!TextUtils.isEmpty(action) && listener == null) {
            snackbar.setAction(action, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    snackbar.dismiss();
                }
            });
        } else if (!TextUtils.isEmpty(action) && listener != null) {
            snackbar.setAction(action, listener);
        }
        snackbar.show();
    }


    /**
     * 网络断开时 snackBar 提示用户，让用户去选择是否去设置网络
     *
     * @param view
     * @param message
     */
    public static void snackNewWorkErrorMessage(final View view, String message) {
        UIUtil.snackLong(view, message,
                view.getContext().getString(R.string.setting),
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 跳转到系统的网络设置界面
                        Intent intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                        if ((view.getContext() instanceof Application)) {
                            intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                        }
                        view.getContext().startActivity(intent);
                    }
                });
    }

    /**
     * 统一的提示信息dialog
     *
     * @param message
     * @param iconType
     */
    public static void showMessageDialog(final Activity context, String message, int iconType) {
        final TopFloatHintDialog topFloatHintDialog = new TopFloatHintDialog.Builder(context)
                .setIconType(iconType)
                .setMessage(message)
                .create();
        topFloatHintDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        topFloatHintDialog.dismiss();
                    }
                });
            }
        }).start();
        //若我们 使用的Context不是Activity 的Context 而是Application的 Context，我们 需要做以下处理 ，否则会报错
        // 设置为系统级别的Dialog
        /*
        mWifiDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        if ((context instanceof Application)) {
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
        在AndroidMainFest中添加以下权限 。
        <!--允许 弹出系统级别的AlterDialog-->
        <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW"/>*/

    }
}
