package com.example.administrator.myappgit.presenter.implPresenter;

import android.content.Context;

import com.example.administrator.myappgit.IView.IZhiHuFragment;
import com.example.administrator.myappgit.api.ApiManager;
import com.example.administrator.myappgit.bean.ZhiHuBean.NewsListBean;
import com.example.administrator.myappgit.presenter.IZhiHuFragmentPresenter;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/7/19 0019.
 */

public class ZhiHuFragmentPresenterImpl extends BasePresenterImpl implements IZhiHuFragmentPresenter{

    private Context mContext;
    private IZhiHuFragment mIZhiHuFragment;

    public ZhiHuFragmentPresenterImpl(Context context, IZhiHuFragment IZhiHuFragment) {
        mContext = context;
        mIZhiHuFragment = IZhiHuFragment;
    }

    @Override
    public void getNewsList() {
        ApiManager.getInstance().getZhuHuApiService().getNewsList()
                .subscribeOn(Schedulers.io())
                .map(new Func1<NewsListBean, List<NewsListBean.StoriesBean>>() {
                    @Override
                    public List<NewsListBean.StoriesBean> call(NewsListBean newsListBean) {
                        if(newsListBean.getStories() != null){
                            return newsListBean.getStories();
                        }
                        return null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<NewsListBean.StoriesBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<NewsListBean.StoriesBean> storiesBeen) {
                        mIZhiHuFragment.showDataList(storiesBeen);
                    }
                });
    }
}