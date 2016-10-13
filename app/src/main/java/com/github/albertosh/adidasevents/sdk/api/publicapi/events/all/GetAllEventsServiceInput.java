package com.github.albertosh.adidasevents.sdk.api.publicapi.events.all;

import java8.util.Optional;

public class GetAllEventsServiceInput {

    private final String language;
    private final Integer page;
    private final Integer pageSize;

    private GetAllEventsServiceInput(Builder builder) {
        this.language = builder.language;
        this.page = builder.page;
        this.pageSize = builder.pageSize;
    }

    public Optional<String> getLanguage() {
        return Optional.ofNullable(language);
    }

    public Optional<Integer> getPage() {
        return Optional.ofNullable(page);
    }

    public Optional<Integer> getPageSize() {
        return Optional.ofNullable(pageSize);
    }


    public static class Builder {
        private String language;
        private Integer page;
        private Integer pageSize;

        public Builder language(String language) {
            this.language = language;
            return this;
        }

        public Builder page(Integer page) {
            this.page = page;
            return this;
        }

        public Builder pageSize(Integer pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public Builder fromPrototype(GetAllEventsServiceInput prototype) {
            language = prototype.language;
            page = prototype.page;
            pageSize = prototype.pageSize;
            return this;
        }

        public GetAllEventsServiceInput build() {
            return new GetAllEventsServiceInput(this);
        }
    }
}
