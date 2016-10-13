package com.github.albertosh.adidasevents.sdk.usecases.auth.login;

import com.github.albertosh.adidasevents.sdk.models.LoginInfo;

import rx.Single;

public interface ILoginUseCase {

    Single<LoginInfo> execute(LoginUseCaseInput input);

}
