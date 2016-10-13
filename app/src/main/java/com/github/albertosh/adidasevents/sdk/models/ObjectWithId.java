package com.github.albertosh.adidasevents.sdk.models;

import com.fernandocejas.arrow.checks.Preconditions;

public abstract class ObjectWithId {

    private final String id;

    protected ObjectWithId(Builder builder) {
        this.id = Preconditions.checkNotNull(builder.id);
    }

    public String getId() {
        return id;
    }


    public abstract static class Builder<T extends ObjectWithId> {
        private String id;

        public Builder<T> id(String id) {
            this.id = id;
            return this;
        }

        public Builder<T> fromPrototype(ObjectWithId prototype) {
            id = prototype.id;
            return this;
        }

        public abstract T build();
    }
}
