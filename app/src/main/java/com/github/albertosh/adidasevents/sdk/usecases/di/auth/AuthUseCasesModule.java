package com.github.albertosh.adidasevents.sdk.usecases.di.auth;

import com.github.albertosh.adidasevents.sdk.api.publicapi.auth.login.ILoginService;
import com.github.albertosh.adidasevents.sdk.api.publicapi.auth.signup.ISignupService;
import com.github.albertosh.adidasevents.sdk.scopes.PerApplication;
import com.github.albertosh.adidasevents.sdk.usecases.auth.login.ILoginUseCase;
import com.github.albertosh.adidasevents.sdk.usecases.auth.login.LoginUseCase;
import com.github.albertosh.adidasevents.sdk.usecases.auth.signup.ISignupUseCase;
import com.github.albertosh.adidasevents.sdk.usecases.auth.signup.SignupUseCase;
import com.github.albertosh.adidasevents.sdk.usecases.di.UserComponentManager;
import com.github.albertosh.adidasevents.sdk.usermanagement.IUserManagement;

import dagger.Module;
import dagger.Provides;

@Module
public class AuthUseCasesModule {

    private final UserComponentManager userComponentManager;

    public AuthUseCasesModule(UserComponentManager userComponentManager) {
        this.userComponentManager = userComponentManager;
    }

    @Provides
    UserComponentManager userComponentManager() {
        return userComponentManager;
    }

    @Provides
    @PerApplication
    ILoginUseCase loginUseCase(ILoginService service, IUserManagement userManagement,
                               UserComponentManager userComponentManager) {
        return new LoginUseCase(service, userManagement, userComponentManager);
    }

    @Provides
    @PerApplication
    ISignupUseCase signupUseCase(ISignupService service, IUserManagement userManagement,
                                 UserComponentManager userComponentManager) {
        return new SignupUseCase(service, userManagement, userComponentManager);
    }

}
