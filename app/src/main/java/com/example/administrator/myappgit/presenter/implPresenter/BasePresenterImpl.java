package com.example.administrator.myappgit.presenter.implPresenter;

import com.example.administrator.myappgit.IView.IBaseView;
import com.example.administrator.myappgit.presenter.BasePresenter;
import com.example.administrator.myappgit.utils.NetWorkExceptionUtil;

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


    /**
     * 解除订阅
     */
    @Override
    public void unsubscribe() {
        if (this.mCompositeSubscription != null) {
            this.mCompositeSubscription.unsubscribe();
        }
    }

    /**
     * 网络请求出错时，统一通知view显示dialog
     *
     * @param e     Throwable
     * @param iView IBaseView
     */
    @Override
    public void informShowErrorMessage(Throwable e, IBaseView iView) {
        if (e instanceof NetWorkExceptionUtil.ResponeThrowable) {
            iView.showNetworkRequestErrorMessage(((NetWorkExceptionUtil.ResponeThrowable) e).message);
        } else {
            iView.showNetworkRequestErrorMessage(NetWorkExceptionUtil.handleException(e).message);
        }
    }

    /**
     * 网络请求出错时，统一通知view显示dialog
     *
     * @param message message
     * @param iView   IBaseView
     */
    @Override
    public void informShowErrorMessage(String message, IBaseView iView) {
        iView.showNetworkRequestErrorMessage(message);
    }

}
