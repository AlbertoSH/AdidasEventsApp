package com.github.albertosh.adidaseventsapp;

import android.app.Application;
import android.os.StrictMode;

import com.github.albertosh.adidasevents.sdk.api.di.privateapi.PrivateApiModule;
import com.github.albertosh.adidasevents.sdk.usecases.di.DaggerUserUseCasesComponent;
import com.github.albertosh.adidasevents.sdk.usecases.di.UserComponentManager;
import com.github.albertosh.adidasevents.sdk.usecases.di.UserUseCasesComponent;
import com.github.albertosh.adidasevents.sdk.usecases.di.auth.AuthUseCasesModule;
import com.github.albertosh.adidaseventsapp.custom.CustomProperties;
import com.github.albertosh.adidaseventsapp.di.AndroidUserManagementModule;
import com.github.albertosh.adidaseventsapp.di.AppComponent;
import com.github.albertosh.adidaseventsapp.di.AppModule;
import com.github.albertosh.adidaseventsapp.di.DaggerAppComponent;

import java.util.List;
import java.util.Map;

public class AdidasApp
        extends Application implements UserComponentManager {

    private AppComponent appComponent;
    private UserUseCasesComponent userUseCasesComponent;
    private List<Map<String, List<String>>> properties;
    private AppModule appModule;

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

        appModule = new AppModule(this);
        appComponent = DaggerAppComponent.builder()
                .appModule(appModule)
                .userManagementModule(new AndroidUserManagementModule(this))
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
        this.userUseCasesComponent = DaggerUserUseCasesComponent.builder()
                .privateApiModule(new PrivateApiModule(token))
                .userManagementComponent(appComponent)
                .build();
    }

    @Override
    public void deleteUserComponent() {
        userUseCasesComponent = null;
    }

    public void setProperties(Map<String, Object> properties) {
        appModule.setCustomProperties(new CustomProperties(properties));
    }

}
