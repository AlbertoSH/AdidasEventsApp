package com.github.albertosh.adidasevents.sdk.usecases.events.enroll;

import com.github.albertosh.adidasevents.sdk.models.Event;



import rx.Single;

public interface IEnrollEventUseCase {

    Single<Event> execute(String eventId);

}
