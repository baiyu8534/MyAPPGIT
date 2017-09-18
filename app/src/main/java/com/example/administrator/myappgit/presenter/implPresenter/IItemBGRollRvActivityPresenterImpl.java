package com.example.administrator.myappgit.presenter.implPresenter;

import com.example.administrator.myappgit.IView.IItemBGRollRvActivity;
import com.example.administrator.myappgit.api.ApiManager;
import com.example.administrator.myappgit.bean.GankBean.GankImages;
import com.example.administrator.myappgit.presenter.IItemBGRollRvActivityPresenter;
import com.example.administrator.myappgit.utils.NetWorkExceptionUtil;

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

    public IItemBGRollRvActivityPresenterImpl(IItemBGRollRvActivity IItemBGRollRvActivity) {
        mIItemBGRollRvActivity = IItemBGRollRvActivity;
    }

    @Override
    public void getImages(String count, String page) {

        mIItemBGRollRvActivity.setRefreshing(true);
        Subscription s = ApiManager.getInstance().getGankApiService().getImages(count, page)
                .subscribeOn(Schedulers.io())
                .map(new Func1<GankImages, ArrayList<String>>() {
                    @Override
                    public ArrayList<String> call(GankImages gankImages) {
                        if (gankImages == null) {
                            // FIXME: 2017/9/15 可以搞个提示，让用户刷新
                        } else {
                            ArrayList<String> datas = new ArrayList<>();
                            for (GankImages.ResultsBean resultsBean : gankImages.getResults()) {
                                datas.add(resultsBean.getUrl());
                            }
                            return datas;
                        }
                        return null;
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        // FIXME: 2017/9/15 提示刷新失败
                        mIItemBGRollRvActivity.setRefreshing(false);
                        if (e instanceof NetWorkExceptionUtil.ResponeThrowable) {
                            System.out.println("message111"+((NetWorkExceptionUtil.ResponeThrowable) e).message);
//                            onError((NetWorkExceptionUtil.ResponeThrowable) e);
                        } else {
                            System.out.println("message222"+NetWorkExceptionUtil.handleException(e).message);
//                            onError(NetWorkExceptionUtil.handleException(e));
                        }
                    }

                    @Override
                    public void onNext(ArrayList<String> strings) {
                        mIItemBGRollRvActivity.upDataImages(strings);
                        mIItemBGRollRvActivity.setRefreshing(false);
                    }
                });
        addSubscription(s);

    }
}
