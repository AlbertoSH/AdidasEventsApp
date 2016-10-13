package com.github.albertosh.adidasevents.sdk.api.di.publicapi.events;

import com.github.albertosh.adidasevents.sdk.api.publicapi.events.all.GetAllEventsService;
import com.github.albertosh.adidasevents.sdk.api.publicapi.events.all.IGetAllEventsService;
import com.github.albertosh.adidasevents.sdk.api.publicapi.events.all.RetrofitAllEvents;
import com.github.albertosh.adidasevents.sdk.api.publicapi.events.single.GetSingleEventService;
import com.github.albertosh.adidasevents.sdk.api.publicapi.events.single.IGetSingleEventService;
import com.github.albertosh.adidasevents.sdk.api.publicapi.events.single.RetrofitSingleEvent;
import com.github.albertosh.adidasevents.sdk.scopes.PerApplication;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class EventsModule {

    @Provides
    @PerApplication
    IGetAllEventsService getAllEventsService(RetrofitAllEvents retrofitAllEvents) {
        return new GetAllEventsService(retrofitAllEvents);
    }

    @Provides
    @PerApplication
    RetrofitAllEvents retrofitAllEvents(Retrofit retrofit) {
        return retrofit.create(RetrofitAllEvents.class);
    }

    @Provides
    @PerApplication
    IGetSingleEventService getSingleEventService(RetrofitSingleEvent retrofitSingleEvent) {
        return new GetSingleEventService(retrofitSingleEvent);
    }

    @Provides
    @PerApplication
    RetrofitSingleEvent retrofitSingleEvent(Retrofit retrofit) {
        return retrofit.create(RetrofitSingleEvent.class);
    }

}
