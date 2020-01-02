package com.example.administrator.myappgit.activity;

import android.content.Intent;
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
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.ObjectKey;
import com.example.administrator.myappgit.IView.IShowAllDemosActivity;
import com.example.administrator.myappgit.MainActivity;
import com.example.administrator.myappgit.MyApplication;
import com.example.administrator.myappgit.R;
import com.example.administrator.myappgit.adapter.RvShowAllDemosAdapter;
import com.example.administrator.myappgit.app.AppConstant;
import com.example.administrator.myappgit.bean.adapterBean.RvShowAllDemosAdapterItemBean;
import com.example.administrator.myappgit.presenter.implPresenter.ShowAllDemosActivityPresenterImpl;
import com.example.administrator.myappgit.service.BaseService;
import com.example.administrator.myappgit.ui.GlideCircleTransform;
import com.example.administrator.myappgit.ui.ShowAllDemosRvItemRecoration;
import com.example.administrator.myappgit.utils.UIUtil;

import java.io.File;
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

public class ShowAllDemosActivity extends BaseActivity implements IShowAllDemosActivity, SwipeRefreshLayout.OnRefreshListener,
        View.OnClickListener {

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
    /**
     * 是否第一次网络请求。当没网络时启动app：主页面第一次显示snackBar提示用户去设置网络，
     * 之后刷新就显示dialog去提示用户
     */
    private boolean isFirstNetworkRequest = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_demos_layout);
        ButterKnife.bind(this);
        // FIXME: 2017/9/21 0021 妈的要搞个启动页了。。在启动页开service,在这开，数据不及时，导致虽然有网，但第一次加载时isConnected()=false
        startService(new Intent(mContext, BaseService.class));

        initData();
        initView();
        initViewListener();
        // FIXME: 2017/9/15 可以把图片加载放到启动页中
        //第一次不加网络判断了，这样可以加载缓存
        mShowAllDemosActivityPresenter.getImages(adapterItemBeans.size() + "", page + "");
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 加载选择的头像
        File file = new File(ShowAllDemosActivity.this.getExternalCacheDir(), "headship_icon.jpg");
        if (file.exists()) {
            Glide.with(this).load(file)
                    .apply(new RequestOptions().error(R.drawable.information)
                            .signature(new ObjectKey(System.currentTimeMillis()))
                            .placeholder(R.drawable.information)
                            .centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .transform(new GlideCircleTransform(this)))
                    .into(mIconImage);
//            headShipFile = file;
        }
    }

    private void initViewListener() {
        mIconImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(mContext, "头像", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ShowAllDemosActivity.this, ChangeHeadIconActivity.class));
            }
        });
        mSwipeRefresh.setOnRefreshListener(this);
        mFab.setOnClickListener(this);
    }

    private void initData() {
        mShowAllDemosActivityPresenter = new ShowAllDemosActivityPresenterImpl(this, this);
        demoNames = new String[]{
                getResources().getString(R.string.demo_name_1),
                getResources().getString(R.string.demo_name_2),
                getResources().getString(R.string.demo_name_3),
                getResources().getString(R.string.demo_name_4),
                getResources().getString(R.string.demo_name_5),
                getResources().getString(R.string.demo_name_6),
                getString(R.string.demo_name_7),
                getString(R.string.demo_name_8),
                getString(R.string.demo_name_9),
                getString(R.string.demo_name_10),
                getString(R.string.demo_name_11),
                getString(R.string.demo_name_12),
                getString(R.string.demo_name_13),
        };
        mClasses = new Class[]{
                MainActivity.class,
                GuideActivity.class,
                ItemBGRollListActivity.class,
                ItemBGRollRvActivity.class,
                TitanicTextViewActivity.class,
                ParallaxActivity.class,
                VariationTabLayoutActivity.class,
                BublesSwitchViewActivity.class,
                DrowInterpolatorViewActivity.class,
                ComplexGlidLayoutRv.class,
                ChangeHeadIconActivity.class,
                GradualAppBarActivity.class,
                TransformIndicatorActivity.class
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

        mSwipeRefresh.setColorSchemeResources(R.color.mainRvItemBg2, R.color.colorPrimary);
        mSwipeRefresh.setProgressBackgroundColorSchemeResource(R.color.colorWhite);
    }

    @Override
    public void upDataImages(ArrayList<String> images) {
        if (isFirstNetworkRequest) {
            isFirstNetworkRequest = false;
        }
        for (int i = 0; i < adapterItemBeans.size(); i++) {
            adapterItemBeans.get(i).setItemImageUrl(images.get(i));
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showNetworkRequestErrorMessage(String message) {
        page--;
        mSwipeRefresh.setRefreshing(false);
        if (isFirstNetworkRequest) {
            isFirstNetworkRequest = false;
        } else {
            UIUtil.showMessageDialog(this, message, AppConstant.ICON_TYPE_FAIL);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mShowAllDemosActivityPresenter.unsubscribe();
    }

    @Override
    public void onRefresh() {
        //有没有网都让他刷新
        //有网 ok
        //没网，但有缓存，就可以加载缓存 ok
        //没网，没缓存，框架显示错误信息
        mShowAllDemosActivityPresenter.getImages(adapterItemBeans.size() + "", ++page + "");
    }

    @Override
    public void setRefreshing(boolean refreshing) {
        mSwipeRefresh.setRefreshing(refreshing);
    }

    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), R.string.message_out_app_tip, Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                MyApplication.getInstance().finishApp();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void noNetworkConnFail() {
        UIUtil.snackNewWorkErrorMessage(mRvShow, getString(R.string.alert_message_no_network_conn));
    }

    @Override
    protected void noNetworkConnSuccess() {
        //当网络恢复时，暂时没有需要做的操作
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                UIUtil.toastShort(mContext, "FloatingActionButton");
                break;
        }
    }
}
