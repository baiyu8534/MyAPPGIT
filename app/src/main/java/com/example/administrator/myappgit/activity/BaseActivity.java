package com.example.administrator.myappgit.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.myappgit.MyApplication;
import com.example.administrator.myappgit.app.AppConstant;
import com.example.administrator.myappgit.app.GlobalVariable;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2017/7/12 0012.
 */

public class BaseActivity extends AppCompatActivity {

    protected final String TAG = getClass().getSimpleName();
    public static BaseActivity activity;
    protected Context mContext;

    public BaseActivityHandler mBaseActivityHandler;

    public static class BaseActivityHandler extends Handler {
        private WeakReference<BaseActivity> activityWeakReference;

        public BaseActivityHandler(BaseActivity activity) {
            activityWeakReference = new WeakReference<BaseActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            BaseActivity activity = activityWeakReference.get();
            if (activity != null) {
                switch (msg.what) {
                    case AppConstant.HANDLER_WHAT_NETWORK_CONN_FAIL:
                        activity.noNetworkConnFail();
                        break;
                    case AppConstant.HANDLER_WHAT_NETWORK_CONN_SUCCESS:
                        activity.noNetworkConnSuccess();
                        break;
                }
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getInstance().addActivity(this);
        activity = this;
        mContext = this;
        mBaseActivityHandler = new BaseActivityHandler(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        activity = this;
        GlobalVariable.currentActivity = this;
    }

    @Override
    protected void onPause() {
        super.onPause();
        activity = null;
        GlobalVariable.currentActivity = null;
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.getInstance().removeActivity(this);
    }

    /**
     * 需要监听网络断开的activity复写这个方法即可
     */
    protected void noNetworkConnFail() {
        //放不到这里，因为用snackBar，需要view。如果用其他提示方法就可以统一放这里，比如Dialog
        //用snackBar就导致了我得在所有activity中都加
        //虽然在这可以获取contentView，但是snackBar不是随便给个view就可以的。。。很烦。。
        //UIUtil.snackNewWorkErrorMessage(mRvShow, getString(R.string.alert_message_no_network_conn));
    }

    /**
     * 需要监听网络回复的activity复写这个方法即可
     */
    protected void noNetworkConnSuccess() {
    }

}
