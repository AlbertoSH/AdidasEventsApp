package com.github.albertosh.adidasevents.sdk.usecases.di.event;

import com.github.albertosh.adidasevents.sdk.api.privateapi.events.enroll.IEnrollService;
import com.github.albertosh.adidasevents.sdk.repositories.di.mapper.MapperModule;
import com.github.albertosh.adidasevents.sdk.repositories.mapper.EventMapper;
import com.github.albertosh.adidasevents.sdk.scopes.PerUser;
import com.github.albertosh.adidasevents.sdk.usecases.events.enroll.EnrollEventUseCase;
import com.github.albertosh.adidasevents.sdk.usecases.events.enroll.IEnrollEventUseCase;

import dagger.Module;
import dagger.Provides;

@Module(includes = {
        MapperModule.class
})
public class PrivateEventUseCasesModule {

    @Provides
    @PerUser
    IEnrollEventUseCase enrollEventUseCase(IEnrollService enrollService, EventMapper eventMapper) {
        return new EnrollEventUseCase(enrollService, eventMapper);
    }

}
