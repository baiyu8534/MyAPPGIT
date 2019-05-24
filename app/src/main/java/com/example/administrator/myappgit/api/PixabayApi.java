package com.example.administrator.myappgit.api;

import com.example.administrator.myappgit.bean.PixadayBean.PixabayListBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/18 0018.
 * pixabay获取免费照片
 * https://pixabay.com/api/
 * ?key=5927878-93b96f8853619c00eff382b7f 注册的用户才有key
 * &q=scenery   搜索条件 可改中文搜索
 * &image_type=photo  图片类型
 * &orientation=horizontal  图片方向
 * &order=latest  获取最新的
 * &per_page=4  获取数量
 * page 页数
 */

public interface PixabayApi {

    /**
     * 风景
     */
    String PIXABAY_QUARY_TAG_SCENERY = "风景";

    // FIXME: 2017/9/12 0012 证书就是坑爹的，加载了证书也不行

    /**
     * 获取最新的查询条件的图片
     *
     * @return
     */
    @GET("?key=5927878-93b96f8853619c00eff382b7f&image_type=photo&orientation=horizontal" +
            "&order=latest&lang=zh}")
    Observable<PixabayListBean> getImageList(@Query("per_page") int count, @Query("q") String q);

}
