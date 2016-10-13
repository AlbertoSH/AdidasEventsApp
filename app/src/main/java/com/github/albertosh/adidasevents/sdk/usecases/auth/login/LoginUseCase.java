package com.github.albertosh.adidasevents.sdk.usecases.auth.login;

import com.github.albertosh.adidasevents.sdk.api.publicapi.auth.login.ILoginService;
import com.github.albertosh.adidasevents.sdk.api.publicapi.auth.login.LoginServiceInput;
import com.github.albertosh.adidasevents.sdk.models.LoginInfo;
import com.github.albertosh.adidasevents.sdk.usecases.di.UserComponentManager;
import com.github.albertosh.adidasevents.sdk.usermanagement.IUserManagement;

import rx.Single;

public class LoginUseCase implements ILoginUseCase {

    private final ILoginService service;
    private final IUserManagement userManagement;
    private final UserComponentManager userComponentManager;

    public LoginUseCase(ILoginService service, IUserManagement userManagement, UserComponentManager userComponentManager) {
        this.service = service;
        this.userManagement = userManagement;
        this.userComponentManager = userComponentManager;
    }

    public Single<LoginInfo> execute(LoginUseCaseInput input) {
        LoginServiceInput serviceInput = new LoginServiceInput.Builder()
                .email(input.getEmail())
                .password(input.getPassword())
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
