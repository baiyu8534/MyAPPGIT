package com.example.administrator.myappgit.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * 文件名：BaseService
 * 描述：主要负责一些APP状态的监听
 * 作者：白煜
 * 时间：2017/9/20 0020
 * 版权：
 */

public class BaseService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
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
}
