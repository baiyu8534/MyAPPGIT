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
import com.example.administrator.myappgit.R;
import com.example.administrator.myappgit.bean.GankBean.GankImages;
import com.example.administrator.myappgit.ui.ShowAllDemosRvItemRecoration;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 文件名：ShowAllDemosActivity
 * 描述：launcher页显示所有的demos
 * 作者：白煜
 * 时间：2017/9/15 0015
 * 版权：
 */

public class ShowAllDemosActivity extends BaseActivity implements IShowAllDemosActivity {

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_demos_layout);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
        mRvShow.setLayoutManager(gridLayoutManager);
        mRvShow.setHasFixedSize(true);
        mRvShow.addItemDecoration(new ShowAllDemosRvItemRecoration(mContext));

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

        ImageView iconImage = (ImageView) headerLayout.findViewById(R.id.icon_image);
        iconImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "头像", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void upDataImages(GankImages images) {

    }
}
