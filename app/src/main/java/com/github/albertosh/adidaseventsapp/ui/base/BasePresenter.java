package com.github.albertosh.adidaseventsapp.ui.base;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class BasePresenter
        <V extends MvpView>
        extends MvpBasePresenter<V>
        implements IBasePresenter<V> {

    protected CompositeSubscription subscriptions;

    public BasePresenter() {
        subscriptions = new CompositeSubscription();
    }

    protected void addSubscription(Subscription subscription) {
        this.subscriptions.add(subscription);
    }

    @Override
    public final void attachView(V view) {
        super.attachView(view);
        onViewAttached(view);
    }

    protected void onViewAttached(V view) {}

    @Override
    public final void detachView(boolean retainInstance) {
        onViewBeingDetached(getView());
        super.detachView(retainInstance);
    }

    protected void onViewBeingDetached(V view) {}

    @Override
    public void onViewStateInstanceRestored(boolean instanceStateRetained) {}

    @Override
    public void destroy() {
        subscriptions.unsubscribe();
        subscriptions = null;
    }

}
