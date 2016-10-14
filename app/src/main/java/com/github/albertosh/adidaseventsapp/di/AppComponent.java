package com.github.albertosh.adidaseventsapp.di;

import android.content.Context;

import com.github.albertosh.adidasevents.sdk.api.di.publicapi.PublicApiComponent;
import com.github.albertosh.adidasevents.sdk.api.di.publicapi.PublicApiModule;
import com.github.albertosh.adidasevents.sdk.repositories.di.event.PublicEventRepositoryModule;
import com.github.albertosh.adidasevents.sdk.repositories.di.mapper.MapperModule;
import com.github.albertosh.adidasevents.sdk.scopes.PerApplication;
import com.github.albertosh.adidasevents.sdk.usecases.di.UserComponentManager;
import com.github.albertosh.adidasevents.sdk.usecases.di.auth.AuthUseCasesComponent;
import com.github.albertosh.adidasevents.sdk.usecases.di.auth.AuthUseCasesModule;
import com.github.albertosh.adidasevents.sdk.usecases.di.event.PublicEventUseCasesComponent;
import com.github.albertosh.adidasevents.sdk.usecases.di.event.PublicEventUseCasesModule;
import com.github.albertosh.adidasevents.sdk.usermanagement.di.UserManagementComponent;
import com.github.albertosh.adidasevents.sdk.usermanagement.di.UserManagementModule;
import com.github.albertosh.adidaseventsapp.custom.CustomProperties;

import dagger.Component;

@PerApplication
@Component(modules = {
        AppModule.class,
        UserManagementModule.class,
        AuthUseCasesModule.class,
        PublicEventUseCasesModule.class,
        MapperModule.class,
        PublicApiModule.class,
        PublicEventRepositoryModule.class
})
public interface AppComponent
        extends UserManagementComponent, AuthUseCasesComponent, PublicEventUseCasesComponent, PublicApiComponent {

    Context context();

    UserComponentManager userComponentManager();

   CustomProperties customProperties();
}
