package com.github.albertosh.adidaseventsapp.ui.base;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

public interface IBasePresenter<V extends MvpView> extends MvpPresenter<V> {

    void onViewStateInstanceRestored(boolean instanceStateRetained);

    public void destroy();

}
