package com.example.administrator.myappgit.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.administrator.myappgit.R;
import com.example.administrator.myappgit.adapter.TraveListAdapter;
import com.example.administrator.myappgit.ui.TravelListView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * item背景滚动list
 * Created by baiyu on 2017/9/16.
 */

public class ItemBGRollListActivity extends BaseActivity {

    @BindView(R.id.tlv)
    TravelListView mTlv;

    private int[] imageRes;
    private TraveListAdapter mTraveListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_bg_roll_list_layout);
        ButterKnife.bind(this);
        initData();
        initView();
        initViewListener();
    }

    private void initData() {
        imageRes = new int[]{
                R.drawable.show_image_1,
                R.drawable.show_image_2,
                R.drawable.show_image_3,
                R.drawable.show_image_4,
                R.drawable.show_image_1,
                R.drawable.show_image_2,
                R.drawable.show_image_3,
                R.drawable.show_image_4,
                R.drawable.show_image_1,
                R.drawable.show_image_2,
                R.drawable.show_image_3,
                R.drawable.show_image_4
        };
        mTraveListAdapter = new TraveListAdapter(imageRes, mContext);
        mTlv.setMyAdapter(mTraveListAdapter);
    }

    private void initView() {
        mTlv.setAdapter(mTraveListAdapter);
    }

    private void initViewListener() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
