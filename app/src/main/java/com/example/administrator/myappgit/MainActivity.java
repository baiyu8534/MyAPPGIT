package com.example.administrator.myappgit;

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
import android.util.Log;
import android.util.Pair;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.myappgit.IView.IMainActivity;
import com.example.administrator.myappgit.activity.BaseActivity;
import com.example.administrator.myappgit.activity.ShowListActivity;
import com.example.administrator.myappgit.adapter.RvMainAdapter;
import com.example.administrator.myappgit.api.PixabayApi;
import com.example.administrator.myappgit.app.AppConstant;
import com.example.administrator.myappgit.bean.PixadayBean.PixabayListBean;
import com.example.administrator.myappgit.presenter.implPresenter.MainActivityPresenterImpl;
import com.example.administrator.myappgit.ui.MainRvItemDecoration;
import com.example.administrator.myappgit.utils.UIUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity implements RvMainAdapter.RvItemClickListener, View.OnClickListener,
        IMainActivity, NavigationView.OnNavigationItemSelectedListener {

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
    private RvMainAdapter mRvMainAdapter;
    private ImageView mImagerNvHeader;
    private MainActivityPresenterImpl mMainActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();
        initListener();

        //不判断网络了。。就几张图片。。加载不出来了,有缓存就显示缓存，没有就显示默认的，返回的错误用架构了的提示就行了
        mMainActivityPresenter = new MainActivityPresenterImpl(this, this);
        mMainActivityPresenter.getImgaes(5, PixabayApi.PIXABAY_QUARY_TAG_SCENERY);

    }

    private void initListener() {
        mRvMainAdapter.setRvItemClickListener(this);
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
        mRvMainAdapter = new RvMainAdapter(this);
        mRvMain.setAdapter(mRvMainAdapter);

        mIvNvMenuIcon.setOnClickListener(this);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //显示小汉堡图标，关联drawerLayout
//            actionBar.setHomeButtonEnabled(true);
//            actionBar.setDisplayHomeAsUpEnabled(true);
//            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, 0, 0);
//            toggle.syncState();
//            mDrawerLayout.setDrawerListener(toggle);
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main_toolbar, menu);
//        return true;
//    }

    //
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.toolbar_nv:
//                mDrawerLayout.openDrawer(Gravity.RIGHT);
//                break;
//            case R.id.nv_item_1:
//                mDrawerLayout.closeDrawer(Gravity.RIGHT);
//                startActivity(new Intent(mContext, TestSwipeBackActivity.class));
//                break;
//            case R.id.nv_item_2:
//
//                break;
//            case R.id.nv_item_3:
//
//                break;
//            case R.id.nv_item_4:
//
//                break;
//        }
//        return true;
//    }


    /**
     * rv的点击事件
     */
    @Override
    public void onRvItemClickListener(int position) {
        Intent intent = new Intent(this, ShowListActivity.class);
        intent.putExtra("position", position);
        List<Pair<View, String>> pairs = new ArrayList<>();
        int firstVisibleItemPosition = mLinearLayoutManager.findFirstVisibleItemPosition();
        int lastVisibleItemPosition = mLinearLayoutManager.findLastVisibleItemPosition();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            for (int i = firstVisibleItemPosition; i <= lastVisibleItemPosition; i++) {
                RvMainAdapter.MyViewHolder holder = (RvMainAdapter.MyViewHolder) mRvMain.findViewHolderForAdapterPosition(i);
                pairs.add(Pair.create((View) holder.tv_title, "tab_" + i));
                if (BuildConfig.DEBUG) Log.d("MainActivity", "tab_" + i);
            }
            pairs.add(Pair.create((View) mIconImage, "mIconImage"));
            pairs.add(Pair.create((View) mTvTitle, "mTvTitle"));
            pairs.add(Pair.create((View) mIvNvMenuIcon, "mIvNvMenuIcon"));
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
    public void setPic(List<PixabayListBean.HitsBean> hitsBeen) {
        if (hitsBeen != null) {
            String webformatURL = hitsBeen.get(0).getWebformatURL();
            webformatURL.replace("640","180");
            Glide.with(mContext).load(webformatURL)
                    .apply(new RequestOptions()
                            .error(R.drawable.show_image_default)
                            .placeholder(R.drawable.show_image_default)
                            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                            .centerCrop())
                    //加载缩略图，缩略图先加载完就显示，否则不显示
                    .thumbnail(0.2f)
                    .into(mImagerNvHeader);
            mRvMainAdapter.setHitsBeen(hitsBeen);
            mRvMainAdapter.notifyDataSetChanged();
        } else {
            Glide.with(this).load(R.drawable.hander).into(mImagerNvHeader);
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
    public void showNetworkRequestErrorMessage(String message) {
        UIUtil.showMessageDialog(this, message, AppConstant.ICON_TYPE_FAIL);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainActivityPresenter.unsubscribe();
    }

    @Override
    protected void noNetworkConnFail() {
        UIUtil.snackNewWorkErrorMessage(mRvMain, getString(R.string.error_message_network_connections_break));
    }
}
