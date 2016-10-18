package com.github.albertosh.adidasevents.sdk.repositories.event;

import com.github.albertosh.adidasevents.sdk.models.Event;
import com.github.albertosh.adidasevents.sdk.repositories.IAllRepositoryRead;

import java.util.List;



import rx.Observable;

public interface IEventAllRepositoryRead extends IAllRepositoryRead<Event> {

    public Observable<List<Event>> read(String language);

}
