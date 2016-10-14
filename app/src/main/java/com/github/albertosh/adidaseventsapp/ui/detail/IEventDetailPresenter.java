package com.github.albertosh.adidaseventsapp.ui.detail;

import android.support.annotation.Nullable;

import com.github.albertosh.adidaseventsapp.model.AEvent;
import com.github.albertosh.adidaseventsapp.ui.base.IBasePresenter;

public interface IEventDetailPresenter extends IBasePresenter<EventDetailView> {

    void loadEvent(@Nullable AEvent event, String eventId);

    void enrollEvent();

}
