package com.github.albertosh.adidasevents.sdk.api.publicapi.events.all;

import com.github.albertosh.adidasevents.sdk.api.publicapi.events.EventData;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Single;

public interface RetrofitAllEvents {

    @GET("event")
    Single<List<EventData>> getAll(
            @Query("page") Integer page,
            @Query("pageSize") Integer pageSize,
            @Query("language") String language
    );

}
