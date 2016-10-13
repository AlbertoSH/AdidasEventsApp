package com.github.albertosh.adidasevents.sdk.usecases.auth.login;

import com.fernandocejas.arrow.checks.Preconditions;

public class LoginUseCaseInput {

    private final String email;
    private final String password;

    private LoginUseCaseInput(Builder builder) {
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

        public Builder fromPrototype(LoginUseCaseInput prototype) {
            email = prototype.email;
            password = prototype.password;
            return this;
        }

        public LoginUseCaseInput build() {
            return new LoginUseCaseInput(this);
        }
    }
}
