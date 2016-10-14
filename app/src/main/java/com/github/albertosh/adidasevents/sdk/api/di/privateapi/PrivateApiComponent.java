package com.github.albertosh.adidasevents.sdk.api.di.privateapi;

import com.github.albertosh.adidasevents.sdk.api.privateapi.events.enroll.IEnrollService;
import com.github.albertosh.adidasevents.sdk.scopes.PerUser;

import dagger.Component;

@PerUser
@Component(modules = {
        PrivateApiModule.class
})
public interface PrivateApiComponent {

    IEnrollService enrollService();

}
