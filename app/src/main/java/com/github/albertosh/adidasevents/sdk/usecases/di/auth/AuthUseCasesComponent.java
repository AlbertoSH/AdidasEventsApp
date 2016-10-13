package com.github.albertosh.adidasevents.sdk.usecases.di.auth;

import com.github.albertosh.adidasevents.sdk.api.di.publicapi.PublicApiModule;
import com.github.albertosh.adidasevents.sdk.scopes.PerApplication;
import com.github.albertosh.adidasevents.sdk.usecases.auth.login.ILoginUseCase;
import com.github.albertosh.adidasevents.sdk.usecases.auth.signup.ISignupUseCase;
import com.github.albertosh.adidasevents.sdk.usermanagement.di.UserManagementModule;

import dagger.Component;

@PerApplication
@Component(modules = {
        UserManagementModule.class,
        AuthUseCasesModule.class,
        PublicApiModule.class
})
public interface AuthUseCasesComponent {

    ILoginUseCase loginUseCase();

    ISignupUseCase signupUseCase();

}
