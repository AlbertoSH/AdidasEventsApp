package com.github.albertosh.adidasevents.sdk.api.publicapi.auth.login;

import com.fernandocejas.arrow.checks.Preconditions;

public class LoginServiceInput {

    private final String email;
    private final String password;

    private LoginServiceInput(Builder builder) {
        this.email = Preconditions.checkNotNull(builder.email);
        this.password = Preconditions.checkNotNull(builder.password);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


    public static class Builder {
        private String email;
        private String password;

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder fromPrototype(LoginServiceInput prototype) {
            email = prototype.email;
            password = prototype.password;
            return this;
        }

        public LoginServiceInput build() {
            return new LoginServiceInput(this);
        }
    }
}
