package com.github.albertosh.adidaseventsapp.ui.login;

import android.util.Log;

import com.github.albertosh.adidasevents.sdk.api.publicapi.auth.login.LoginServiceError;
import com.github.albertosh.adidasevents.sdk.usecases.auth.login.ILoginUseCase;
import com.github.albertosh.adidasevents.sdk.usecases.auth.login.LoginUseCaseInput;
import com.github.albertosh.adidasevents.sdk.usecases.di.UserComponentManager;
import com.github.albertosh.adidaseventsapp.ui.base.BasePresenter;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginPresenter
        extends BasePresenter<LoginView>
        implements ILoginPresenter {

    private final ILoginUseCase loginUseCase;
    private final UserComponentManager userComponentManager;

    public LoginPresenter(ILoginUseCase loginUseCase, UserComponentManager userComponentManager) {
        this.loginUseCase = loginUseCase;
        this.userComponentManager = userComponentManager;
    }

    @Override
    public void doLogin() {
        if (isViewAttached()) {
            String email = getView().getEmail();
            String pass = getView().getPass();

            boolean inputError = false;
            if (email.isEmpty()) {
                getView().showEmailIsEmptyError();
                inputError = true;
            }
            if (pass.isEmpty()) {
                getView().showPasswordIsEmptyError();
                inputError = true;
            }

            if (!inputError) {
                getView().showLoading();
                LoginUseCaseInput input = new LoginUseCaseInput.Builder()
                        .email(email)
                        .password(pass)
                        .build();
                addSubscription(
                        loginUseCase.execute(input)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(loginInfo -> {
                                    if (isViewAttached()) {
                                        getView().loginSuccessful();
                                    }
                                }, error -> {
                                    if (isViewAttached()) {
                                        getView().dismissLoading();
                                        if (error == LoginServiceError.invalidEmailOrPassword)
                                            getView().showInvalidUserPassword();
                                        else if (error == LoginServiceError.networkError) {
                                            getView().showNetworkError();
                                        } else {
                                            throw new RuntimeException(error);
                                        }
                                    }
                                })
                );
            }
        }
    }

    @Override
    public void forgotPassword() {
        // TODO
        Log.d("Login", "TODO: forgotPassword");
    }

    @Override
    public void doSignup() {
// TODO
        Log.d("Login", "TODO: doSignup");
    }
}
