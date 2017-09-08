package com.example.administrator.myappgit.api;

import com.example.administrator.myappgit.bean.ZhiHuBean.NewsListBean;
import com.example.administrator.myappgit.bean.ZhiHuBean.ZhiHuNewDetail;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/12 0012.
 */

public interface ZhiHuApi {

    @GET("api/4/news/latest")
    Observable<NewsListBean> getNews();

    @GET("/api/4/news/before/{date}")
    Observable<NewsListBean> getMoreNews(@Path("date") String date);

    @GET("api/4/news/{id}")
    Observable<ZhiHuNewDetail> getNewsDetail(@Path("id") String id);

}
