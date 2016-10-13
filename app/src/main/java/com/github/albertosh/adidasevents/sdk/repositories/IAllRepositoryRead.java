package com.github.albertosh.adidasevents.sdk.repositories;

import com.github.albertosh.adidasevents.sdk.models.ObjectWithId;

import java.util.List;

import rx.Observable;

public interface IAllRepositoryRead<T extends ObjectWithId> {

    public Observable<List<T>> read();

}
