package com.example.administrator.myappgit.contract.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import com.example.administrator.myappgit.R;
import com.example.administrator.myappgit.bean.ZhiHuBean.NewsListBean;
import com.example.administrator.myappgit.contract.ZhiHuContract;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/7/14 0014.
 */

public class ZhiHuFragment extends Fragment implements ZhiHuContract.view{

    @BindView(R.id.rv_show_list)
    RecyclerView mRvShowList;
    @BindView(R.id.vs_no_connection)
    ViewStub mVsNoConnection;
    Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zhuhu_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void shouError() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showResult(ArrayList<NewsListBean.StoriesBean> reslutList) {

    }

    @Override
    public void setPresenter(ZhiHuContract.Presenter presenter) {

    }

    @Override
    public void initView() {

    }
}
