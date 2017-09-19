package com.example.administrator.myappgit.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.administrator.myappgit.ui.TopFloatHintDialog;

/**
 * Created by baiyu on 2017/9/7.
 */

public class BaseFragment extends Fragment {

    protected Context mContext;
    protected Activity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        mActivity = getActivity();
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
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        topFloatHintDialog.dismiss();
                    }
                });
            }
        }).start();
    }
}
