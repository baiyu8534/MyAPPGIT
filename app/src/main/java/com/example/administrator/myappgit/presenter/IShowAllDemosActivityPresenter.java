package com.example.administrator.myappgit.presenter;

/**
 * Created by baiyu on 2017/9/15.
 */

public interface IShowAllDemosActivityPresenter extends BasePresenter {
    /**
     * 获取图片
     *
     * @param count 数量
     * @param page  页
     */
    void getImages(String count, String page);
}
