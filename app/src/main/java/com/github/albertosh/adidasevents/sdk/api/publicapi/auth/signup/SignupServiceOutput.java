package com.github.albertosh.adidasevents.sdk.api.publicapi.auth.signup;

import com.fernandocejas.arrow.checks.Preconditions;

public class SignupServiceOutput {

    private final String uuid;
    private final String token;

    private SignupServiceOutput(Builder builder) {
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

        public Builder fromPrototype(SignupServiceOutput prototype) {
            uuid = prototype.uuid;
            token = prototype.token;
            return this;
        }

        public SignupServiceOutput build() {
            return new SignupServiceOutput(this);
        }
    }
}
