package com.github.albertosh.adidasevents.sdk.usecases.di.event;

import com.github.albertosh.adidasevents.sdk.repositories.event.IEventAllRepositoryRead;
import com.github.albertosh.adidasevents.sdk.repositories.event.IEventSingleRepositoryRead;
import com.github.albertosh.adidasevents.sdk.scopes.PerApplication;
import com.github.albertosh.adidasevents.sdk.usecases.events.getall.GetAllEventsUseCase;
import com.github.albertosh.adidasevents.sdk.usecases.events.getall.IGetAllEventsUseCase;
import com.github.albertosh.adidasevents.sdk.usecases.events.getsingle.GetSingleEventUseCase;
import com.github.albertosh.adidasevents.sdk.usecases.events.getsingle.IGetSingleEventUseCase;

import dagger.Module;
import dagger.Provides;

@Module
public class PublicEventUseCasesModule {

    @Provides
    @PerApplication
    IGetSingleEventUseCase getSingleEventUseCase(IEventSingleRepositoryRead repositoryRead) {
        return new GetSingleEventUseCase(repositoryRead);
    }

    @Provides
    @PerApplication
    IGetAllEventsUseCase getAllEventsUseCase(IEventAllRepositoryRead repositoryRead) {
        return new GetAllEventsUseCase(repositoryRead);
    }

}
