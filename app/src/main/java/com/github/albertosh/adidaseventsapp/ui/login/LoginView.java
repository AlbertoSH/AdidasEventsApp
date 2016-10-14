package com.github.albertosh.adidaseventsapp.ui.login;

import com.hannesdorfmann.mosby.mvp.MvpView;

public interface LoginView extends MvpView {
    void dismissLoading();

    void loginSuccessful();

    void showInvalidUserPassword();

    void showNetworkError();

    String getEmail();

    String getPass();

    void showEmailIsEmptyError();

    void showPasswordIsEmptyError();

    void showLoading();
}
