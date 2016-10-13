package com.github.albertosh.adidaseventsapp.ui.main;

import android.util.Pair;

import com.github.albertosh.adidasevents.sdk.usecases.events.getall.IGetAllEventsUseCase;
import com.github.albertosh.adidaseventsapp.model.AEvent;
import com.github.albertosh.adidaseventsapp.ui.base.BaseLcePresenter;

import java.util.List;

import java8.util.stream.Collectors;
import java8.util.stream.StreamSupport;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainPresenter
        extends BaseLcePresenter<MainView, Pair<List<AEvent>, AEvent>>
        implements IMainPresenter {

    private final IGetAllEventsUseCase getAllEventsUseCase;

    public MainPresenter(IGetAllEventsUseCase getAllEventsUseCase) {
        this.getAllEventsUseCase = getAllEventsUseCase;
    }

    @Override
    public void loadEvents(boolean twoPaneView) {
        addSubscription(
                getAllEventsUseCase.execute()
                        .map(eventList -> StreamSupport.stream(eventList)
                                .map(AEvent::new)
                                .collect(Collectors.toList()))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(next -> {
                            if (twoPaneView) {
                                AEvent event = next.size() > 0
                                        ? next.get(0)
                                        : null;
                                showData(Pair.create(next, event));
                            } else {
                                showData(Pair.create(next, null));
                            }
                        }, error -> {
                            showError(error);
                        })
        );
    }
}
