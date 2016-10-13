package com.github.albertosh.adidasevents.sdk.api.publicapi.events.all;

import com.github.albertosh.adidasevents.sdk.api.IService;
import com.github.albertosh.adidasevents.sdk.api.publicapi.events.EventData;

import java.util.List;

public interface IGetAllEventsService extends IService<GetAllEventsServiceInput, List<EventData>> {
}
