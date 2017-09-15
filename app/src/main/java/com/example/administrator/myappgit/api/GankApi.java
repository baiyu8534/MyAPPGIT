package com.example.administrator.myappgit.api;

import com.example.administrator.myappgit.bean.GankBean.GankImages;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * 文件名：GankApi
 * 描述：GankApi
 * 作者：白煜
 * 时间：2017/9/15 0015
 * 版权：
 */

public interface GankApi {

    /**
     * 获取图片
     *
     * @param count 数量
     * @param page  页
     * @return
     */
    @GET("/api/data/福利/{count}/{page}")
    Observable<GankImages> getImages(@Path("count") String count, @Path("page") String page);
}
