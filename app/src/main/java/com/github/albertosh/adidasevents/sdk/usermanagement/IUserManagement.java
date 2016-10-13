package com.github.albertosh.adidasevents.sdk.usermanagement;

import rx.Single;

public interface IUserManagement {

    public Single<Boolean> hasUserLoggedIn() throws SecurityException;

    public Single addUser(String email, String password, String id, String token) throws SecurityException;

    public Single<Boolean> logout() throws SecurityException;

    public Single<String> getUserToken() throws SecurityException;

}
