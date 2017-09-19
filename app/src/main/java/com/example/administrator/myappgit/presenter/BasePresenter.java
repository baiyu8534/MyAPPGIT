package com.example.administrator.myappgit.presenter;

import com.example.administrator.myappgit.IView.IBaseView;

/**
 * Created by Administrator on 2017/7/18 0018.
 */

public interface BasePresenter {
    /**
     * 解除订阅
     */
    void unsubscribe();

    /**
     * 统一处理网络请求出错时的错误信息
     *
     * @param e
     * @param iView
     */
    void informShowErrorMessage(Throwable e, IBaseView iView);

    /**
     * 统一处理网络请求出错时的错误信息
     *
     * @param message 除了网络状态和网络请求错误的其他错误信息
     * @param iView
     */
    void informShowErrorMessage(String message, IBaseView iView);
}
