package com.github.albertosh.adidasevents.sdk.api.publicapi.custom;

import java.util.Map;

import retrofit2.http.GET;
import rx.Single;

public interface ICustomService {

    @GET("custom")
    Single<Map<String, Object>> get();

}
