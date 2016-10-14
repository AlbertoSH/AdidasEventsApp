package com.github.albertosh.adidaseventsapp.usermanagement;


import android.accounts.AccountManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class AuthenticationService extends Service {

    public AuthenticationService() {}

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new Authenticator(AccountManager.get(this), this).getIBinder();
    }

}
