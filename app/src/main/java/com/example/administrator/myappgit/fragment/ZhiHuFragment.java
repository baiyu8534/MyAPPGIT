package com.example.administrator.myappgit.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import com.example.administrator.myappgit.IView.IZhiHuFragment;
import com.example.administrator.myappgit.R;
import com.example.administrator.myappgit.adapter.RvZhiHuFragmentAdapter;
import com.example.administrator.myappgit.bean.ZhiHuBean.NewsListBean;
import com.example.administrator.myappgit.presenter.implPresenter.ZhiHuFragmentPresenterImpl;
import com.example.administrator.myappgit.ui.ShowRvItemDecoration;
import com.example.administrator.myappgit.ui.WhorlView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/7/14 0014.
 */

public class ZhiHuFragment extends Fragment implements IZhiHuFragment {


    @BindView(R.id.rv_show_list)
    RecyclerView mRvShowList;
    @BindView(R.id.vs_no_connection)
    ViewStub mVsNoConnection;
    Unbinder unbinder;
    @BindView(R.id.wv_dialog)
    WhorlView mWvDialog;
    private Context mContext;
    private RvZhiHuFragmentAdapter mRvZhiHuFragmentAdapter;

    /**
     * 当前加载的新闻的日期
     */
    private String currentLoadDate;

    public ZhiHuFragment() {
    }

    public static ZhiHuFragment getInstance() {
        return new ZhiHuFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zhuhu_layout, container, false);
        mContext = getContext();
        unbinder = ButterKnife.bind(this, view);
        initView();
        initViewListener();

        ZhiHuFragmentPresenterImpl zhiHuFragmentPresenter = new ZhiHuFragmentPresenterImpl(getContext(), this);
        zhiHuFragmentPresenter.getNewsList();

        return view;
    }

    private void initViewListener() {

    }

    private void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRvShowList.setLayoutManager(linearLayoutManager);
        mRvShowList.setHasFixedSize(true);
        mRvShowList.addItemDecoration(new ShowRvItemDecoration(mContext));
    }


    @Override
    public void upDataNewsList(List<NewsListBean.StoriesBean> newsStoriesList) {
        mRvZhiHuFragmentAdapter = new RvZhiHuFragmentAdapter(getContext(), newsStoriesList);
        mRvShowList.setAdapter(mRvZhiHuFragmentAdapter);
    }

    @Override
    public void hidProgressDialog() {
        mWvDialog.setVisibility(View.GONE);
        mWvDialog.stop();
    }

    @Override
    public void showProgressDialog() {
        mWvDialog.setVisibility(View.VISIBLE);
        mWvDialog.start();
    }

    @Override
    public void showErrorMessage() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void loadData(){

    }

    public void loadMoreData(){

    }
}
