package com.github.albertosh.adidasevents.sdk.api.privateapi.events.enroll;

import com.github.albertosh.adidasevents.sdk.api.ServiceError;

public class EnrollServiceError extends ServiceError {

    public static final EnrollServiceError eventNotFound =
            new EnrollServiceError("Event not found");

    private EnrollServiceError(String message) {
        super(message);
    }

}
