package com.example.administrator.myappgit.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.administrator.myappgit.IView.IShowAllDemosActivity;
import com.example.administrator.myappgit.bean.GankBean.GankImages;

/**
 * 文件名：ShowAllDemosActivity
 * 描述：launcher页显示所有的demos
 * 作者：白煜
 * 时间：2017/9/15 0015
 * 版权：
 */

public class ShowAllDemosActivity extends BaseActivity implements IShowAllDemosActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void upDataImages(GankImages images) {

    }
}
