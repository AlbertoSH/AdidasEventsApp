package com.github.albertosh.adidasevents.sdk.api.privateapi.events.enroll;

import com.github.albertosh.adidasevents.sdk.api.publicapi.events.EventData;

import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Single;

public interface RetrofitEnroll {

    @POST("event/{eventId}/enroll")
    Single<EventData> enroll(@Path("eventId") String eventId, @Body String body);

}
