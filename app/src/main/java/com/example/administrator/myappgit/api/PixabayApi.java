package com.example.administrator.myappgit.api;

import com.example.administrator.myappgit.bean.PixadayBean.PixabayListBean;

import retrofit2.http.GET;
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
 */

public interface PixabayApi {

    /**
     * 获取5张最新的横向风景图片
     * @return
     */
    @GET("?key=5927878-93b96f8853619c00eff382b7f&q=scenery&image_type=photo&orientation=horizontal" +
            "&order=latest&per_page=5")
    Observable<PixabayListBean> getImageList();

}
