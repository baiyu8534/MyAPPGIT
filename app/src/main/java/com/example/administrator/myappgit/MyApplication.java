package com.example.administrator.myappgit;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;

import com.example.administrator.myappgit.activity.BaseActivity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/12 0012.
 */

public class MyApplication extends Application {

    private static MyApplication myApplication;
    private static int mainTid;

    private static List<BaseActivity> activitys;

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

    public static Context getContext() {
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

    public static void finishApp(){
        finishAllActivity();
        System.exit(0);
    }

    //提交成功

}
