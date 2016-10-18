package com.github.albertosh.adidasevents.sdk.usecases.events.getall;

import com.github.albertosh.adidasevents.sdk.models.Event;

import java.util.List;



import rx.Observable;

public interface IGetAllEventsUseCase {

    Observable<List<Event>> execute();

    Observable<List<Event>> execute(String language);

}
