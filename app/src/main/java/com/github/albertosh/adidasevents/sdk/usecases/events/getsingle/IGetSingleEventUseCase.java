package com.github.albertosh.adidasevents.sdk.usecases.events.getsingle;

import com.github.albertosh.adidasevents.sdk.models.Event;

import rx.Observable;

public interface IGetSingleEventUseCase {

    Observable<Event> execute(GetSingleEventUseCaseInput input);

}
