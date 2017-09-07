package com.example.administrator.myappgit.IView;

import com.example.administrator.myappgit.bean.ZhiHuBean.NewsListBean;

/**
 * Created by Administrator on 2017/7/19 0019.
 */

public interface IZhiHuFragment {
    void upDataNewsList(NewsListBean newsList);

    void hidProgressDialog();

    void showProgressDialog();

    void showErrorMessage();

}
