package com.github.albertosh.adidaseventsapp.ui.detail;

import android.support.annotation.Nullable;

import com.github.albertosh.adidasevents.sdk.usecases.events.getsingle.GetSingleEventUseCaseInput;
import com.github.albertosh.adidasevents.sdk.usecases.events.getsingle.IGetSingleEventUseCase;
import com.github.albertosh.adidaseventsapp.model.AEvent;
import com.github.albertosh.adidaseventsapp.ui.base.BaseLcePresenter;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class EventDetailPresenter
        extends BaseLcePresenter<EventDetailView, AEvent>
        implements IEventDetailPresenter {

    private final IGetSingleEventUseCase getSingleEventUseCase;

    public EventDetailPresenter(IGetSingleEventUseCase getSingleEventUseCase) {
        this.getSingleEventUseCase = getSingleEventUseCase;
    }

    @Override
    public void loadEvent(@Nullable AEvent event, String eventId) {
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
}
