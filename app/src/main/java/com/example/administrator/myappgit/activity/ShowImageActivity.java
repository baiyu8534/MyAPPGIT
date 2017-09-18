package com.example.administrator.myappgit.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.myappgit.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 文件名：ShowImageActivity
 * 描述：显示图片
 * 作者：白煜
 * 时间：2017/9/18 0018
 * 版权：
 */

public class ShowImageActivity extends BaseActivity {

    @BindView(R.id.iv)
    ImageView mIv;
    private String mImageUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // FIXME: 2017/9/18 0018 要加教程遮罩，长按保存，设置桌面
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_iamge_layout);
        ButterKnife.bind(this);
        initData();
        initView();
        initViewListener();
    }

    private void initViewListener() {
        mIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ShowImageActivity.this.finishAfterTransition();
                } else {
                    ShowImageActivity.this.finish();
                }
            }
        });
    }

    private void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mIv.setTransitionName("iv_item");
        }
        Glide.with(mContext).load(mImageUrl)
                .apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .centerCrop())
                //加载缩略图，缩略图先加载完就显示，否则不显示
                .into(mIv);
    }

    private void initData() {
        mImageUrl = getIntent().getStringExtra("imageUrl");
    }
}
