package com.example.administrator.myappgit.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Pair;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myappgit.R;
import com.example.administrator.myappgit.adapter.RvTestAdapter;
import com.example.administrator.myappgit.presenter.implPresenter.MainActivityPresenterImpl;
import com.example.administrator.myappgit.ui.MainRvItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class TestActivity extends BaseActivity implements RvTestAdapter.RvItemClickListener, View.OnClickListener,
         NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.icon_image)
    CircleImageView mIconImage;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.float_button)
    FloatingActionButton mFloatButton;
    @BindView(R.id.rv_main)
    RecyclerView mRvMain;
    @BindView(R.id.nv_main)
    NavigationView mNvMain;
    @BindView(R.id.iv_nv_menu_icon)
    ImageView mIvNvMenuIcon;

    private LinearLayoutManager mLinearLayoutManager;
    private ImageView mImagerNvHeader;
    private MainActivityPresenterImpl mMainActivityPresenter;
    private RvTestAdapter mRvTestAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();
        initListener();

        //不判断网络了。。就几张图片。。加载不出来了,有缓存就显示缓存，没有就显示默认的，返回的错误用架构了的提示就行了
//        mMainActivityPresenter = new MainActivityPresenterImpl(this, this);
//        mMainActivityPresenter.getImgaes(5, PixabayApi.PIXABAY_QUARY_TAG_SCENERY);

    }

    private void initListener() {
        mRvTestAdapter.setRvItemClickListener(this);
        mFloatButton.setOnClickListener(this);
        mNvMain.setNavigationItemSelectedListener(this);

    }

    private void initView() {

        View headerLayout =
                mNvMain.inflateHeaderView(R.layout.nv_header_layout);
        mImagerNvHeader = (ImageView) headerLayout.findViewById(R.id.iv_nv_header);

        setSupportActionBar(mToolbar);

        //不显示默认标题
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mLinearLayoutManager = new LinearLayoutManager(this);
        mRvMain.setLayoutManager(mLinearLayoutManager);
        mRvMain.addItemDecoration(new MainRvItemDecoration(this));
        mRvTestAdapter = new RvTestAdapter(this);
        mRvMain.setAdapter(mRvTestAdapter);

        mIvNvMenuIcon.setOnClickListener(this);

        ActionBar actionBar = getSupportActionBar();
    }



    /**
     * rv的点击事件
     */
    @Override
    public void onRvItemClickListener(int position) {
        Intent intent = new Intent(this, TestActivity2.class);
        intent.putExtra("position", position);
        List<Pair<View, String>> pairs = new ArrayList<>();
        int firstVisibleItemPosition = mLinearLayoutManager.findFirstVisibleItemPosition();
        int lastVisibleItemPosition = mLinearLayoutManager.findLastVisibleItemPosition();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            for (int i = firstVisibleItemPosition; i <= lastVisibleItemPosition; i++) {
                RvTestAdapter.MyViewHolder holder = (RvTestAdapter.MyViewHolder) mRvMain.findViewHolderForAdapterPosition(i);
//                pairs.add(Pair.create((View) holder.tv_title, "tab_" + i));
                pairs.add(Pair.create((View) holder.cv, "tab_cv_" + i));
//                if (BuildConfig.DEBUG) Log.d("MainActivity", "tab_" + i);
            }
//            pairs.add(Pair.create((View) mIconImage, "mIconImage"));
//            pairs.add(Pair.create((View) mTvTitle, "mTvTitle"));
//            pairs.add(Pair.create((View) mIvNvMenuIcon, "mIvNvMenuIcon"));
            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(this, pairs.toArray(new Pair[]{})).toBundle();
            startActivity(intent, bundle);
        } else {
            startActivity(intent);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.float_button:
                Toast.makeText(this, "float_button", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_nv_menu_icon:
                mDrawerLayout.openDrawer(Gravity.RIGHT);
                break;
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.toolbar_nv:
                mDrawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.nv_item_1:
                mDrawerLayout.closeDrawer(Gravity.RIGHT);
                break;
            case R.id.nv_item_2:
                mDrawerLayout.closeDrawer(Gravity.RIGHT);
                break;
            case R.id.nv_item_3:
                mDrawerLayout.closeDrawer(Gravity.RIGHT);
                break;
            case R.id.nv_item_4:
                mDrawerLayout.closeDrawer(Gravity.RIGHT);
                break;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}