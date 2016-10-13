package com.github.albertosh.adidasevents.sdk.usecases.events.getsingle;

import com.github.albertosh.adidasevents.sdk.models.Event;
import com.github.albertosh.adidasevents.sdk.repositories.event.IEventSingleRepositoryRead;

import rx.Observable;

public class GetSingleEventUseCase implements IGetSingleEventUseCase {

    private final IEventSingleRepositoryRead eventSingleRepositoryRead;

    public GetSingleEventUseCase(IEventSingleRepositoryRead eventSingleRepositoryRead) {
        this.eventSingleRepositoryRead = eventSingleRepositoryRead;
    }

    @Override
    public Observable<Event> execute(GetSingleEventUseCaseInput input) {
        return eventSingleRepositoryRead.read(
                input.getId(),
                input.getLanguage().orElse(null)
        );
    }

}
