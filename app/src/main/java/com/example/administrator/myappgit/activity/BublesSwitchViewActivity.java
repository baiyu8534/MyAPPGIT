package com.example.administrator.myappgit.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.example.administrator.myappgit.R;
import com.example.administrator.myappgit.ui.BublesSwitchView;
import com.example.administrator.myappgit.ui.DrowInterpolatorView;
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
    @BindView(R.id.bsw_60_1)
    BublesSwitchView mBsw601;
    @BindView(R.id.bsw_100_1)
    BublesSwitchView mBsw1001;
    @BindView(R.id.bsw_150_1)
    BublesSwitchView mBsw1501;
    @BindView(R.id.bsw_s_1)
    BublesSwitchView mBswS1;
    @BindView(R.id.bsw_s_2)
    BublesSwitchView mBswS2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bubles_switchview_layout);
        ButterKnife.bind(this);

        initView();
        initListener();

    }

    private void initView() {
        mBsw40.setChecked(true);
        mBsw50.setChecked(false);
        mBsw60.setChecked(true);
        mBsw601.setChecked(false);
        mBsw100.setChecked(false);
        mBsw1001.setChecked(true);
        mBsw150.setChecked(true);
        mBsw200.setChecked(false);
        mBsw250.setChecked(true);
        mBsw300.setChecked(false);

        mBsw40.setInterpolator(new BounceInterpolator());
        mBsw60.setInterpolator(new BounceInterpolator());
        mBsw100.setInterpolator(new AccelerateDecelerateInterpolator());
        mBsw1001.setInterpolator(new DrowInterpolatorView.MyDecelerateInterpolator());
        mBswS1.setInterpolator(new DrowInterpolatorView.MyDecelerateInterpolator());
        mBswS2.setInterpolator(new DrowInterpolatorView.MyDecelerateInterpolator());
//        mBsw150.setInterpolator(new AnticipateInterpolator());
        mBsw1501.setInterpolator(new BounceInterpolator());
//        mBsw200.setInterpolator(new AccelerateInterpolator());
        mBsw250.setInterpolator(new BounceInterpolator());
        mBsw300.setInterpolator(new DecelerateInterpolator());

        //设置小球和背景 选中和未选中的颜色
        mBsw100.setBGColors(R.color.black, R.color.colorPrimary);
        mBsw100.setCircleColors(R.color.mainRvItemBg1, R.color.mainRvItemBg2);

        mBsw1001.setBGColors(R.color.black, R.color.black);
        mBsw1001.setCircleColors(R.color.colorWhite, R.color.colorWhite);
        mBswS1.setBGColors(R.color.black, R.color.black);
        mBswS1.setCircleColors(R.color.colorWhite, R.color.colorWhite);
        mBswS2.setBGColors(R.color.black, R.color.black);
        mBswS2.setCircleColors(R.color.colorWhite, R.color.colorWhite);

        //设置背景为图片类型
        mBsw150.setBgType(BublesSwitchView.BgType.BITMAP);
        mBsw1501.setBgType(BublesSwitchView.BgType.BITMAP);
        //设置选中和未选中的背景图片
        mBsw150.setBGDrawableResIds(R.drawable.d_1, R.drawable.d_2);
//        mBsw1501.setBGDrawableResIds(R.drawable.d_2,R.drawable.d_1);

        //设置为默认图片类型
        mBsw300.setBgType(BublesSwitchView.BgType.BITMAP);
        mBsw60.setBgType(BublesSwitchView.BgType.BITMAP);
        mBsw601.setBgType(BublesSwitchView.BgType.BITMAP);
        mBswS2.setBgType(BublesSwitchView.BgType.BITMAP);
    }

    private void initListener() {
        mBsw300.setOnClickListener(this);
        mBsw250.setOnCheckedChangeListener(this);
    }

    int i = 0;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bsw_300:
                UIUtil.toastShort(mContext, "bsw_300 被点击了" + ++i + "次");
                break;
        }
    }


    @Override
    public void onCheckedChange(BublesSwitchView bublesSwitchView, boolean checked) {
        switch (bublesSwitchView.getId()) {
            case R.id.bsw_250:
                UIUtil.toastShort(mContext, "bsw_250 的选中状态变为" + checked);
                break;
        }
    }
}
