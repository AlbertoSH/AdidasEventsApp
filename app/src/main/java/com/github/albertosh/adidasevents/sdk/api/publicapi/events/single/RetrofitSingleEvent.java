package com.github.albertosh.adidasevents.sdk.api.publicapi.events.single;

import com.github.albertosh.adidasevents.sdk.api.publicapi.events.EventData;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Single;

public interface RetrofitSingleEvent {

    @GET("event/{eventId}")
    Single<EventData> getSingle(
            @Path("eventId") String eventId,
            @Query("language") String language
    );

}
