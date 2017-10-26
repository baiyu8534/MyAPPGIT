package com.example.administrator.myappgit.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.example.administrator.myappgit.R;
import com.example.administrator.myappgit.ui.BublesSwitchView;
import com.example.administrator.myappgit.utils.UIUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 文件名：BublesSwitchViewActivity
 * 描述：BublesSwitchView
 * 作者：白煜
 * 时间：2017/10/24 0024
 * 版权：
 */

public class BublesSwitchViewActivity extends BaseActivity implements View.OnClickListener, BublesSwitchView
        .OnCheckedChangeListener {

    @BindView(R.id.bsw_40)
    BublesSwitchView mBsw40;
    @BindView(R.id.bsw_50)
    BublesSwitchView mBsw50;
    @BindView(R.id.bsw_60)
    BublesSwitchView mBsw60;
    @BindView(R.id.bsw_100)
    BublesSwitchView mBsw100;
    @BindView(R.id.bsw_150)
    BublesSwitchView mBsw150;
    @BindView(R.id.bsw_200)
    BublesSwitchView mBsw200;
    @BindView(R.id.bsw_250)
    BublesSwitchView mBsw250;
    @BindView(R.id.bsw_300)
    BublesSwitchView mBsw300;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bubles_switchview_layout);
        ButterKnife.bind(this);

        initView();
        initListener();

    }

    private void initView() {
        mBsw40.setSelect(true);
        mBsw50.setSelect(false);
        mBsw60.setSelect(true);
        mBsw100.setSelect(false);
        mBsw150.setSelect(true);
        mBsw200.setSelect(false);
        mBsw250.setSelect(true);
        mBsw300.setSelect(false);

//        mBsw40.setInterpolator();
//        mBsw50.setInterpolator();
//        mBsw60.setInterpolator();
        mBsw100.setInterpolator(new AccelerateDecelerateInterpolator());
        mBsw150.setInterpolator(new AnticipateInterpolator());
        mBsw200.setInterpolator(new AccelerateInterpolator());
        mBsw250.setInterpolator(new BounceInterpolator());
        mBsw300.setInterpolator(new DecelerateInterpolator());
    }

    private void initListener() {
        mBsw300.setOnClickListener(this);
        mBsw300.setOnCheckedChangeListener(this);
    }

    int i = 0;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bsw_40:
                UIUtil.toastShort(mContext,"bsw_40 被点击了");
                break;
            case R.id.bsw_50:
                UIUtil.toastShort(mContext,"bsw_50 被点击了");
                break;
            case R.id.bsw_60:
                UIUtil.toastShort(mContext,"bsw_60 被点击了");
                break;
            case R.id.bsw_100:
                UIUtil.toastShort(mContext,"bsw_100 被点击了");
                break;
            case R.id.bsw_150:
                UIUtil.toastShort(mContext,"bsw_150 被点击了");
                break;
            case R.id.bsw_200:
                UIUtil.toastShort(mContext,"bsw_200 被点击了");
                break;
            case R.id.bsw_250:
                UIUtil.toastShort(mContext,"bsw_250 被点击了");
                break;
            case R.id.bsw_300:
                UIUtil.toastShort(mContext,"bsw_300 被点击了"+ ++i +"次");
                break;
        }
    }

    @Override
    public void onCheckedChange(BublesSwitchView bublesSwitchView, boolean isSelected) {
        switch (bublesSwitchView.getId()){
            case R.id.bsw_40:
                UIUtil.toastShort(mContext,"bsw_40 的选中状态变为"+isSelected);
                break;
            case R.id.bsw_50:
                UIUtil.toastShort(mContext,"bsw_50 的选中状态变为"+isSelected);
                break;
            case R.id.bsw_60:
                UIUtil.toastShort(mContext,"bsw_60 的选中状态变为"+isSelected);
                break;
            case R.id.bsw_100:
                UIUtil.toastShort(mContext,"bsw_100 的选中状态变为"+isSelected);
                break;
            case R.id.bsw_150:
                UIUtil.toastShort(mContext,"bsw_150 的选中状态变为"+isSelected);
                break;
            case R.id.bsw_200:
                UIUtil.toastShort(mContext,"bsw_200 的选中状态变为"+isSelected);
                break;
            case R.id.bsw_250:
                UIUtil.toastShort(mContext,"bsw_250 的选中状态变为"+isSelected);
                break;
            case R.id.bsw_300:
                UIUtil.toastShort(mContext,"bsw_300 的选中状态变为"+isSelected);
                break;
        }
    }
}
