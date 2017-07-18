package com.example.administrator.myappgit;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Pair;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myappgit.activity.BaseActivity;
import com.example.administrator.myappgit.activity.ShowListActivity;
import com.example.administrator.myappgit.adapter.RvMainAdapter;
import com.example.administrator.myappgit.ui.MainRvItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity implements RvMainAdapter.RvItemClickListener, View.OnClickListener {

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

    private LinearLayoutManager mLinearLayoutManager;
    private RvMainAdapter mRvMainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();
        initListener();

    }

    private void initListener() {
        mRvMainAdapter.setRvItemClickListener(this);
        mFloatButton.setOnClickListener(this);

    }

    private void initView() {
        setSupportActionBar(mToolbar);

        //不显示默认标题
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mLinearLayoutManager = new LinearLayoutManager(this);
        mRvMain.setLayoutManager(mLinearLayoutManager);
        mRvMain.addItemDecoration(new MainRvItemDecoration(this));
        mRvMainAdapter = new RvMainAdapter(this);
        mRvMain.setAdapter(mRvMainAdapter);

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


    /**
     * rv的点击事件
     */
    @Override
    public void onRvItemClickListener(int position) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Intent intent = new Intent(this, ShowListActivity.class);
            intent.putExtra("position",position);
            List<Pair<TextView, String>> pairs = new ArrayList<>();
            int firstVisibleItemPosition = mLinearLayoutManager.findFirstVisibleItemPosition();
            int lastVisibleItemPosition = mLinearLayoutManager.findLastVisibleItemPosition();

            for (int i = firstVisibleItemPosition; i <= lastVisibleItemPosition; i++) {
                RvMainAdapter.MyViewHolder holder = (RvMainAdapter.MyViewHolder) mRvMain.findViewHolderForAdapterPosition(i);
                pairs.add(Pair.create(holder.tv_title, "tab_" + i));
                if (BuildConfig.DEBUG) Log.d("MainActivity", "tab_"+i);
            }
            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(this, pairs.toArray(new Pair[]{})).toBundle();
            startActivity(intent, bundle);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.float_button:
                Toast.makeText(this,"float_button", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
