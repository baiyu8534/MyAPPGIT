package com.example.administrator.myappgit;

/**
 * Created by Administrator on 2017/7/10 0010.
 * view基类
 */

public interface BaseView<T> {

    /**
     * 给view设置presenter
     * @param presenter
     */
    void setPresenter(T presenter);

    /**
     * 初始化页面上的控件
     */
    void initView();
}
