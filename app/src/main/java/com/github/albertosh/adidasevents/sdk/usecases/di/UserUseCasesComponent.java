package com.github.albertosh.adidasevents.sdk.usecases.di;

import com.github.albertosh.adidasevents.sdk.api.di.privateapi.PrivateApiModule;
import com.github.albertosh.adidasevents.sdk.scopes.PerUser;
import com.github.albertosh.adidasevents.sdk.usecases.di.event.PrivateEventUseCasesModule;
import com.github.albertosh.adidasevents.sdk.usecases.events.enroll.IEnrollEventUseCase;
import com.github.albertosh.adidasevents.sdk.usermanagement.di.UserManagementComponent;

import dagger.Component;

@PerUser
@Component(dependencies = {
        UserManagementComponent.class
}, modules = {
        PrivateApiModule.class,
        PrivateEventUseCasesModule.class
})
public interface UserUseCasesComponent {

    IEnrollEventUseCase enrollEventUseCase();

}
