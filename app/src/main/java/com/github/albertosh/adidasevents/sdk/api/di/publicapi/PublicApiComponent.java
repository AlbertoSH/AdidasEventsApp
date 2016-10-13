package com.github.albertosh.adidasevents.sdk.api.di.publicapi;

import com.github.albertosh.adidasevents.sdk.api.publicapi.auth.login.ILoginService;
import com.github.albertosh.adidasevents.sdk.api.publicapi.auth.signup.ISignupService;
import com.github.albertosh.adidasevents.sdk.api.publicapi.events.all.IGetAllEventsService;
import com.github.albertosh.adidasevents.sdk.api.publicapi.events.single.IGetSingleEventService;
import com.github.albertosh.adidasevents.sdk.scopes.PerApplication;

import dagger.Component;

@PerApplication
@Component(modules = {
        PublicApiModule.class
})
public interface PublicApiComponent {

    ILoginService loginService();

    ISignupService signupService();

    IGetAllEventsService getAllEventsService();

    IGetSingleEventService getSingleEventService();

}
