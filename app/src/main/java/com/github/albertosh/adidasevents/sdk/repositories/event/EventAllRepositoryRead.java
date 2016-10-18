package com.github.albertosh.adidasevents.sdk.repositories.event;

import com.github.albertosh.adidasevents.sdk.api.publicapi.events.all.GetAllEventsServiceInput;
import com.github.albertosh.adidasevents.sdk.api.publicapi.events.all.IGetAllEventsService;
import com.github.albertosh.adidasevents.sdk.models.Event;
import com.github.albertosh.adidasevents.sdk.repositories.mapper.EventMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java8.util.stream.Collectors;
import java8.util.stream.StreamSupport;
import rx.Observable;
import rx.functions.Func2;

public class EventAllRepositoryRead implements IEventAllRepositoryRead {

    private final static int DEFAULT_PAGE_SIZE = 20;

    private final IGetAllEventsService service;
    private final EventMapper mapper;

    public EventAllRepositoryRead(IGetAllEventsService service, EventMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public Observable<List<Event>> read() {
        return read(null);
    }

    @Override
    public Observable<List<Event>> read(String language) {
        return queryByPage(0, language)
                .scan(Collections.emptyList(), new Func2<List<Event>, List<Event>, List<Event>>() {
                    @Override
                    public List<Event> call(List<Event> accum, List<Event> newList) {
                        List<Event> result = new ArrayList<Event>(accum);
                        result.addAll(newList);
                        Collections.sort(result, (e1, e2) -> e1.getDate().compareTo(e2.getDate()));
                        return result;
                    }
                }).skip(1);
    }

    private Observable<List<Event>> queryByPage(int page, String language) {
        GetAllEventsServiceInput input = new GetAllEventsServiceInput.Builder()
                .page(page)
                .pageSize(DEFAULT_PAGE_SIZE)
                .language(language)
                .build();
        return service.execute(input)
                .toObservable()
                .map(eventList ->
                        StreamSupport.stream(eventList)
                                .map((value) -> mapper.map(value))
                                .collect(Collectors.toList())
                )
                .concatMap(eventList -> {
                    if (eventList.size() < DEFAULT_PAGE_SIZE) {
                        return Observable.just(eventList);
                    } else {
                        return Observable.just(eventList)
                                .concatWith(queryByPage(page + 1, language));
                    }
                });
    }
}
