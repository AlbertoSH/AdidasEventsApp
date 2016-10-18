package com.github.albertosh.adidasevents.sdk.repositories.event;

import com.github.albertosh.adidasevents.sdk.models.Event;
import com.github.albertosh.adidasevents.sdk.repositories.ISingleRepositoryRead;



import rx.Observable;

public interface IEventSingleRepositoryRead extends ISingleRepositoryRead<Event> {

    public Observable<Event> read(String id, String language);

}
