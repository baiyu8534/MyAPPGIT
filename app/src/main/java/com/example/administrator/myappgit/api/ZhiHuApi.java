package com.example.administrator.myappgit.api;

import com.example.administrator.myappgit.bean.ZhiHuBean.NewsContent;
import com.example.administrator.myappgit.bean.ZhiHuBean.NewsListBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/12 0012.
 */

public interface ZhiHuApi {

    @GET("api/4/news/latest")
    Observable<NewsListBean> getNewsList();

    @GET("api/4/news/{id}")
    Observable<NewsContent> getNewContent(@Path("id") String id);

}
