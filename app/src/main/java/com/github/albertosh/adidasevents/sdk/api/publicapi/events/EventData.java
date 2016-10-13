package com.github.albertosh.adidasevents.sdk.api.publicapi.events;

import java8.util.Optional;

public class EventData {

    private String id;
    private String title;
    private String date;
    private String description;
    private String imageUrl;
    private String imageId;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

    public Optional<String> getImageUrl() {
        return Optional.ofNullable(imageUrl);
    }

    public Optional<String> getImageId() {
        return Optional.ofNullable(imageId);
    }
}
