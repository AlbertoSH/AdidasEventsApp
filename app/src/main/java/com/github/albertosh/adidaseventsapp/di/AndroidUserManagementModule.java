package com.github.albertosh.adidaseventsapp.di;

import android.accounts.AccountManager;
import android.content.Context;

import com.github.albertosh.adidasevents.sdk.scopes.PerApplication;
import com.github.albertosh.adidasevents.sdk.usermanagement.IUserManagement;
import com.github.albertosh.adidasevents.sdk.usermanagement.di.UserManagementModule;
import com.github.albertosh.adidaseventsapp.usermanagement.AndroidUserManagement;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class AndroidUserManagementModule extends UserManagementModule {

    private final Context context;

    public AndroidUserManagementModule(Context context) {
        this.context = context.getApplicationContext();
    }

    // A bit ugly :S
    // Should be thought again at some point
    // But it works
    // But I don't like it :'(

    private IUserManagement userManagementSingleton = null;

    @Override
    protected IUserManagement getUserManagement() {
        return new AndroidUserManagement(
                providesAccountType(),
                providesAccountManager(
                        context
                ));
    }
/*
    @Provides
    @PerApplication
    IUserManagement providesAndroidUserManagement(@Named("accountType") String accountType,
                                                  AccountManager accountManager) {
        return new AndroidUserManagement(accountType, accountManager);
    }

    */
    @Provides
    @PerApplication
    @Named("accountType")
    String providesAccountType() {
        return "com.github.albertosh.adidasevents";
    }

    private AccountManager accountManagerSingleton = null;

    @Provides
    @PerApplication
    AccountManager providesAccountManager(Context context) {
        if (accountManagerSingleton == null)
            accountManagerSingleton = AccountManager.get(context);
        return accountManagerSingleton;
    }

}
