package com.example.administrator.myappgit;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatDelegate;

import com.example.administrator.myappgit.activity.BaseActivity;
import com.example.administrator.myappgit.service.BaseService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/12 0012.
 */

public class MyApplication extends Application {

    private static MyApplication myApplication;
    private static int mainTid;

    // FIXME: 2017/9/20 0020 等加了在service中加了网络状态的动态监听，就可以动态的设置，在做网络请求时去判断是否做出相应的提示
    /**
     * 当前网络为wifi
     */
    private boolean isWifi;
    /**
     * 当前网络为移动数据
     */
    private boolean isMobile;
    /**
     * 当前网络是否连接
     */
    private boolean isConnected;

    private static List<BaseActivity> activitys;
    /**
     * 当前正在显示的activity
     */
    private BaseActivity currentActivity;

    static {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        activitys = new LinkedList<>();
        mainTid = android.os.Process.myTid();
    }

    public boolean isWifi() {
        return isWifi;
    }

    public boolean isMobile() {
        return isMobile;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setWifi(boolean wifi) {
        isWifi = wifi;
    }

    public void setMobile(boolean mobile) {
        isMobile = mobile;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    public static Context getContext() {
        return myApplication;
    }

    public static MyApplication getInstance() {
        return myApplication;
    }

    public static int getMainTid() {
        return mainTid;
    }

    public void addActivity(BaseActivity activity) {
        activitys.add(activity);
    }

    public void removeActivity(BaseActivity activity) {
        activitys.remove(activity);
    }

    public static void finishAllActivity() {
        for (BaseActivity activity : activitys) {
            if (activity != null) {
                activity.finish();
            }
        }
    }

    public void finishApp() {
        ActivityManager myManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        ArrayList<ActivityManager.RunningServiceInfo> runningService = (ArrayList<ActivityManager.RunningServiceInfo>)
                myManager.getRunningServices(30);
        for (int i = 0; i < runningService.size(); i++) {
            //判断启动的service有没有baseService
            if (runningService.get(i).service.getClassName().toString().equals("com.example.administrator.myappgit.service" +
                    ".BaseService")) {
                stopService(new Intent(this, BaseService.class));
            }
        }

        finishAllActivity();
        System.exit(0);
    }

    public BaseActivity getCurrentActivity() {
        return currentActivity;
    }

    public void setCurrentActivity(BaseActivity currentActivity) {
        this.currentActivity = currentActivity;
    }
}
