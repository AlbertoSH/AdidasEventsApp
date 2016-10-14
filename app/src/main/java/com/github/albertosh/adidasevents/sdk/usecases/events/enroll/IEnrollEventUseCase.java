package com.github.albertosh.adidasevents.sdk.usecases.events.enroll;

import com.github.albertosh.adidasevents.sdk.models.Event;

import javax.annotation.Nullable;

import rx.Single;

public interface IEnrollEventUseCase {

    Single<Event> execute(@Nullable String eventId);

}
