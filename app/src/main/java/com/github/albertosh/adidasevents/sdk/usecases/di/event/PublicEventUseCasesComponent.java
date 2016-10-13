package com.github.albertosh.adidasevents.sdk.usecases.di.event;

import com.github.albertosh.adidasevents.sdk.api.di.publicapi.PublicApiModule;
import com.github.albertosh.adidasevents.sdk.repositories.di.event.PublicEventRepositoryModule;
import com.github.albertosh.adidasevents.sdk.repositories.di.mapper.MapperModule;
import com.github.albertosh.adidasevents.sdk.scopes.PerApplication;
import com.github.albertosh.adidasevents.sdk.usecases.events.getall.IGetAllEventsUseCase;
import com.github.albertosh.adidasevents.sdk.usecases.events.getsingle.IGetSingleEventUseCase;

import dagger.Component;

@PerApplication
@Component(modules = {
        PublicEventUseCasesModule.class,
        PublicEventRepositoryModule.class,
        MapperModule.class,
        PublicApiModule.class
})
public interface PublicEventUseCasesComponent {

    IGetAllEventsUseCase getAllEventsUseCase();

    IGetSingleEventUseCase getSingleEventUseCase();

}
