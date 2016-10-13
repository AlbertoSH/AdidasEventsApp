package com.github.albertosh.adidasevents.sdk.api.publicapi.events.single;

import com.fernandocejas.arrow.checks.Preconditions;

import java8.util.Optional;

public class GetSingleEventServiceInput {

    private final String language;
    private final String id;

    private GetSingleEventServiceInput(Builder builder) {
        this.language = builder.language;
        this.id = Preconditions.checkNotNull(builder.id);
    }

    public Optional<String> getLanguage() {
        return Optional.ofNullable(language);
    }

    public String getId() {
        return id;
    }


    public static class Builder {
        private String language;
        private String id;

        public Builder language(String language) {
            this.language = language;
            return this;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder fromPrototype(GetSingleEventServiceInput prototype) {
            language = prototype.language;
            id = prototype.id;
            return this;
        }

        public GetSingleEventServiceInput build() {
            return new GetSingleEventServiceInput(this);
        }
    }
}
