package com.github.albertosh.adidaseventsapp.di.login;

import com.github.albertosh.adidaseventsapp.custom.CustomProperties;
import com.github.albertosh.adidaseventsapp.di.AppComponent;
import com.github.albertosh.adidaseventsapp.di.ComponentWithPresenter;
import com.github.albertosh.adidaseventsapp.di.PerController;
import com.github.albertosh.adidaseventsapp.ui.login.ILoginPresenter;

import dagger.Component;

@PerController
@Component(dependencies = {
        AppComponent.class
}, modules = {
        LoginModule.class
})
public interface LoginComponent extends ComponentWithPresenter<ILoginPresenter> {

    CustomProperties customProperties();

}
