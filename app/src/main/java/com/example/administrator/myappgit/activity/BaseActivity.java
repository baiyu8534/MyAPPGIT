package com.example.administrator.myappgit.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.myappgit.MyApplication;
import com.example.administrator.myappgit.ui.TopFloatHintDialog;

/**
 * Created by Administrator on 2017/7/12 0012.
 */

public class BaseActivity extends AppCompatActivity {

    protected final String TAG = getClass().getSimpleName();
    public static BaseActivity activity;
    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MyApplication) MyApplication.getContext()).addActivity(this);
        activity = this;
        mContext = this;
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
    }

}
