package com.github.albertosh.adidasevents.sdk.api;

import java.io.IOException;

import rx.Single;

public abstract class BaseService<Input, Output> implements IService<Input, Output> {

    @Override
    public final Single<Output> execute(Input input) {
        return performNetworkCall(input)
                .onErrorResumeNext(error -> {
                    ApiException apiError = (ApiException) error;
                    try {
                        switch (apiError.getKind()) {
                            case HTTP:
                                return Single.error(mapErrorCodeToException(
                                        apiError.getErrorBodyAs(NetworkOperationError.class)
                                                .getCode()));
                            case NETWORK:
                                return Single.error(ServiceError.networkError);
                            default:
                                return Single.error(new RuntimeException(error));
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    protected abstract Single<Output> performNetworkCall(Input input);

    protected ServiceError mapErrorCodeToException(int code) {
        return ServiceError.unknownError;
    }
}
