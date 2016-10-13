package com.github.albertosh.adidaseventsapp;

import android.app.Application;
import android.os.StrictMode;

import com.github.albertosh.adidasevents.sdk.usecases.di.UserComponentManager;
import com.github.albertosh.adidasevents.sdk.usecases.di.UserUseCasesComponent;
import com.github.albertosh.adidasevents.sdk.usecases.di.auth.AuthUseCasesModule;
import com.github.albertosh.adidaseventsapp.di.AppComponent;
import com.github.albertosh.adidaseventsapp.di.AppModule;
import com.github.albertosh.adidaseventsapp.di.DaggerAppComponent;

public class AdidasApp
        extends Application implements UserComponentManager {

    private AppComponent appComponent;
    private UserUseCasesComponent userUseCasesComponent;

    @Override
    public void onCreate() {
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .penaltyFlashScreen()
                    .penaltyDialog()
                    .build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .penaltyDeath()
                    .build());
        }

        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .authUseCasesModule(new AuthUseCasesModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public void setAppComponent(AppComponent appComponent) {
        this.appComponent = appComponent;
    }

    @Override
    public UserUseCasesComponent getUserComponent() {
        if (userUseCasesComponent == null)
            throw new IllegalStateException("Trying to obtain userUseCaseComponent before initializing it");

        return userUseCasesComponent;
    }

    @Override
    public void createUserComponent(String token) {
        // TODO
    }

    @Override
    public void deleteUserComponent() {
        userUseCasesComponent = null;
    }
}
