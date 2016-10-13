package com.github.albertosh.adidasevents.sdk.api;

import rx.Single;

public interface IService<Input, Output> {

    public Single<Output> execute(Input input);

}
