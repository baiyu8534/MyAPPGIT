package com.example.administrator.myappgit.contract;

import com.example.administrator.myappgit.BasePresenter;
import com.example.administrator.myappgit.BaseView;
import com.example.administrator.myappgit.bean.ZhiHuBean.NewsListBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/7/12 0012.
 */

public interface ZhiHuContract {

    interface view extends BaseView<Presenter> {

        //显示错误信息
        void shouError();

        //显示加载框
        void showLoading();

        //停止加载
        void stopLoading();

        //显示获取结果
        void showResult(ArrayList<NewsListBean.StoriesBean> reslutList);
    }


    interface Presenter extends BasePresenter {

        //加载数据
        void loadData();

        //加载更多数据
        void loadMoreData();

        //刷新
        void refresh();

        //显示详情
        void showDetail();

        //随便看看
        void feelLook();
    }


}
