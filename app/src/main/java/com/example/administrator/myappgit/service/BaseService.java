package com.example.administrator.myappgit.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.administrator.myappgit.MyApplication;
import com.example.administrator.myappgit.app.AppConstant;
import com.example.administrator.myappgit.app.GlobalVariable;
import com.example.administrator.myappgit.utils.NetWorkUtil;

import static android.content.ContentValues.TAG;

/**
 * 文件名：BaseService
 * 描述：主要负责一些APP状态的监听
 * 作者：白煜
 * 时间：2017/9/20 0020
 * 版权：
 */

public class BaseService extends Service {

    private Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        // 注册广播
        IntentFilter mFilter = new IntentFilter();
        // 添加接收网络连接状态改变的Action
        mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mReceiver, mFilter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                if (NetWorkUtil.isWifiConnected(mContext)) {
                    MyApplication.getInstance().setWifi(true);
                    MyApplication.getInstance().setConnected(true);
                    Log.e(TAG, "当前WiFi连接可用 ");
                }
                if (NetWorkUtil.isMobileConnected(mContext)) {
                    MyApplication.getInstance().setMobile(true);
                    MyApplication.getInstance().setConnected(true);
                    Log.e(TAG, "当前移动网络连接可用 ");
                }
                if (!NetWorkUtil.isNetWorkAvailable(mContext)) {
                    Log.e(TAG, "当前没有网络连接，请确保你已经打开网络 ");
                    if(GlobalVariable.currentActivity.mBaseActivityHandler != null) {
                        GlobalVariable.currentActivity.mBaseActivityHandler.sendEmptyMessage(AppConstant.HANDLER_WHAT_NETWORK_CONN_FAIL);
                    }
                    MyApplication.getInstance().setWifi(false);
                    MyApplication.getInstance().setMobile(false);
                    MyApplication.getInstance().setConnected(false);
                }
            }
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }
}
