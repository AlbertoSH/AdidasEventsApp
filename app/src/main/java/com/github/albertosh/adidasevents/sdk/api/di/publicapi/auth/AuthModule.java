package com.github.albertosh.adidasevents.sdk.api.di.publicapi.auth;

import com.github.albertosh.adidasevents.sdk.api.publicapi.auth.login.ILoginService;
import com.github.albertosh.adidasevents.sdk.api.publicapi.auth.login.LoginService;
import com.github.albertosh.adidasevents.sdk.api.publicapi.auth.login.RetrofitLogin;
import com.github.albertosh.adidasevents.sdk.api.publicapi.auth.signup.ISignupService;
import com.github.albertosh.adidasevents.sdk.api.publicapi.auth.signup.RetrofitSignup;
import com.github.albertosh.adidasevents.sdk.api.publicapi.auth.signup.SignupService;
import com.github.albertosh.adidasevents.sdk.scopes.PerApplication;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class AuthModule {

    @Provides
    @PerApplication
    ILoginService loginService(RetrofitLogin retrofitLogin) {
        return new LoginService(retrofitLogin);
    }

    @Provides
    @PerApplication
    RetrofitLogin retrofitLoginService(Retrofit retrofit) {
        return retrofit.create(RetrofitLogin.class);
    }

    @Provides
    @PerApplication
    ISignupService signupService(RetrofitSignup retrofitSignup) {
        return new SignupService(retrofitSignup);
    }

    @Provides
    @PerApplication
    RetrofitSignup retrofitSignupService(Retrofit retrofit) {
        return retrofit.create(RetrofitSignup.class);
    }

}
