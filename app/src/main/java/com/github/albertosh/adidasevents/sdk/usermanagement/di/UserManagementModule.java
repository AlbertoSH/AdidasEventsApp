package com.github.albertosh.adidasevents.sdk.usermanagement.di;

import com.github.albertosh.adidasevents.sdk.scopes.PerApplication;
import com.github.albertosh.adidasevents.sdk.usermanagement.IUserManagement;

import dagger.Module;
import dagger.Provides;

@Module
public class UserManagementModule {

    @Provides
    @PerApplication
    protected IUserManagement userManagement() {
        return getUserManagement();
    }

    protected IUserManagement getUserManagement() {
        throw new RuntimeException("This method MUST be overridden!");
    }

}
