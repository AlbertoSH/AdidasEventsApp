package com.github.albertosh.adidasevents.sdk.usecases.events.enroll;

import com.github.albertosh.adidasevents.sdk.api.privateapi.events.enroll.IEnrollService;
import com.github.albertosh.adidasevents.sdk.models.Event;
import com.github.albertosh.adidasevents.sdk.repositories.mapper.EventMapper;

import javax.annotation.Nullable;

import rx.Single;

public class EnrollEventUseCase implements IEnrollEventUseCase{

    private final IEnrollService enrollService;
    private final EventMapper eventMapper;

    public EnrollEventUseCase(IEnrollService enrollService, EventMapper eventMapper) {
        this.enrollService = enrollService;
        this.eventMapper = eventMapper;
    }


    @Override
    public Single<Event> execute(@Nullable String eventId) {
        return enrollService.execute(eventId)
                .map(eventMapper::map);
    }
}
