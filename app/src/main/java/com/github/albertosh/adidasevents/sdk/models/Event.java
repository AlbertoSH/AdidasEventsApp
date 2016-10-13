package com.github.albertosh.adidasevents.sdk.models;

import com.fernandocejas.arrow.checks.Preconditions;

import org.joda.time.LocalDate;

import java8.util.Optional;

public class Event extends ObjectWithId {

    private final String title;
    private final String description;
    private final LocalDate date;
    private final String imageUrl;
    private final String imageId;

    protected Event(Builder builder) {
        super(builder);
        this.title = Preconditions.checkNotNull(builder.title);
        this.description = builder.description;
        this.date = Preconditions.checkNotNull(builder.date);
        this.imageUrl = builder.imageUrl;
        this.imageId = builder.imageId;
    }

    public String getTitle() {
        return title;
    }

    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

    public LocalDate getDate() {
        return date;
    }

    public Optional<String> getImageUrl() {
        return Optional.ofNullable(imageUrl);
    }

    public Optional<String> getImageId() {
        return Optional.ofNullable(imageId);
    }


    public static class Builder extends ObjectWithId.Builder<Event> {
        private String title;
        private String description;
        private LocalDate date;
        private String imageUrl;
        private String imageId;

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder imageId(String imageId) {
            this.imageId = imageId;
            return this;
        }

        public Builder fromPrototype(Event prototype) {
            super.fromPrototype(prototype);
            title = prototype.title;
            description = prototype.description;
            date = prototype.date;
            imageUrl = prototype.imageUrl;
            imageId = prototype.imageId;
            return this;
        }

        public Event build() {
            return new Event(this);
        }
    }
}
