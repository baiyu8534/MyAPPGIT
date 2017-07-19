package com.example.administrator.myappgit.IView;

import com.example.administrator.myappgit.bean.ZhiHuBean.NewsListBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/19 0019.
 */

public interface IZhiHuFragment {
    void showDataList(List<NewsListBean.StoriesBean> newsStoriesList);
}
