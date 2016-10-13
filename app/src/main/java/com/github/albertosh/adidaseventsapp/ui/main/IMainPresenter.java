package com.github.albertosh.adidaseventsapp.ui.main;

import com.github.albertosh.adidaseventsapp.ui.base.IBasePresenter;

public interface IMainPresenter extends IBasePresenter<MainView> {

    void loadEvents(boolean twoPaneView);

}
