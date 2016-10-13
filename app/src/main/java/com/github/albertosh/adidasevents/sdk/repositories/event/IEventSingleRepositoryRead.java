package com.github.albertosh.adidasevents.sdk.repositories.event;

import com.github.albertosh.adidasevents.sdk.models.Event;
import com.github.albertosh.adidasevents.sdk.repositories.ISingleRepositoryRead;

import javax.annotation.Nullable;

import rx.Observable;

public interface IEventSingleRepositoryRead extends ISingleRepositoryRead<Event> {

    public Observable<Event> read(String id, @Nullable String language);

}
