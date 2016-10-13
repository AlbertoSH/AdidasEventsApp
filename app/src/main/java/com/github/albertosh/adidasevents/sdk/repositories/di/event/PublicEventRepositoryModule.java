package com.github.albertosh.adidasevents.sdk.repositories.di.event;

import com.github.albertosh.adidasevents.sdk.api.publicapi.events.all.IGetAllEventsService;
import com.github.albertosh.adidasevents.sdk.api.publicapi.events.single.IGetSingleEventService;
import com.github.albertosh.adidasevents.sdk.repositories.event.EventAllRepositoryRead;
import com.github.albertosh.adidasevents.sdk.repositories.event.EventSingleRepositoryRead;
import com.github.albertosh.adidasevents.sdk.repositories.event.IEventAllRepositoryRead;
import com.github.albertosh.adidasevents.sdk.repositories.event.IEventSingleRepositoryRead;
import com.github.albertosh.adidasevents.sdk.repositories.mapper.EventMapper;
import com.github.albertosh.adidasevents.sdk.scopes.PerApplication;

import dagger.Module;
import dagger.Provides;

@Module
public class PublicEventRepositoryModule {

    @Provides
    @PerApplication
    IEventSingleRepositoryRead eventSingleRepositoryRead(IGetSingleEventService service,
                                                         EventMapper mapper) {
        return new EventSingleRepositoryRead(service, mapper);
    }

    @Provides
    @PerApplication
    IEventAllRepositoryRead eventAllRepositoryRead(IGetAllEventsService service,
                                                   EventMapper mapper) {
        return new EventAllRepositoryRead(service, mapper);
    }
}
