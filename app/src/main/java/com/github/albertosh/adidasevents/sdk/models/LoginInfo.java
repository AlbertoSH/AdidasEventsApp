package com.github.albertosh.adidasevents.sdk.models;

import com.fernandocejas.arrow.checks.Preconditions;

public class LoginInfo {

    private final String uuid;
    private final String token;

    private LoginInfo(Builder builder) {
        this.uuid = Preconditions.checkNotNull(builder.uuid);
        this.token = Preconditions.checkNotNull(builder.token);
    }

    public String getUuid() {
        return uuid;
    }

    public String getToken() {
        return token;
    }


    public static class Builder {
        private String uuid;
        private String token;

        public Builder uuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public Builder fromPrototype(LoginInfo prototype) {
            uuid = prototype.uuid;
            token = prototype.token;
            return this;
        }

        public LoginInfo build() {
            return new LoginInfo(this);
        }
    }
}
