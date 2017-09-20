package com.example.administrator.myappgit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.administrator.myappgit.IView.IZhiHuDetailActivity;
import com.example.administrator.myappgit.R;
import com.example.administrator.myappgit.app.AppConstant;
import com.example.administrator.myappgit.bean.ZhiHuBean.ZhiHuNewDetail;
import com.example.administrator.myappgit.presenter.implPresenter.IZhiHuDetailActivityPresenterImpl;
import com.example.administrator.myappgit.ui.WhorlView;
import com.liuguangqiang.swipeback.SwipeBackLayout;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by baiyu on 2017/9/8.
 */

public class ZhiHuDetailActivity extends BaseActivity implements IZhiHuDetailActivity, SwipeBackLayout.SwipeBackListener {

    WebView mWvDetail;
    WhorlView mWvDialog;
    private ImageView ivShadow;
    private IZhiHuDetailActivityPresenterImpl mIZhiHuDetailActivityPresenter;
    private String mNewsId;
    private SwipeBackLayout swipeBackLayout;
    private ImageView mImageVIew;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // FIXME: 2017/9/20 0020 没网时不显示列表，显示一个图标加一个按钮提示没网（暂定）
        setContentView(setSwipeContentView(R.layout.activity_zhuhu_detail_layout));
        mWvDetail = (WebView) findViewById(R.id.wv_detail);
        mWvDialog = (WhorlView) findViewById(R.id.wv_dialog);
        mImageVIew = (ImageView) findViewById(R.id.iv);
        mIZhiHuDetailActivityPresenter = new IZhiHuDetailActivityPresenterImpl(this, this);
        Intent intent = getIntent();
        mNewsId = intent.getStringExtra("newsId");
        initListener();
        initView();
        getData();
    }

    private void getData() {
        mIZhiHuDetailActivityPresenter.getZhiHuNewsDetail(mNewsId);
    }


    private void initListener() {

    }

    private View setSwipeContentView(int resId) {
        View view = LayoutInflater.from(this).inflate(resId, null);
        mWvDetail = (WebView) view.findViewById(R.id.wv_detail);
        mWvDialog = (WhorlView) view.findViewById(R.id.wv_dialog);

        RelativeLayout container = new RelativeLayout(this);
        swipeBackLayout = new SwipeBackLayout(this);
        swipeBackLayout.setOnSwipeBackListener(this);
        swipeBackLayout.addView(view);
        swipeBackLayout.setDragEdge(SwipeBackLayout.DragEdge.LEFT);
        ivShadow = new ImageView(this);
        ivShadow.setBackgroundColor(getResources().getColor(R.color.black_p50));
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        container.addView(ivShadow, params);
        container.addView(swipeBackLayout);
        return container;

    }

    private void initView() {

        WebSettings settings = mWvDetail.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setLoadWithOverviewMode(true);
        settings.setBuiltInZoomControls(true);
        //settings.setUseWideViewPort(true);造成文字太小
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setAppCachePath(getCacheDir().getAbsolutePath() + "/webViewCache");
        settings.setAppCacheEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setDisplayZoomControls(false);
        mWvDetail.setWebChromeClient(new WebChromeClient());
    }

    @Override
    public void showNewsDetail(ZhiHuNewDetail detail) {
        String url = detail.getShare_url();
        boolean isEmpty = TextUtils.isEmpty(detail.getBody());
        String mBody = detail.getBody();
        List<String> scc = detail.getCss();
        // FIXME: 2017/9/8 有种方法就是拼html在html中加载图片，坑爹的，详情页要研究下
//        Glide.with(this).load(detail.getImage())
//                .apply(new RequestOptions().centerCrop()).into(mImageVIew);
        if (isEmpty) {
            mWvDetail.loadUrl(url);
        } else {
            //String data = WebUtil.buildHtmlWithCss(mBody, scc, false);
//            mWvDetail.loadDataWithBaseURL(WebUtil.BASE_URL, mBody, WebUtil.MIME_TYPE, WebUtil.ENCODING, WebUtil.FAIL_URL);
//            mWvDetail.loadData(mBody, "text/html", "utf-8");


            mBody = "<link rel=\"stylesheet\" type=\"text/css\" href=\"" + scc.get(0) + "\" />" + mBody;
// lets assume we have /assets/style.css file
            mWvDetail.loadDataWithBaseURL("file:///android_asset/", mBody, "text/html", "UTF-8", null);
        }
    }

    @Override
    public void showProgressDialog() {
        mWvDialog.setVisibility(View.VISIBLE);
        mWvDialog.start();
    }

    @Override
    public void hidProgressDialog() {
        mWvDialog.setVisibility(View.GONE);
        mWvDialog.stop();
    }

    @Override
    public void showErrorMessage(String message) {
        showMessageDialog(message, AppConstant.ICON_TYPE_FAIL);
    }

    @Override
    public void onViewPositionChanged(float fractionAnchor, float fractionScreen) {
        ivShadow.setAlpha(1 - fractionScreen);
    }

    @Override
    protected void onResume() {
        super.onResume();

        try {
            mWvDetail.getClass().getMethod("onResume").invoke(mWvDetail, (Object[]) null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            mWvDetail.getClass().getMethod("onPause").invoke(mWvDetail, (Object[]) null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        //webview内存泄露
        if (mWvDetail != null) {
            ((ViewGroup) mWvDetail.getParent()).removeView(mWvDetail);
            mWvDetail.destroy();
            mWvDetail = null;
        }
        mIZhiHuDetailActivityPresenter.unsubscribe();
        super.onDestroy();

    }

}
