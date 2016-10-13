package com.github.albertosh.adidasevents.sdk.api.publicapi.events.all;

import com.github.albertosh.adidasevents.sdk.api.BaseService;
import com.github.albertosh.adidasevents.sdk.api.publicapi.events.EventData;

import java.util.List;

import rx.Single;

public class GetAllEventsService
        extends BaseService<GetAllEventsServiceInput, List<EventData>>
        implements IGetAllEventsService {

    private final RetrofitAllEvents retrofitAllEvents;

    public GetAllEventsService(RetrofitAllEvents retrofitAllEvents) {
        this.retrofitAllEvents = retrofitAllEvents;
    }

    @Override
    protected Single<List<EventData>> performNetworkCall(GetAllEventsServiceInput input) {
        return retrofitAllEvents.getAll(
                input.getPage().orElse(null),
                input.getPageSize().orElse(null),
                input.getLanguage().orElse(null)
        );
    }

}
