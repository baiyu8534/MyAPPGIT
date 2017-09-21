package com.example.administrator.myappgit.IView;

import com.example.administrator.myappgit.bean.ZhiHuBean.NewsListBean;

/**
 * Created by Administrator on 2017/7/19 0019.
 */

public interface IZhiHuFragment extends IBaseView {
    /**
     * 显示newsList信息
     *
     * @param newsList
     */
    void upDataNewsList(NewsListBean newsList);

    /**
     * 隐藏加载控件
     */
    void hidProgressDialog();

    /**
     * 显示加载控件
     */
    void showProgressDialog();

    /**
     * 显示网络请求出错的信息
     *
     * @param message
     */
    void showNetworkRequestErrorMessage(String message);

}
