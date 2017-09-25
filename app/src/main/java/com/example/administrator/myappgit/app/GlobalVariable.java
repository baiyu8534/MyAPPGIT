package com.example.administrator.myappgit.app;

import com.example.administrator.myappgit.activity.BaseActivity;

/**
 * 文件名：GlobalVariable
 * 描述：全局静态变量（公共静态变量不建议放application，所以就放这）
 * 作者：白煜
 * 时间：2017/9/22 0022
 * 版权：
 */

public class GlobalVariable {
    /**
     * 当前正在显示的activity
     * 可以放到Application中，弄一个静态的MyApplication，MyApplication.getinstance().currentActivity;
     */
    public static BaseActivity currentActivity = null;
}
