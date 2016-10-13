package com.github.albertosh.adidasevents.sdk.usermanagement.di;

import com.github.albertosh.adidasevents.sdk.scopes.PerApplication;
import com.github.albertosh.adidasevents.sdk.usermanagement.IUserManagement;

import dagger.Component;

@PerApplication
@Component(modules = {
        UserManagementModule.class
})
public interface UserManagementComponent {

    IUserManagement userManagement();

}
