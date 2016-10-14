package com.github.albertosh.adidaseventsapp.usermanagement;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresPermission;

import com.github.albertosh.adidasevents.sdk.usermanagement.IUserManagement;

import rx.Single;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AndroidUserManagement implements IUserManagement {

    private final String accountType;
    private final AccountManager accountManager;

    public AndroidUserManagement(String accountType, AccountManager accountManager) {
        this.accountType = accountType;
        this.accountManager = accountManager;
    }

    @Override
    public Single<Boolean> hasUserLoggedIn() throws SecurityException {
        return Single.fromCallable(() -> accountManager.getAccountsByType(accountType))
                .map(array -> array.length > 0)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single addUser(String email, String password, String id, String token) throws SecurityException {
        return Single.fromCallable(() -> {
            Account account = new Account(email, accountType);

            Bundle userData = new Bundle();
            userData.putString(UserManagementUtils.KEY_USER_ID, id);
            userData.putString(AccountManager.KEY_AUTHTOKEN, token);

            accountManager.addAccountExplicitly(account, password, userData);
            accountManager.setAuthToken(account, accountType, token);

            return null;
        })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<Boolean> logout() throws SecurityException {
        Account currentAccount = getCurrentAccount();

        if (Build.VERSION.SDK_INT < 22) {

            return Single.defer(() -> {
                try {
                    @SuppressWarnings("deprecation")
                    Boolean result = accountManager.removeAccount(currentAccount, null, null).getResult();
                    return Single.just(result);
                } catch (Exception e) {
                    return Single.error(e);
                }
            })
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread());
        } else {
            return Single.just(accountManager.removeAccountExplicitly(currentAccount));
        }
    }

    @Override
    public Single<String> getUserToken() throws SecurityException {
        return Single.fromCallable(() -> accountManager.peekAuthToken(getCurrentAccount(), accountType))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @RequiresPermission(Manifest.permission.GET_ACCOUNTS)
    private Account getCurrentAccount() throws SecurityException {
        Account[] accounts = accountManager.getAccountsByType(accountType);
        return accounts[0];
    }
}
