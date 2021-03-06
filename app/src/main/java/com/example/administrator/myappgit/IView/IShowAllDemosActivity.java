package com.example.administrator.myappgit.IView;

import java.util.ArrayList;

/**
 * 文件名：IShowAllDemosActivity
 * 描述：
 * 作者：白煜
 * 时间：2017/9/15 0015
 * 版权：
 */

public interface IShowAllDemosActivity extends IBaseView{
    /**
     * 更新图片显示
     *
     * @param images
     */
    void upDataImages(ArrayList<String> images);

    /**
     * 显示错误信息
     *
     * @param message
     */
    void showNetworkRequestErrorMessage(String message);

    /**
     * 控制下拉刷新的显示
     *
     * @param refreshing
     */
    void setRefreshing(boolean refreshing);
}
