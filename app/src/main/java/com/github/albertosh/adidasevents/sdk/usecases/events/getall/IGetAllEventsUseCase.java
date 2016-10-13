package com.github.albertosh.adidasevents.sdk.usecases.events.getall;

import com.github.albertosh.adidasevents.sdk.models.Event;

import java.util.List;

import javax.annotation.Nullable;

import rx.Observable;

public interface IGetAllEventsUseCase {

    Observable<List<Event>> execute();

    Observable<List<Event>> execute(@Nullable String language);

}
