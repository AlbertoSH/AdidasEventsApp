package com.github.albertosh.adidasevents.sdk.api.privateapi.events.enroll;

import com.github.albertosh.adidasevents.sdk.api.BaseService;
import com.github.albertosh.adidasevents.sdk.api.ServiceError;
import com.github.albertosh.adidasevents.sdk.api.publicapi.events.EventData;

import rx.Single;

public class EnrollService
        extends BaseService<String, EventData>
        implements IEnrollService {

    private final static int INVALID_EMAIL_OR_PASSWORD = -1;

    private final RetrofitEnroll retrofitEnroll;

    public EnrollService(RetrofitEnroll retrofitEnroll) {
        this.retrofitEnroll = retrofitEnroll;
    }

    @Override
    protected Single<EventData> performNetworkCall(String eventId) {
        return retrofitEnroll.enroll(eventId, "{}");
    }

    @Override
    protected ServiceError mapErrorCodeToException(int code) {
        // In this case the only error can be eventNotFound
        return EnrollServiceError.eventNotFound;
    }

}
