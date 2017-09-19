package com.example.administrator.myappgit.presenter.implPresenter;

import android.content.Context;

import com.example.administrator.myappgit.IView.IItemBGRollRvActivity;
import com.example.administrator.myappgit.R;
import com.example.administrator.myappgit.api.ApiManager;
import com.example.administrator.myappgit.bean.GankBean.GankImages;
import com.example.administrator.myappgit.presenter.IItemBGRollRvActivityPresenter;

import java.util.ArrayList;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 文件名：IItemBGRollRvActivityPresenterImpl
 * 描述：getImages(String count, String page)
 * 作者：白煜
 * 时间：2017/9/18 0018
 * 版权：
 */

public class IItemBGRollRvActivityPresenterImpl extends BasePresenterImpl implements IItemBGRollRvActivityPresenter {

    private IItemBGRollRvActivity mIItemBGRollRvActivity;
    private Context mContext;

    public IItemBGRollRvActivityPresenterImpl(IItemBGRollRvActivity IItemBGRollRvActivity, Context context) {
        mIItemBGRollRvActivity = IItemBGRollRvActivity;
        mContext = context;
    }

    @Override
    public void getImages(String count, String page) {

        Subscription s = ApiManager.getInstance().getGankApiService().getImages(count, page)
                .subscribeOn(Schedulers.io())
                .map(new Func1<GankImages, ArrayList<String>>() {
                    @Override
                    public ArrayList<String> call(GankImages gankImages) {
                        if (gankImages == null) {
                            informShowErrorMessage(mContext.getString(R.string.error_message_service_data_abnormal),
                                    mIItemBGRollRvActivity);
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
                        mIItemBGRollRvActivity.setRefreshing(false);
                        informShowErrorMessage(e, mIItemBGRollRvActivity);
                    }

                    @Override
                    public void onNext(ArrayList<String> strings) {
                        mIItemBGRollRvActivity.setRefreshing(false);
                        if (null != strings) {
                            mIItemBGRollRvActivity.upDataImages(strings);
                        }
                    }
                });
        addSubscription(s);

    }
}
