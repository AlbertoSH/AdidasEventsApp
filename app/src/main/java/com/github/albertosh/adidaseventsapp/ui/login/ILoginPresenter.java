package com.github.albertosh.adidaseventsapp.ui.login;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;

public interface ILoginPresenter extends MvpPresenter<LoginView> {

    void doLogin();

    void forgotPassword();

    void doSignup();
}
