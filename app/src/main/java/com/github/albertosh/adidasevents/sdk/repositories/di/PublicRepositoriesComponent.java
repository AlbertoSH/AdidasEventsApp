package com.github.albertosh.adidasevents.sdk.repositories.di;

import com.github.albertosh.adidasevents.sdk.api.di.publicapi.PublicApiModule;
import com.github.albertosh.adidasevents.sdk.repositories.di.event.PublicEventRepositoryModule;
import com.github.albertosh.adidasevents.sdk.repositories.di.mapper.MapperModule;
import com.github.albertosh.adidasevents.sdk.repositories.event.IEventAllRepositoryRead;
import com.github.albertosh.adidasevents.sdk.repositories.event.IEventSingleRepositoryRead;
import com.github.albertosh.adidasevents.sdk.scopes.PerApplication;

import dagger.Component;

@PerApplication
@Component(modules = {
        PublicEventRepositoryModule.class,
        MapperModule.class,
        PublicApiModule.class
})
public interface PublicRepositoriesComponent {

    IEventSingleRepositoryRead eventSingleRepositoryRead();

    IEventAllRepositoryRead eventAllRepositoryRead();

}
