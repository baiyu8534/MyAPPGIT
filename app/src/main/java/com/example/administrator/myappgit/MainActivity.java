package com.example.administrator.myappgit;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.administrator.myappgit.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity {

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

    private int[] itemTitles = {
            R.string.rv_main_item_1,
            R.string.rv_main_item_2,
            R.string.rv_main_item_3,
            R.string.rv_main_item_4,
            R.string.rv_main_item_5,
    };
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();
        initListener();

    }

    private void initListener() {

    }

    private void initView() {
        setSupportActionBar(mToolbar);

        //不显示默认标题
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mLinearLayoutManager = new LinearLayoutManager(this);

        mRvMain.setLayoutManager(mLinearLayoutManager);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_nv:
                mDrawerLayout.openDrawer(Gravity.RIGHT);
                break;
        }
        return true;
    }
}
