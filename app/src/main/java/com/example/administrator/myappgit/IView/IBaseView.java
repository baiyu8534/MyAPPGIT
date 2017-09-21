package com.example.administrator.myappgit.IView;

/**
 * 文件名：IBaseView
 * 描述：
 * 作者：白煜
 * 时间：2017/9/19 0019
 * 版权：
 */

public interface IBaseView {
    /**
     * 网络请求错误时显示错误信息
     *
     * @param message
     */
    void showNetworkRequestErrorMessage(String message);
}
