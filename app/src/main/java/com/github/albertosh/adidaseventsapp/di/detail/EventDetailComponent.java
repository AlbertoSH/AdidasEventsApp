package com.github.albertosh.adidaseventsapp.di.detail;

import com.github.albertosh.adidaseventsapp.custom.CustomProperties;
import com.github.albertosh.adidaseventsapp.di.AppComponent;
import com.github.albertosh.adidaseventsapp.di.ComponentWithPresenter;
import com.github.albertosh.adidaseventsapp.di.PerController;
import com.github.albertosh.adidaseventsapp.ui.detail.IEventDetailPresenter;

import dagger.Component;

@PerController
@Component(dependencies = {
        AppComponent.class
}, modules = {
        EventDetailModule.class
})
public interface EventDetailComponent extends ComponentWithPresenter<IEventDetailPresenter> {

    CustomProperties customProperties();
}
