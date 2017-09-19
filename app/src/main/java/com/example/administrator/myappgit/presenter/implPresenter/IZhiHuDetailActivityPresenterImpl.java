package com.example.administrator.myappgit.presenter.implPresenter;

import android.content.Context;

import com.example.administrator.myappgit.IView.IZhiHuDetailActivity;
import com.example.administrator.myappgit.api.ApiManager;
import com.example.administrator.myappgit.bean.ZhiHuBean.ZhiHuNewDetail;
import com.example.administrator.myappgit.presenter.IZhiHuDetailActivityPresenter;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by baiyu on 2017/9/8.
 */

public class IZhiHuDetailActivityPresenterImpl extends BasePresenterImpl implements IZhiHuDetailActivityPresenter {

    private Context mContext;
    private IZhiHuDetailActivity mIZhiHuDetailActivity;

    public IZhiHuDetailActivityPresenterImpl(Context context, IZhiHuDetailActivity IZhiHuDetailActivity) {
        mContext = context;
        mIZhiHuDetailActivity = IZhiHuDetailActivity;
    }

    @Override
    public void getZhiHuNewsDetail(String NewsId) {
        mIZhiHuDetailActivity.showProgressDialog();
        Subscription s = ApiManager.getInstance().getZhuHuApiService().getNewsDetail(NewsId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZhiHuNewDetail>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mIZhiHuDetailActivity.hidProgressDialog();
                        informShowErrorMessage(e, mIZhiHuDetailActivity);
                    }

                    @Override
                    public void onNext(ZhiHuNewDetail detail) {
                        System.out.println(detail.toString());
                        mIZhiHuDetailActivity.showNewsDetail(detail);
                        mIZhiHuDetailActivity.hidProgressDialog();
                    }
                });
        addSubscription(s);
    }
}
