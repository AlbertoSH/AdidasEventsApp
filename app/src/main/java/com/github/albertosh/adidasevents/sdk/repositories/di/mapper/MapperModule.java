package com.github.albertosh.adidasevents.sdk.repositories.di.mapper;

import com.github.albertosh.adidasevents.sdk.repositories.mapper.EventMapper;

import dagger.Module;
import dagger.Provides;

@Module
public class MapperModule {

    @Provides
    EventMapper eventMapper() {
        return new EventMapper();
    }

}
