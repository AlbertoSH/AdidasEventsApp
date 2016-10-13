package com.github.albertosh.adidaseventsapp.di.main;

import com.github.albertosh.adidaseventsapp.custom.CustomProperties;
import com.github.albertosh.adidaseventsapp.di.AppComponent;
import com.github.albertosh.adidaseventsapp.di.ComponentWithPresenter;
import com.github.albertosh.adidaseventsapp.di.PerController;
import com.github.albertosh.adidaseventsapp.ui.main.IMainPresenter;

import dagger.Component;

@PerController
@Component(dependencies = {
        AppComponent.class
}, modules = {
        MainModule.class
})
public interface MainComponent extends ComponentWithPresenter<IMainPresenter> {

    CustomProperties customProperties();

}
