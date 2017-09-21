package com.example.administrator.myappgit.IView;

import com.example.administrator.myappgit.bean.ZhiHuBean.ZhiHuNewDetail;

/**
 * Created by baiyu on 2017/9/8.
 */

public interface IZhiHuDetailActivity extends IBaseView {
    /**
     * 显示detial信息
     *
     * @param detail
     */
    void showNewsDetail(ZhiHuNewDetail detail);

    /**
     * 显示加载控件
     */
    void showProgressDialog();

    /**
     * 隐藏加载控件
     */
    void hidProgressDialog();

    /**
     * 显示网络请求出错的信息
     *
     * @param message
     */
    void showNetworkRequestErrorMessage(String message);
}
