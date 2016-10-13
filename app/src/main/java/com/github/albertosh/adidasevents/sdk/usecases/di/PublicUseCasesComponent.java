package com.github.albertosh.adidasevents.sdk.usecases.di;

import com.github.albertosh.adidasevents.sdk.api.di.publicapi.PublicApiModule;
import com.github.albertosh.adidasevents.sdk.repositories.di.event.PublicEventRepositoryModule;
import com.github.albertosh.adidasevents.sdk.repositories.di.mapper.MapperModule;
import com.github.albertosh.adidasevents.sdk.scopes.PerApplication;
import com.github.albertosh.adidasevents.sdk.usecases.di.auth.AuthUseCasesComponent;
import com.github.albertosh.adidasevents.sdk.usecases.di.auth.AuthUseCasesModule;
import com.github.albertosh.adidasevents.sdk.usecases.di.event.PublicEventUseCasesComponent;
import com.github.albertosh.adidasevents.sdk.usecases.di.event.PublicEventUseCasesModule;
import com.github.albertosh.adidasevents.sdk.usermanagement.di.UserManagementModule;

import dagger.Component;

@PerApplication
@Component(modules = {
        UserManagementModule.class,
        AuthUseCasesModule.class,
        PublicEventUseCasesModule.class,
        MapperModule.class,
        PublicApiModule.class,
        PublicEventRepositoryModule.class
})
public interface PublicUseCasesComponent
        extends AuthUseCasesComponent,
        PublicEventUseCasesComponent {

}
