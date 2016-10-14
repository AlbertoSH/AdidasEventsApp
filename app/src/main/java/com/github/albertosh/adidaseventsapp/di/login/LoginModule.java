package com.github.albertosh.adidaseventsapp.di.login;

import com.github.albertosh.adidasevents.sdk.usecases.auth.login.ILoginUseCase;
import com.github.albertosh.adidasevents.sdk.usecases.di.UserComponentManager;
import com.github.albertosh.adidaseventsapp.di.PerController;
import com.github.albertosh.adidaseventsapp.ui.login.ILoginPresenter;
import com.github.albertosh.adidaseventsapp.ui.login.LoginPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    @Provides @PerController
    ILoginPresenter loginPresenter(ILoginUseCase loginUseCase, UserComponentManager userComponentManager) {
        return new LoginPresenter(loginUseCase, userComponentManager);
    }

}
