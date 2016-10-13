package com.github.albertosh.adidasevents.sdk.usecases.events.getall;

import com.github.albertosh.adidasevents.sdk.models.Event;
import com.github.albertosh.adidasevents.sdk.repositories.event.IEventAllRepositoryRead;

import java.util.List;

import javax.annotation.Nullable;

import rx.Observable;

public class GetAllEventsUseCase implements IGetAllEventsUseCase {

    private final IEventAllRepositoryRead eventAllRepositoryRead;

    public GetAllEventsUseCase(IEventAllRepositoryRead eventAllRepositoryRead) {
        this.eventAllRepositoryRead = eventAllRepositoryRead;
    }

    @Override
    public Observable<List<Event>> execute() {
        return execute(null);
    }

    @Override
    public Observable<List<Event>> execute(@Nullable String language) {
        return eventAllRepositoryRead.read(language);
    }

}
