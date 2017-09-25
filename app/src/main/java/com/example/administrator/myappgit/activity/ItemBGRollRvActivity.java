package com.example.administrator.myappgit.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.myappgit.IView.IItemBGRollRvActivity;
import com.example.administrator.myappgit.R;
import com.example.administrator.myappgit.adapter.TravelRecyclerAdapter;
import com.example.administrator.myappgit.app.AppConstant;
import com.example.administrator.myappgit.presenter.implPresenter.IItemBGRollRvActivityPresenterImpl;
import com.example.administrator.myappgit.ui.TravelRecyclerView;
import com.example.administrator.myappgit.ui.WhorlView;
import com.example.administrator.myappgit.utils.ScreenUtil;
import com.example.administrator.myappgit.utils.UIUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * item背景滚动 Rv
 * Created by baiyu on 2017/9/16.
 */

public class ItemBGRollRvActivity extends BaseActivity implements IItemBGRollRvActivity {

    @BindView(R.id.trv_showImages)
    TravelRecyclerView mTrvShowImages;
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
    private LinearLayoutManager mLinearLayoutManager;
    private ArrayList<String> imageUrls;
    private TravelRecyclerAdapter mAdapter;
    private IItemBGRollRvActivityPresenterImpl mPresenter;

    private int page;
    private boolean isLoadingMoreData = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_bg_roll_rv_layout);
        ButterKnife.bind(this);
        initData();
        initView();
        initViewListener();
        loadMoreData();
    }

    private void initViewListener() {
        mTrvShowImages.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // TODO: 2017/9/18 0018 因为加了下拉自动加载，所以不能把背景滚动的操作放到自定义wview中，否则会被覆盖，要考虑一些怎么整合下。。
                if (dy > 0) {
                    //向下滚动
                    int totalItemCount = mLinearLayoutManager.getItemCount();
                    int visibleItemCount = mLinearLayoutManager.getChildCount();
                    int unvisibleItemCount = mLinearLayoutManager.findFirstVisibleItemPosition();
                    if (!isLoadingMoreData && (visibleItemCount + unvisibleItemCount + 2 >= totalItemCount)) {
                        System.out.println("loadMoreData");
                        isLoadingMoreData = true;
                        loadMoreData();
                    }
                }

                TravelRecyclerAdapter adapter = (TravelRecyclerAdapter) mTrvShowImages.getAdapter();
                if (dy > 0) {
                    //手指向上滑
                    for (int i = 0; i < mTrvShowImages.getChildCount(); i++) {
                        View child = mTrvShowImages.getChildAt(i);
                        ImageView imageView = (ImageView) child.findViewById(R.id.iv);
                        //ScreenUtil.getScreenHeight(mContext) / 5 是滚动的最大距离
                        if (imageView.getScrollY() + (dy) / 3 < ScreenUtil.getScreenHeight(mContext) / 5) {
                            //(scrollY - proY) / 2使滑动的速度不会太快。。
                            imageView.scrollBy(0, (int) ((dy) / 2.5));
                        } else {
                        }
                        adapter.isUp = true;
                    }
                } else {
                    for (int i = 0; i < mTrvShowImages.getChildCount(); i++) {
                        View child = mTrvShowImages.getChildAt(i);
                        ImageView imageView = (ImageView) child.findViewById(R.id.iv);
                        if (imageView.getScrollY() + (dy) / 3 > -ScreenUtil.getScreenHeight(mContext) / 5) {
                            imageView.scrollBy(0, (int) ((dy) / 2.5));
                        } else {
//                        System.out.println("imageView.getScrollY()+dy::::"+imageView.getScrollY()+dy);
                        }
                        adapter.isUp = false;
                    }
                }
            }
        });
        mTvNetworkErrorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRlNetworkError.setVisibility(View.GONE);
                mPresenter.getImages(imageUrls.size() + "", page++ + "");
            }
        });
    }

    private void loadMoreData() {
        //有没有网都让他去请求
        //有网 ok
        //没网，但有缓存，就可以加载缓存 ok
        //没网，没缓存，框架显示错误信息
        mPresenter.getImages(imageUrls.size() + "", page++ + "");
    }

    private void initView() {
        mTrvShowImages.setLayoutManager(mLinearLayoutManager);
        mTrvShowImages.setHasFixedSize(true);
        //刚开始隐藏，只要加载成功一次就显示，要不然第一次没成功就会显示没图的Rv
        mTrvShowImages.setVisibility(View.GONE);
        mTrvShowImages.setAdapter(mAdapter);
    }

    private void initData() {
        mPresenter = new IItemBGRollRvActivityPresenterImpl(this, this);
        mLinearLayoutManager = new LinearLayoutManager(mContext);
        imageUrls = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            imageUrls.add("");
        }
        new TravelRecyclerAdapter(imageUrls, mContext);
        page = 1;
        mAdapter = new TravelRecyclerAdapter(imageUrls, mContext);
    }

    @Override
    public void upDataImages(ArrayList<String> images) {
        if (mTrvShowImages.getVisibility() == View.GONE) {
            mTrvShowImages.setVisibility(View.VISIBLE);
        }
        if (isLoadingMoreData) {
            isLoadingMoreData = false;
        }
        int oldSize = imageUrls.size();
        if (page == 2) {
            //第一次加载
            imageUrls.clear();
            oldSize = imageUrls.size();
            imageUrls.addAll(images);
        } else {
            imageUrls.addAll(images);
        }
        mAdapter.notifyItemRangeChanged(oldSize == 0 ? 0 : oldSize - 1, imageUrls.size() - 1);
    }

    @Override
    public void showNetworkRequestErrorMessage(String message) {
        page--;
        if (page == 1) {
            //第一次加载，要么有网加载数据，要么没网加载缓存，出错就是加载不出来
            //第一次都没成功，就显示无网的UI，根据message显示不同UI，第一次未必是没网
            Log.d("ItemBGRollRvActivity", "第一次都没成功，就显示无网的UI");
            mRlNetworkError.setVisibility(View.VISIBLE);
            mTvNetworkError.setText(message);
        } else {
            UIUtil.showMessageDialog(this, message, AppConstant.ICON_TYPE_FAIL);
        }
    }

    @Override
    public void showProgressDialog() {
        mWvDialog.setVisibility(View.VISIBLE);
        mWvDialog.start();
    }

    @Override
    public void hidProgressDialog() {
        mWvDialog.setVisibility(View.GONE);
        mWvDialog.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
    }

    @Override
    protected void noNetworkConnFail() {
        UIUtil.snackNewWorkErrorMessage(mTrvShowImages, getString(R.string.error_message_network_connections_break));
    }

    @Override
    protected void noNetworkConnSuccess() {
        if (isLoadingMoreData) {
            isLoadingMoreData = false;
        }
        if (mRlNetworkError.getVisibility() == View.VISIBLE) {
            mRlNetworkError.setVisibility(View.GONE);
            loadMoreData();
        }
    }

}
