package com.github.albertosh.adidasevents.sdk.usecases.events.getsingle;

import com.fernandocejas.arrow.checks.Preconditions;

import java8.util.Optional;

public class GetSingleEventUseCaseInput {

    private final String id;
    private final String language;

    private GetSingleEventUseCaseInput(Builder builder) {
        this.id = Preconditions.checkNotNull(builder.id);
        this.language = builder.language;
    }

    public String getId() {
        return id;
    }

    public Optional<String> getLanguage() {
        return Optional.ofNullable(language);
    }


    public static class Builder {
        private String id;
        private String language;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder language(String language) {
            this.language = language;
            return this;
        }

        public Builder fromPrototype(GetSingleEventUseCaseInput prototype) {
            id = prototype.id;
            language = prototype.language;
            return this;
        }

        public GetSingleEventUseCaseInput build() {
            return new GetSingleEventUseCaseInput(this);
        }
    }
}
