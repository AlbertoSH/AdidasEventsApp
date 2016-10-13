package com.github.albertosh.adidasevents.sdk.api;

public class ServiceError extends RuntimeException {

    public static final ServiceError networkError = new ServiceError("NetworkError");

    public static final ServiceError unknownError = new ServiceError("UnknownError");

    protected ServiceError(String message) {
        super(message);
    }
}
