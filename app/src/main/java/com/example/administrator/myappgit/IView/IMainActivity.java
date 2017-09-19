package com.example.administrator.myappgit.IView;

import com.example.administrator.myappgit.bean.PixadayBean.PixabayListBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/18 0018.
 */

public interface IMainActivity extends IBaseView {
    /**
     * 设置图片
     *
     * @param hitsBeen
     */
    void setPic(List<PixabayListBean.HitsBean> hitsBeen);
}
