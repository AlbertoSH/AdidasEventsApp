package com.github.albertosh.adidaseventsapp.ui.detail;

import android.support.annotation.Nullable;

import com.github.albertosh.adidasevents.sdk.usecases.di.UserComponentManager;
import com.github.albertosh.adidasevents.sdk.usecases.events.getsingle.GetSingleEventUseCaseInput;
import com.github.albertosh.adidasevents.sdk.usecases.events.getsingle.IGetSingleEventUseCase;
import com.github.albertosh.adidasevents.sdk.usermanagement.IUserManagement;
import com.github.albertosh.adidaseventsapp.model.AEvent;
import com.github.albertosh.adidaseventsapp.ui.base.BaseLcePresenter;

import rx.Single;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class EventDetailPresenter
        extends BaseLcePresenter<EventDetailView, AEvent>
        implements IEventDetailPresenter {

    private final IGetSingleEventUseCase getSingleEventUseCase;
    private final UserComponentManager userComponentManager;
    private final IUserManagement userManagement;

    public EventDetailPresenter(IGetSingleEventUseCase getSingleEventUseCase,
                                UserComponentManager userComponentManager, IUserManagement userManagement) {
        this.getSingleEventUseCase = getSingleEventUseCase;
        this.userComponentManager = userComponentManager;
        this.userManagement = userManagement;
    }

    @Override
    public void loadEvent(AEvent event, String eventId) {
        if (event != null) {
            showData(event);
        } else {
            GetSingleEventUseCaseInput input = new GetSingleEventUseCaseInput.Builder()
                    .id(eventId)
                    .build();
            addSubscription(
                    getSingleEventUseCase.execute(input)
                            .map(AEvent::new)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(next -> {
                                showData(next);
                            }, error -> {
                                showError(error);
                            })
            );
        }
    }

    @Override
    public void enrollEvent() {
        if (isViewAttached()) {
            String eventId = getView().getEventId();
            addSubscription(
                    userManagement.hasUserLoggedIn()
                            .flatMap(userIsLoggedIn -> {
                                if (userIsLoggedIn) {
                                    if (isViewAttached())
                                        getView().disableEnrollment();
                                    return userComponentManager.getUserComponent()
                                            .enrollEventUseCase()
                                            .execute(eventId)
                                            .map(AEvent::new)
                                            .subscribeOn(Schedulers.io());
                                } else {
                                    return Single.just(null);
                                }
                            })
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(next -> {
                                if ((next == null) && (isViewAttached()))
                                    getView().goToLogin();
                            }, error -> {
                                getView().enableEnrollment();
                                showError(error);
                            })
            );
        }
    }
}
