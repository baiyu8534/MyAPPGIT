package com.example.administrator.myappgit.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.myappgit.IView.IItemBGRollRvActivity;
import com.example.administrator.myappgit.R;
import com.example.administrator.myappgit.adapter.TravelRecyclerAdapter;
import com.example.administrator.myappgit.presenter.implPresenter.IItemBGRollRvActivityPresenterImpl;
import com.example.administrator.myappgit.ui.TopFloatHintDialog;
import com.example.administrator.myappgit.ui.TravelRecyclerView;
import com.example.administrator.myappgit.utils.ScreenUtil;

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
    }

    private void loadMoreData() {
        mPresenter.getImages(imageUrls.size() + "", page++ + "");
    }

    private void initView() {
        mTrvShowImages.setLayoutManager(mLinearLayoutManager);
        mTrvShowImages.setHasFixedSize(true);
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
        if (isLoadingMoreData)
            isLoadingMoreData = false;
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
    public void showErrorMessage(String message) {
        showMessageDialog(message, TopFloatHintDialog.Builder.ICON_TYPE_FAIL);
    }

    @Override
    public void setRefreshing(boolean refreshing) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
    }
}
