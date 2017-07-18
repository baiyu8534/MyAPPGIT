package com.example.administrator.myappgit.presenter.implPresenter;

import com.example.administrator.myappgit.presenter.BasePresenter;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2017/7/18 0018.
 */

public class BasePresenterImpl implements BasePresenter {

    private CompositeSubscription mCompositeSubscription;

    protected void addSubscription(Subscription s) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }
        this.mCompositeSubscription.add(s);
    }

    @Override
    public void unsubscribe() {
        if (this.mCompositeSubscription != null) {
            this.mCompositeSubscription.unsubscribe();
        }
    }
}
