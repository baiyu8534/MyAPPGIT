package com.example.administrator.myappgit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import com.example.administrator.myappgit.IView.IZhiHuFragment;
import com.example.administrator.myappgit.R;
import com.example.administrator.myappgit.adapter.RvZhiHuFragmentAdapter;
import com.example.administrator.myappgit.app.AppConstant;
import com.example.administrator.myappgit.bean.ZhiHuBean.NewsListBean;
import com.example.administrator.myappgit.presenter.implPresenter.ZhiHuFragmentPresenterImpl;
import com.example.administrator.myappgit.ui.ShowRvItemDecoration;
import com.example.administrator.myappgit.ui.WhorlView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/7/14 0014.
 */

public class ZhiHuFragment extends BaseFragment implements IZhiHuFragment {


    @BindView(R.id.rv_show_list)
    RecyclerView mRvShowList;
    @BindView(R.id.vs_no_connection)
    ViewStub mVsNoConnection;
    Unbinder unbinder;
    @BindView(R.id.wv_dialog)
    WhorlView mWvDialog;
    private RvZhiHuFragmentAdapter mRvZhiHuFragmentAdapter;

    /**
     * 当前加载的新闻的日期
     */
    private String currentLoadDate;

    private boolean loading;

    // FIXME: 2017/9/6 要加网络是否通畅的判断和相应的操作
    private boolean networkIsOk = true;
    private ZhiHuFragmentPresenterImpl mZhiHuFragmentPresenter;
    private RecyclerView.OnScrollListener mOnScrollListener;
    private LinearLayoutManager mLinearLayoutManager;

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
        // FIXME: 2017/9/20 0020 没网时不显示列表，显示一个图标加一个按钮提示没网（暂定）
        View view = inflater.inflate(R.layout.fragment_zhuhu_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();

        mZhiHuFragmentPresenter = new ZhiHuFragmentPresenterImpl(getContext(), this);
        if (networkIsOk) {
            loadData();
        }

        return view;
    }

    private void loadData() {
        currentLoadDate = "0";
        mZhiHuFragmentPresenter.getNewsList();
    }

    private void initViewListener() {

        mOnScrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    //向下滚动
                    int totalItemCount = mLinearLayoutManager.getItemCount();
                    int visibleItemCount = mLinearLayoutManager.getChildCount();
                    int unvisibleItemCount = mLinearLayoutManager.findFirstVisibleItemPosition();
                    if (!loading && (visibleItemCount + unvisibleItemCount + 1 >= totalItemCount)) {
                        loading = true;
                        loadMoreData();
                    }
                }
            }
        };

    }

    private void initView() {

        initViewListener();

        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mRvShowList.setLayoutManager(mLinearLayoutManager);
        mRvShowList.setHasFixedSize(true);
        mRvShowList.addItemDecoration(new ShowRvItemDecoration(mContext));
        mRvShowList.addOnScrollListener(mOnScrollListener);
    }


    @Override
    public void upDataNewsList(NewsListBean newsList) {
        if (loading) {
            loading = false;
        }
        currentLoadDate = newsList.getDate();
        if (mRvZhiHuFragmentAdapter == null) {
            mRvZhiHuFragmentAdapter = new RvZhiHuFragmentAdapter(getContext(), newsList.getStories());
            mRvShowList.setAdapter(mRvZhiHuFragmentAdapter);
        } else {
            mRvZhiHuFragmentAdapter.addItems(newsList.getStories());
        }
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
    public void onDestroyView() {
        super.onDestroyView();
        mZhiHuFragmentPresenter.unsubscribe();
        unbinder.unbind();
    }


    public void loadMoreData() {
        mZhiHuFragmentPresenter.getMoreNews(currentLoadDate);
    }

    @Override
    public void showErrorMessage(String message) {
        showMessageDialog(message, AppConstant.ICON_TYPE_FAIL);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mZhiHuFragmentPresenter.unsubscribe();
    }
}
