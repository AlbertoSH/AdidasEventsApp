package com.github.albertosh.adidaseventsapp.di.main;

import com.github.albertosh.adidasevents.sdk.usecases.events.getall.IGetAllEventsUseCase;
import com.github.albertosh.adidaseventsapp.ui.main.IMainPresenter;
import com.github.albertosh.adidaseventsapp.ui.main.MainPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Provides
    IMainPresenter mainPresenter(IGetAllEventsUseCase getAllEventsUseCase) {
        return new MainPresenter(getAllEventsUseCase);
    }

}
