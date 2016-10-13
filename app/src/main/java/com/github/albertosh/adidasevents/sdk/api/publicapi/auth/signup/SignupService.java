package com.github.albertosh.adidasevents.sdk.api.publicapi.auth.signup;

import com.github.albertosh.adidasevents.sdk.api.BaseService;
import com.github.albertosh.adidasevents.sdk.api.ServiceError;

import rx.Single;

public class SignupService
        extends BaseService<SignupServiceInput, SignupServiceOutput>
        implements ISignupService {

    private final static int EMAIL_ALREADY_EXISTS_CODE = -1;

    private final RetrofitSignup retrofitSignup;

    public SignupService(RetrofitSignup retrofitSignup) {
        this.retrofitSignup = retrofitSignup;
    }

    @Override
    protected Single<SignupServiceOutput> performNetworkCall(SignupServiceInput signupServiceInput) {
        return retrofitSignup.signup(signupServiceInput);
    }

    @Override
    protected ServiceError mapErrorCodeToException(int code) {
        switch (code) {
            case EMAIL_ALREADY_EXISTS_CODE:
                return SignupServiceError.emailAlreadyRegistered;
            default:
                return super.mapErrorCodeToException(code);
        }
    }
}
