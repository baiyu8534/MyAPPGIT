package com.example.administrator.myappgit.IView;

import com.example.administrator.myappgit.bean.ZhiHuBean.ZhiHuNewDetail;

/**
 * Created by baiyu on 2017/9/8.
 */

public interface IZhiHuDetailActivity {
    void showNewsDetail(ZhiHuNewDetail detail);

    void showProgressDialog();

    void hidProgressDialog();

    void showErrorMessage(String message);
}
