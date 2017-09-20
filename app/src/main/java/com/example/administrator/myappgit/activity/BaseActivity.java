package com.example.administrator.myappgit.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.myappgit.MyApplication;
import com.example.administrator.myappgit.R;
import com.example.administrator.myappgit.app.AppConstant;
import com.example.administrator.myappgit.ui.TopFloatHintDialog;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2017/7/12 0012.
 */

public class BaseActivity extends AppCompatActivity {

    protected final String TAG = getClass().getSimpleName();
    public static BaseActivity activity;
    protected Context mContext;

    public BaseActivityHandler mBaseActivityHandler;

    private static class BaseActivityHandler extends Handler {
        private WeakReference<BaseActivity> activityWeakReference;

        public BaseActivityHandler(BaseActivity activity) {
            activityWeakReference = new WeakReference<BaseActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            BaseActivity activity = activityWeakReference.get();
            if (activity != null) {
                switch (msg.what) {
                    case AppConstant.HANDLER_WHAT_NETWORK_ERROR:
                        activity.showMessageDialog(activity.getString(R.string.error_message_network_connections_break),
                                AppConstant.ICON_TYPE_FAIL);
                        break;
                }
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MyApplication) MyApplication.getContext()).addActivity(this);
        activity = this;
        mContext = this;
        ((MyApplication) getApplication()).setCurrentActivity(this);
        mBaseActivityHandler = new BaseActivityHandler(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        activity = this;
    }

    @Override
    protected void onPause() {
        super.onPause();
        activity = null;
    }

    @Override
    protected void onStop() {
        super.onStop();
        ((MyApplication) MyApplication.getContext()).setCurrentActivity(null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((MyApplication) MyApplication.getContext()).removeActivity(this);
    }

    /**
     * 统一的提示信息dialog
     *
     * @param message
     * @param iconType
     */
    protected void showMessageDialog(String message, int iconType) {
        final TopFloatHintDialog topFloatHintDialog = new TopFloatHintDialog.Builder(mContext)
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
                runOnUiThread(new Runnable() {
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
