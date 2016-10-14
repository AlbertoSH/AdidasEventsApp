package com.github.albertosh.adidaseventsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.github.albertosh.adidasevents.sdk.usermanagement.IUserManagement;
import com.github.albertosh.adidaseventsapp.ui.main.MainController;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.controller_container)
    ViewGroup container;

    private Router router;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        router = Conductor.attachRouter(this, container, savedInstanceState);
        if (!router.hasRootController()) {
            // First launch
            AdidasApp app = ((AdidasApp) getApplication());
            IUserManagement userManagement = app.getAppComponent().userManagement();

            Observable checkIfUserIsLoggedIn =
                    userManagement.hasUserLoggedIn()
                            .toObservable()
                            .filter(bool -> bool)
                            .flatMap(aBool -> userManagement.getUserToken().toObservable())
                            .doOnNext(token -> {
                                app.createUserComponent(token);
                            })
                            .subscribeOn(Schedulers.newThread());
            Observable loadProperties = app.getAppComponent().customService()
                    .get()
                    .doOnSuccess(next -> app.setProperties(next))
                    .subscribeOn(Schedulers.io())
                    .toObservable();

            Observable.zip(checkIfUserIsLoggedIn, loadProperties, (a, b) -> null)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnCompleted(() -> {
                        router.setRoot(RouterTransaction.with(new MainController()));
                    }).subscribe();
        }
    }

    @Override
    public void onBackPressed() {
        if (!router.handleBack()) {
            super.onBackPressed();
        }
    }

}
