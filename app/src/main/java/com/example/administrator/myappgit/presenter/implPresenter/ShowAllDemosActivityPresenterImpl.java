package com.example.administrator.myappgit.presenter.implPresenter;

import android.content.Context;

import com.example.administrator.myappgit.IView.IShowAllDemosActivity;
import com.example.administrator.myappgit.R;
import com.example.administrator.myappgit.api.ApiManager;
import com.example.administrator.myappgit.bean.GankBean.GankImages;
import com.example.administrator.myappgit.presenter.IShowAllDemosActivityPresenter;

import java.util.ArrayList;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by baiyu on 2017/9/15.
 */

public class ShowAllDemosActivityPresenterImpl extends BasePresenterImpl implements IShowAllDemosActivityPresenter {

    private Context mContext;
    private IShowAllDemosActivity mShowAllDemosActivity;

    public ShowAllDemosActivityPresenterImpl(Context context, IShowAllDemosActivity showAllDemosActivity) {
        mContext = context;
        mShowAllDemosActivity = showAllDemosActivity;
    }

    @Override
    public void getImages(String count, String page) {
        mShowAllDemosActivity.setRefreshing(true);
        // FIXME: 2017/9/15 主页图片，可以在启动页加载一次，下次就直接用缓，这里就不弄加载框了
        Subscription s = ApiManager.getInstance().getGankApiService().getImages(count, page)
                .subscribeOn(Schedulers.io())
                .map(new Func1<GankImages, ArrayList<String>>() {
                    @Override
                    public ArrayList<String> call(GankImages gankImages) {
                        if (gankImages == null) {
                            informShowErrorMessage(mContext.getString(R.string.error_message_service_data_abnormal),
                                    mShowAllDemosActivity);
                            return null;
                        } else {
                            ArrayList<String> datas = new ArrayList<>();
                            for (GankImages.ResultsBean resultsBean : gankImages.getResults()) {
                                datas.add(resultsBean.getUrl());
                            }
                            return datas;
                        }
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mShowAllDemosActivity.setRefreshing(false);
                        informShowErrorMessage(e, mShowAllDemosActivity);
                    }

                    @Override
                    public void onNext(ArrayList<String> strings) {
                        mShowAllDemosActivity.setRefreshing(false);
                        if (null != strings) {
                            mShowAllDemosActivity.upDataImages(strings);
                        }
                    }
                });
        addSubscription(s);
    }
}
