package com.github.albertosh.adidasevents.sdk.api.publicapi.auth.login;

import com.github.albertosh.adidasevents.sdk.api.ServiceError;

public class LoginServiceError extends ServiceError {

    public static final LoginServiceError invalidEmailOrPassword =
            new LoginServiceError("Invalid email or password");

    private LoginServiceError(String message) {
        super(message);
    }

}
