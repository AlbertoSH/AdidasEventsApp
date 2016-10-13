package com.github.albertosh.adidasevents.sdk.repositories;

import com.github.albertosh.adidasevents.sdk.models.ObjectWithId;

import rx.Observable;

public interface ISingleRepositoryRead<T extends ObjectWithId> {

    public Observable<T> read(String id);

}
