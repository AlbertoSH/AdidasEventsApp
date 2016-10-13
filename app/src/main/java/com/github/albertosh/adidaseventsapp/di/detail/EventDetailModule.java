package com.github.albertosh.adidaseventsapp.di.detail;

import com.github.albertosh.adidasevents.sdk.usecases.events.getsingle.IGetSingleEventUseCase;
import com.github.albertosh.adidaseventsapp.ui.detail.EventDetailPresenter;
import com.github.albertosh.adidaseventsapp.ui.detail.IEventDetailPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class EventDetailModule {

    @Provides
    IEventDetailPresenter eventDetailPresenter(IGetSingleEventUseCase getSingleEventUseCase) {
        return new EventDetailPresenter(getSingleEventUseCase);
    }

}
