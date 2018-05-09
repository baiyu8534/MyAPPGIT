package com.example.administrator.myappgit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.myappgit.IView.IZhiHuFragment;
import com.example.administrator.myappgit.R;
import com.example.administrator.myappgit.adapter.RvZhiHuFragmentAdapter;
import com.example.administrator.myappgit.app.AppConstant;
import com.example.administrator.myappgit.bean.ZhiHuBean.NewsListBean;
import com.example.administrator.myappgit.presenter.implPresenter.ZhiHuFragmentPresenterImpl;
import com.example.administrator.myappgit.ui.ShowRvItemDecoration;
import com.example.administrator.myappgit.ui.WhorlView;
import com.example.administrator.myappgit.utils.UIUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/7/14 0014.
 */

public class ZhiHuFragment extends BaseFragment implements IZhiHuFragment {


    @BindView(R.id.rv_show_list)
    RecyclerView mRvShowList;
    Unbinder unbinder;
    @BindView(R.id.wv_dialog)
    WhorlView mWvDialog;
    @BindView(R.id.iv_network_error)
    ImageView mIvNetworkError;
    @BindView(R.id.tv_network_error)
    TextView mTvNetworkError;
    @BindView(R.id.tv_network_error_button)
    TextView mTvNetworkErrorButton;
    @BindView(R.id.rl_network_error)
    RelativeLayout mRlNetworkError;
    private RvZhiHuFragmentAdapter mRvZhiHuFragmentAdapter;

    /**
     * 当前加载的新闻的日期
     */
    private String currentLoadDate;

    private boolean loading;

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
        View view = inflater.inflate(R.layout.fragment_zhuhu_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();

        mZhiHuFragmentPresenter = new ZhiHuFragmentPresenterImpl(getContext(), this);
        loadData();
        return view;
    }

    private void loadData() {
        //有没有网都让他去请求
        //有网 ok
        //没网，但有缓存，就可以加载缓存 ok
        //没网，没缓存，框架显示错误信息
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
                if (dy >= 0) {
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

        mTvNetworkErrorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRlNetworkError.setVisibility(View.GONE);
                loadData();
            }
        });

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
        //有没有网都让他刷新
        //有网 ok
        //没网，但有缓存，就可以加载缓存 ok
        //没网，没缓存，框架显示错误信息
        mZhiHuFragmentPresenter.getMoreNews(currentLoadDate);
    }

    @Override
    public void showNetworkRequestErrorMessage(String message) {
        //第一次加载，要么有网加载数据，要么没网加载缓存，出错就是加载不出来
        //根据message显示不同UI，第一次未必是没网
        if (Integer.parseInt(currentLoadDate) == 0) {
            Log.d("ZhiHuFragment", "显示没网UI");
            mRlNetworkError.setVisibility(View.VISIBLE);
        } else {
            UIUtil.showMessageDialog(getActivity(), message, AppConstant.ICON_TYPE_FAIL);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mZhiHuFragmentPresenter.unsubscribe();
    }

    public void setLoadMoreRefreshState() {
        if (loading) {
            loading = false;
        }
        if (mRlNetworkError.getVisibility() == View.VISIBLE) {
            mRlNetworkError.setVisibility(View.GONE);
            loadData();
        }
    }
}
