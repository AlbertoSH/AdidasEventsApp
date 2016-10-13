package com.github.albertosh.adidasevents.sdk.api.publicapi.events.single;

import com.github.albertosh.adidasevents.sdk.api.BaseService;
import com.github.albertosh.adidasevents.sdk.api.publicapi.events.EventData;

import rx.Single;

public class GetSingleEventService
        extends BaseService<GetSingleEventServiceInput, EventData>
        implements IGetSingleEventService {

    private final RetrofitSingleEvent retrofitSingleEvent;

    public GetSingleEventService(RetrofitSingleEvent retrofitSingleEvent) {
        this.retrofitSingleEvent = retrofitSingleEvent;
    }

    @Override
    protected Single<EventData> performNetworkCall(GetSingleEventServiceInput input) {
        return retrofitSingleEvent.getSingle(
                input.getId(),
                input.getLanguage().orElse(null)
        );
    }

}
