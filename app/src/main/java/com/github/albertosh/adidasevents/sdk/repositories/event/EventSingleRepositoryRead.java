package com.github.albertosh.adidasevents.sdk.repositories.event;

import com.github.albertosh.adidasevents.sdk.api.publicapi.events.single.GetSingleEventServiceInput;
import com.github.albertosh.adidasevents.sdk.api.publicapi.events.single.IGetSingleEventService;
import com.github.albertosh.adidasevents.sdk.models.Event;
import com.github.albertosh.adidasevents.sdk.repositories.mapper.EventMapper;

import javax.annotation.Nullable;

import rx.Observable;

public class EventSingleRepositoryRead implements IEventSingleRepositoryRead {

    private final IGetSingleEventService service;
    private final EventMapper mapper;

    public EventSingleRepositoryRead(IGetSingleEventService service, EventMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public Observable<Event> read(String id) {
        return read(id, null);
    }

    @Override
    public Observable<Event> read(String id, @Nullable String language) {
        GetSingleEventServiceInput input = new GetSingleEventServiceInput.Builder()
                .id(id)
                .language(language)
                .build();
        return service.execute(input)
                .map(mapper::map)
                .toObservable();
    }
}
