package com.example.administrator.myappgit.presenter.implPresenter;

import android.content.Context;

import com.example.administrator.myappgit.IView.IZhiHuFragment;
import com.example.administrator.myappgit.api.ApiManager;
import com.example.administrator.myappgit.bean.ZhiHuBean.NewsListBean;
import com.example.administrator.myappgit.presenter.IZhiHuFragmentPresenter;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/7/19 0019.
 */

public class ZhiHuFragmentPresenterImpl extends BasePresenterImpl implements IZhiHuFragmentPresenter {

    private Context mContext;
    private IZhiHuFragment mIZhiHuFragment;

    public ZhiHuFragmentPresenterImpl(Context context, IZhiHuFragment IZhiHuFragment) {
        mContext = context;
        mIZhiHuFragment = IZhiHuFragment;
    }

    /**
     * 获取news列表
     */
    @Override
    public void getNewsList() {
        mIZhiHuFragment.showProgressDialog();
        Subscription s = ApiManager.getInstance().getZhuHuApiService().getNews()
                .subscribeOn(Schedulers.io())
                .map(new Func1<NewsListBean, NewsListBean>() {
                    @Override
                    public NewsListBean call(NewsListBean newsListBean) {
                        String newsDate = newsListBean.getDate();
                        for (NewsListBean.StoriesBean storiesBean : newsListBean.getStories()) {
                            storiesBean.setDate(newsDate);
                        }
                        return newsListBean;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsListBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mIZhiHuFragment.hidProgressDialog();
                        mIZhiHuFragment.showErrorMessage();
                    }

                    @Override
                    public void onNext(NewsListBean newsListBean) {
                        mIZhiHuFragment.hidProgressDialog();
                        mIZhiHuFragment.upDataNewsList(newsListBean);
                    }
                });
        addSubscription(s);
    }

    /**
     * 获取更多news，上拉加载
     *
     * @param date 日期 如20170505
     */
    @Override
    public void getMoreNews(String date) {
        Subscription s = ApiManager.getInstance().getZhuHuApiService().getMoreNews(date)
                .subscribeOn(Schedulers.io())
                .map(new Func1<NewsListBean, NewsListBean>() {
                    @Override
                    public NewsListBean call(NewsListBean newsListBean) {
                        String newsDate = newsListBean.getDate();
                        for (NewsListBean.StoriesBean storiesBean : newsListBean.getStories()) {
                            storiesBean.setDate(newsDate);
                        }
                        return newsListBean;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsListBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mIZhiHuFragment.hidProgressDialog();
                        mIZhiHuFragment.showErrorMessage();
                    }

                    @Override
                    public void onNext(NewsListBean newsListBean) {
                        mIZhiHuFragment.hidProgressDialog();
                        mIZhiHuFragment.upDataNewsList(newsListBean);
                    }

                });
        addSubscription(s);
    }

    @Override
    public void getNewsFormCache() {

    }
}
