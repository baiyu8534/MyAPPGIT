package com.example.administrator.myappgit.presenter.implPresenter;

import android.content.Context;

import com.example.administrator.myappgit.IView.IMainActivity;
import com.example.administrator.myappgit.api.ApiManager;
import com.example.administrator.myappgit.bean.PixadayBean.PixabayListBean;
import com.example.administrator.myappgit.presenter.IMainActivityPresenter;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/7/18 0018.
 */

public class MainActivityPresenterImpl extends BasePresenterImpl implements IMainActivityPresenter {

    private IMainActivity mIMainActivity;
    private Context mContext;

    public MainActivityPresenterImpl(IMainActivity IMainActivity, Context context) {
        mIMainActivity = IMainActivity;
        mContext = context;
    }

    @Override
    public void getImgaes(int count, String q) {
        Subscription s = ApiManager.getInstance().getPixabayApiService().getImageList(count, q)
                .subscribeOn(Schedulers.io())
                .map(new Func1<PixabayListBean, List<PixabayListBean.HitsBean>>() {
                    @Override
                    public List<PixabayListBean.HitsBean> call(PixabayListBean pixabayListBean) {
                        if (pixabayListBean.getHits() != null) {
                            return pixabayListBean.getHits();
                        }
                        return null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<PixabayListBean.HitsBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mIMainActivity.setPic(null);
                    }

                    @Override
                    public void onNext(List<PixabayListBean.HitsBean> hitsBeen) {
                        mIMainActivity.setPic(hitsBeen);
                    }
                });
        addSubscription(s);
    }
}
