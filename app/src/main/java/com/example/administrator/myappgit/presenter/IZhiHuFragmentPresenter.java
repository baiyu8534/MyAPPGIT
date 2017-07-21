package com.example.administrator.myappgit.presenter;

/**
 * Created by Administrator on 2017/7/19 0019.
 */

public interface IZhiHuFragmentPresenter extends BasePresenter {
    void getNewsList();

    void getMoreNews(String date);

    void getNewsFormCache();
}
