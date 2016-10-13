package com.github.albertosh.adidaseventsapp.ui.base;

import android.support.annotation.CallSuper;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

public class BaseLcePresenter
        <V extends MvpLceView<D>, D>
        extends BasePresenter<V> {

    protected D data = null;
    private Throwable error;

    @Override
    public void onViewStateInstanceRestored(boolean instanceStateRetained) {
        if (isViewAttached()) {
            if (data != null) {
                getView().setData(data);
            } else if (error != null) {
                getView().showError(error, false);
            }
        }
    }

    @CallSuper
    protected void showData(D data) {
        this.data = data;
        this.error = null;
        if (isViewAttached()) {
            getView().setData(data);
            getView().showContent();
        }
    }

    @CallSuper
    protected void showError(Throwable error) {
        this.data = null;
        this.error = error;
        if (isViewAttached()) {
            getView().showError(error, false);
        }
    }

}
