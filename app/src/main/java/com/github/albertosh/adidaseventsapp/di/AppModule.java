package com.github.albertosh.adidaseventsapp.di;

import android.content.Context;

import com.github.albertosh.adidasevents.sdk.scopes.PerApplication;
import com.github.albertosh.adidaseventsapp.AdidasApp;
import com.github.albertosh.adidaseventsapp.custom.CustomProperties;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final AdidasApp application;

    public AppModule(AdidasApp application) {
        this.application = application;
    }
/*
    @Provides
    LoginActivityProvider loginActivityProvider() {
        return application;
    }
*/
    @Provides
    Context context() {
        return application;
    }

    @Provides @PerApplication
    CustomProperties customPropertiesObservable() {
        // TODO
        return new CustomProperties();
    }
}
