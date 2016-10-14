package com.github.albertosh.adidasevents.sdk.api.di.privateapi.events;

import com.github.albertosh.adidasevents.sdk.api.di.privateapi.PrivateApi;
import com.github.albertosh.adidasevents.sdk.api.privateapi.events.enroll.EnrollService;
import com.github.albertosh.adidasevents.sdk.api.privateapi.events.enroll.IEnrollService;
import com.github.albertosh.adidasevents.sdk.api.privateapi.events.enroll.RetrofitEnroll;
import com.github.albertosh.adidasevents.sdk.scopes.PerUser;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class PrivateEventsApiModule {

    @Provides
    @PerUser
    IEnrollService enrollService(RetrofitEnroll retrofitEnroll) {
        return new EnrollService(retrofitEnroll);
    }

    @Provides
    @PerUser
    RetrofitEnroll retrofitEnroll(@PrivateApi Retrofit retrofit) {
        return retrofit.create(RetrofitEnroll.class);
    }

}
