package com.github.albertosh.adidasevents.sdk.usecases.auth.signup;

import com.github.albertosh.adidasevents.sdk.models.LoginInfo;

import rx.Single;

public interface ISignupUseCase {

    Single<LoginInfo> execute(SignupUseCaseInput input);

}
