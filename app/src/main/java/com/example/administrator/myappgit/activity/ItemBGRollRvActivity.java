package com.example.administrator.myappgit.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.example.administrator.myappgit.R;
import com.example.administrator.myappgit.adapter.TravelRecyclerAdapter;
import com.example.administrator.myappgit.ui.TravelRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * item背景滚动 Rv
 * Created by baiyu on 2017/9/16.
 */

public class ItemBGRollRvActivity extends BaseActivity {

    @BindView(R.id.trv_showImages)
    TravelRecyclerView mTrvShowImages;
    private LinearLayoutManager mLinearLayoutManager;
    private ArrayList<String> imageUrls;
    private TravelRecyclerAdapter mAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_bg_roll_rv_layout);
        ButterKnife.bind(this);
        initData();
        initView();
        initViewListener();
    }

    private void initViewListener() {

    }

    private void initView() {

    }

    private void initData() {
        mLinearLayoutManager = new LinearLayoutManager(mContext);

    }
}
