package com.github.albertosh.adidasevents.sdk.usecases.auth.signup;

import com.github.albertosh.adidasevents.sdk.api.publicapi.auth.signup.ISignupService;
import com.github.albertosh.adidasevents.sdk.api.publicapi.auth.signup.SignupServiceInput;
import com.github.albertosh.adidasevents.sdk.models.LoginInfo;
import com.github.albertosh.adidasevents.sdk.usecases.di.UserComponentManager;
import com.github.albertosh.adidasevents.sdk.usermanagement.IUserManagement;

import org.joda.time.format.ISODateTimeFormat;

import rx.Single;

public class SignupUseCase implements ISignupUseCase {

    private final ISignupService service;
    private final IUserManagement userManagement;
    private final UserComponentManager userComponentManager;

    public SignupUseCase(ISignupService service, IUserManagement userManagement, UserComponentManager userComponentManager) {
        this.service = service;
        this.userManagement = userManagement;
        this.userComponentManager = userComponentManager;
    }

    public Single<LoginInfo> execute(SignupUseCaseInput input) {
        SignupServiceInput serviceInput = new SignupServiceInput.Builder()
                .email(input.getEmail())
                .password(input.getPassword())
                .firstName(input.getFirstName())
                .lastName(input.getLastName())
                .dateOfBirth(ISODateTimeFormat.date().print(input.getDateOfBirth()))
                .country(input.getCountry())
                .build();
        return service.execute(serviceInput)
                .map(data -> new LoginInfo.Builder()
                        .uuid(data.getUuid())
                        .token(data.getToken())
                        .build())
                .doOnSuccess(loginInfo -> {
                    userComponentManager.createUserComponent(loginInfo.getToken());
                    userManagement.addUser(
                            input.getEmail(),
                            input.getPassword(),
                            loginInfo.getUuid(),
                            loginInfo.getToken()
                    );
                });
    }
}
