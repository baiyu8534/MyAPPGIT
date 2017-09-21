package com.example.administrator.myappgit.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.myappgit.MyApplication;
import com.example.administrator.myappgit.app.AppConstant;

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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((MyApplication) MyApplication.getContext()).removeActivity(this);
    }

    /**
     * 需要监听网络断开的activity复写这个方法即可
     */
    protected void noNetworkConnFail() {
    }

    /**
     * 需要监听网络回复的activity复写这个方法即可
     */
    protected void noNetworkConnSuccess() {
    }


}
