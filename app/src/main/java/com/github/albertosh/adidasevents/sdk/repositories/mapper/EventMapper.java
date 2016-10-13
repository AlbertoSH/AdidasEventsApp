package com.github.albertosh.adidasevents.sdk.repositories.mapper;

import com.github.albertosh.adidasevents.sdk.api.publicapi.events.EventData;
import com.github.albertosh.adidasevents.sdk.models.Event;

import org.joda.time.LocalDate;

public class EventMapper {

    public Event map(EventData value) {
        return new Event.Builder()
                .title(value.getTitle())
                .description(value.getDescription().orElse(null))
                .date(LocalDate.parse(value.getDate()))
                .imageId(value.getImageId().orElse(null))
                .imageUrl(value.getImageUrl().orElse(null))
                .id(value.getId())
                .build();
    }

}
