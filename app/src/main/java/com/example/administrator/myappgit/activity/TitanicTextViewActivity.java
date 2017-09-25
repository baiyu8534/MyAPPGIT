package com.example.administrator.myappgit.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.administrator.myappgit.R;
import com.example.administrator.myappgit.ui.Titanic;
import com.example.administrator.myappgit.ui.TitanicTextView;
import com.example.administrator.myappgit.ui.Typefaces;
import com.example.administrator.myappgit.utils.UIUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 文件名：TitanicTextViewActivity
 * 描述：TitanicTextView
 * 作者：白煜
 * 时间：2017/9/25 0025
 * 版权：
 */

public class TitanicTextViewActivity extends BaseActivity {

    @BindView(R.id.tv_titanic)
    TitanicTextView mTvTitanic;
    private Titanic mTitanic;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_titanic_text_view_layout);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mTvTitanic.setTypeface(Typefaces.get(this, "Satisfy-Regular.ttf"));

        // start animation
        mTitanic = new Titanic();
        mTitanic.start(mTvTitanic);

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(TitanicTextViewActivity.this, MainActivity.class);
//                TitanicTextViewActivity.this.startActivity(intent);
//                TitanicTextViewActivity.this.finish();
//            }
//        }, 3000);
    }

    @Override
    protected void noNetworkConnFail() {
        UIUtil.snackNewWorkErrorMessage(mTvTitanic, getString(R.string.alert_message_no_network_conn));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTitanic != null) {
            mTitanic.cancel();
        }
    }
}
