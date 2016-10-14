package com.github.albertosh.adidaseventsapp.di;

import android.content.Context;

import com.github.albertosh.adidaseventsapp.AdidasApp;
import com.github.albertosh.adidaseventsapp.custom.CustomProperties;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final AdidasApp application;
    private CustomProperties customProperties;

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

    @Provides
    CustomProperties customProperties() {
       return customProperties;
    }

    public void setCustomProperties(CustomProperties customProperties) {
        this.customProperties = customProperties;
    }
}
