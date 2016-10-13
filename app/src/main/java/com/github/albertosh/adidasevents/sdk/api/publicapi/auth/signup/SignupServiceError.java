package com.github.albertosh.adidasevents.sdk.api.publicapi.auth.signup;

import com.github.albertosh.adidasevents.sdk.api.ServiceError;

public class SignupServiceError extends ServiceError {

    public static final SignupServiceError emailAlreadyRegistered = new SignupServiceError("Email is already registered");

    private SignupServiceError(String message) {
        super(message);
    }

}
