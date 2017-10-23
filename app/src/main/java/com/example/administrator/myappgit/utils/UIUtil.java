package com.example.administrator.myappgit.utils;

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myappgit.R;
import com.example.administrator.myappgit.app.AppConstant;
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

    /**
     * 第一次使用教学dialog
     *
     * @param message
     */
    public static void showFirstMessageDialog(final Activity context, String message) {
        final TopFloatHintDialog topFloatHintDialog = new TopFloatHintDialog.Builder(context)
                .setIconType(AppConstant.ICON_TYPE_INFO)
                .setMessage(message)
                .create();
        topFloatHintDialog.setCancelable(false);
        topFloatHintDialog.setCanceledOnTouchOutside(false);
        topFloatHintDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
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

    /**
     * 设置状态栏透明
     * @param activity
     */
    public static void setTranslucentStatusBar(Activity activity) {
        //用了这个。。toolBar的动画效果就迟钝了，会发生不会自动
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
//            View decorView = activity.getWindow().getDecorView();
//            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
//            decorView.setSystemUiVisibility(option);
//            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);

            Window window = activity.getWindow();
            //添加Flag把状态栏设为可绘制模式
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if (false) {
                //全透明，状态栏的颜色不会随着toolbar颜色的变化而变化
                //如果为全透明模式，取消设置Window半透明的Flag
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                //设置状态栏为透明
                window.setStatusBarColor(Color.TRANSPARENT);
                //设置window的状态栏不可见
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            } else {
                //如果为半透明模式，添加设置Window半透明的Flag
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                //设置系统状态栏处于可见状态
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
            }
            //view不根据系统窗口来调整自己的布局
            ViewGroup mContentView = (ViewGroup) window.findViewById(Window.ID_ANDROID_CONTENT);
            View mChildView = mContentView.getChildAt(0);
            if (mChildView != null) {
                ViewCompat.setFitsSystemWindows(mChildView, false);
                ViewCompat.requestApplyInsets(mChildView);
            }

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
//            WindowManager.LayoutParams localLayoutParams = activity.getWindow().getAttributes();
//            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);


            Window window = activity.getWindow();
            //设置Window为透明
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            ViewGroup mContentView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);
            View mContentChild = mContentView.getChildAt(0);

//            //移除已经存在假状态栏则,并且取消它的Margin间距
//            removeFakeStatusBarViewIfExist(activity);
//            removeMarginTopOfContentChild(mContentChild, getStatusBarHeight(activity));
            if (mContentChild != null) {
                //fitsSystemWindow 为 false, 不预留系统栏位置.
                ViewCompat.setFitsSystemWindows(mContentChild, false);
            }
        }
    }

    /**
     * 设置导航栏为透明色
     * @param activity
     */
    public static void setTranslucentNavigationBar(Activity activity){
        //设置导航栏为透明，但是要在xml中适当地方加android:fitsSystemWindows="true" android:clipToPadding="true"
        //否则UI会和状态栏重叠
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }

}
