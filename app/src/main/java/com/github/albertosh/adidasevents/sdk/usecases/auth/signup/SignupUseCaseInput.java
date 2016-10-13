package com.github.albertosh.adidasevents.sdk.usecases.auth.signup;

import com.fernandocejas.arrow.checks.Preconditions;

import org.joda.time.LocalDate;

import java8.util.Optional;

public class SignupUseCaseInput {

    private final String email;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final LocalDate dateOfBirth;
    private final String country;
    private final String language;

    private SignupUseCaseInput(Builder builder) {
        this.email = Preconditions.checkNotNull(builder.email);
        this.password = Preconditions.checkNotNull(builder.password);
        this.firstName = Preconditions.checkNotNull(builder.firstName);
        this.lastName = Preconditions.checkNotNull(builder.lastName);
        this.dateOfBirth = Preconditions.checkNotNull(builder.dateOfBirth);
        this.country = Preconditions.checkNotNull(builder.country);
        this.language = builder.language;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getCountry() {
        return country;
    }

    public Optional<String> getLanguage() {
        return Optional.ofNullable(language);
    }


    public static class Builder {
        private String email;
        private String password;
        private String firstName;
        private String lastName;
        private LocalDate dateOfBirth;
        private String country;
        private String language;

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder dateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder language(String language) {
            this.language = language;
            return this;
        }

        public Builder fromPrototype(SignupUseCaseInput prototype) {
            email = prototype.email;
            password = prototype.password;
            firstName = prototype.firstName;
            lastName = prototype.lastName;
            dateOfBirth = prototype.dateOfBirth;
            country = prototype.country;
            language = prototype.language;
            return this;
        }

        public SignupUseCaseInput build() {
            return new SignupUseCaseInput(this);
        }
    }
}
