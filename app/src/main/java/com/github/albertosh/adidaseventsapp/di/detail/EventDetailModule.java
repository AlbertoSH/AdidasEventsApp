package com.github.albertosh.adidaseventsapp.di.detail;

import com.github.albertosh.adidasevents.sdk.usecases.di.UserComponentManager;
import com.github.albertosh.adidasevents.sdk.usecases.events.getsingle.IGetSingleEventUseCase;
import com.github.albertosh.adidasevents.sdk.usermanagement.IUserManagement;
import com.github.albertosh.adidaseventsapp.di.PerController;
import com.github.albertosh.adidaseventsapp.ui.detail.EventDetailPresenter;
import com.github.albertosh.adidaseventsapp.ui.detail.IEventDetailPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class EventDetailModule {

    @Provides @PerController
    IEventDetailPresenter eventDetailPresenter(IGetSingleEventUseCase getSingleEventUseCase,
                                               UserComponentManager userComponentManager,
                                               IUserManagement userManagement) {
        return new EventDetailPresenter(getSingleEventUseCase, userComponentManager, userManagement);
    }

}
