package com.example.administrator.myappgit.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.myappgit.IView.IShowAllDemosActivity;
import com.example.administrator.myappgit.MainActivity;
import com.example.administrator.myappgit.R;
import com.example.administrator.myappgit.adapter.RvShowAllDemosAdapter;
import com.example.administrator.myappgit.bean.adapterBean.RvShowAllDemosAdapterItemBean;
import com.example.administrator.myappgit.presenter.implPresenter.ShowAllDemosActivityPresenterImpl;
import com.example.administrator.myappgit.ui.ShowAllDemosRvItemRecoration;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 文件名：ShowAllDemosActivity
 * 描述：launcher页显示所有的demos
 * 作者：白煜
 * 时间：2017/9/15 0015
 * 版权：
 */

public class ShowAllDemosActivity extends BaseActivity implements IShowAllDemosActivity, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rv_show)
    RecyclerView mRvShow;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;
    private ShowAllDemosActivityPresenterImpl mShowAllDemosActivityPresenter;

    private String[] demoNames;
    private Class[] mClasses;
    private ArrayList<RvShowAllDemosAdapterItemBean> adapterItemBeans;
    private RvShowAllDemosAdapter mAdapter;
    /**
     * 头像
     */
    private ImageView mIconImage;

    /**
     * 获取图片的页数
     */
    private int page = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_demos_layout);
        ButterKnife.bind(this);
        initData();
        initView();
        initViewListener();
        // FIXME: 2017/9/15 可以把图片加载放到启动页中
        mShowAllDemosActivityPresenter.getImages(adapterItemBeans.size() + "", page + "");
    }

    private void initViewListener() {
        mIconImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "头像", Toast.LENGTH_SHORT).show();
            }
        });
        mSwipeRefresh.setOnRefreshListener(this);
    }

    private void initData() {
        mShowAllDemosActivityPresenter = new ShowAllDemosActivityPresenterImpl(this, this);
        demoNames = new String[]{
                getResources().getString(R.string.demo_name_1),
                getResources().getString(R.string.demo_name_2),
                getResources().getString(R.string.demo_name_3),
                getResources().getString(R.string.demo_name_4)
        };
        mClasses = new Class[]{
                MainActivity.class,
                GuideActivity.class,
                GuideActivity.class,
                ItemBGRollRvActivity.class
        };
        adapterItemBeans = new ArrayList<>();

        for (int i = 0; i < demoNames.length; i++) {
            RvShowAllDemosAdapterItemBean bean = new RvShowAllDemosAdapterItemBean();
            bean.setItemTitle(demoNames[i]);
            bean.setItemImageUrl("");
            bean.setActivityClass(mClasses[i]);
            adapterItemBeans.add(bean);
        }
        mAdapter = new RvShowAllDemosAdapter(this, adapterItemBeans);
    }

    private void initView() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //设置那个矢量动画按钮
        ActionBarDrawerToggle actionBarDrawerToggle;
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, 0, 0);
        actionBarDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(actionBarDrawerToggle);

//        WaveBackGroundVIew wbg = (WaveBackGroundVIew) mNavigationView.getRootView().findViewById(R.id.wbg);
        View headerLayout =
                mNavView.inflateHeaderView(R.layout.nav_hander_layout);

        mIconImage = (ImageView) headerLayout.findViewById(R.id.icon_image);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
        mRvShow.setLayoutManager(gridLayoutManager);
        mRvShow.setHasFixedSize(true);
        mRvShow.addItemDecoration(new ShowAllDemosRvItemRecoration(mContext));
        mRvShow.setAdapter(mAdapter);

        mSwipeRefresh.setColorSchemeResources(R.color.mainRvItemBg2,R.color.colorPrimary);
        mSwipeRefresh.setProgressBackgroundColorSchemeResource(R.color.colorWhite);
    }

    @Override
    public void upDataImages(ArrayList<String> images) {
        for (int i = 0; i < adapterItemBeans.size(); i++) {
            adapterItemBeans.get(i).setItemImageUrl(images.get(i));
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorMessage(String message) {
        // FIXME: 2017/9/15 集成QMUI，用里面的message控件
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mShowAllDemosActivityPresenter.unsubscribe();
    }

    @Override
    public void onRefresh() {
        mShowAllDemosActivityPresenter.getImages(adapterItemBeans.size() + "", ++page + "");
    }

    @Override
    public void setRefreshing(boolean refreshing) {
        mSwipeRefresh.setRefreshing(refreshing);
    }
}
