package com.github.albertosh.adidasevents.sdk.api.publicapi.auth.login;

import com.fernandocejas.arrow.checks.Preconditions;

public class LoginServiceOutput {

    private final String uuid;
    private final String token;

    private LoginServiceOutput(Builder builder) {
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

        public Builder fromPrototype(LoginServiceOutput prototype) {
            uuid = prototype.uuid;
            token = prototype.token;
            return this;
        }

        public LoginServiceOutput build() {
            return new LoginServiceOutput(this);
        }
    }
}
