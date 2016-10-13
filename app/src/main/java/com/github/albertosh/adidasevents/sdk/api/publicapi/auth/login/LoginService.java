package com.github.albertosh.adidasevents.sdk.api.publicapi.auth.login;

import com.github.albertosh.adidasevents.sdk.api.BaseService;
import com.github.albertosh.adidasevents.sdk.api.ServiceError;

import rx.Single;

public class LoginService
        extends BaseService<LoginServiceInput, LoginServiceOutput>
        implements ILoginService {

    private final static int INVALID_EMAIL_OR_PASSWORD = -1;

    private final RetrofitLogin retrofitLogin;

    public LoginService(RetrofitLogin retrofitLogin) {
        this.retrofitLogin = retrofitLogin;
    }

    @Override
    protected Single<LoginServiceOutput> performNetworkCall(LoginServiceInput loginServiceInput) {
        return retrofitLogin.login(loginServiceInput);
    }

    @Override
    protected ServiceError mapErrorCodeToException(int code) {
        switch (code) {
            case INVALID_EMAIL_OR_PASSWORD:
                return LoginServiceError.invalidEmailOrPassword;
            default:
                return super.mapErrorCodeToException(code);
        }
    }

}
