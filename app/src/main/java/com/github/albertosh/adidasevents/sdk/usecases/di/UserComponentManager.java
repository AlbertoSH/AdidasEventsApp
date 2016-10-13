package com.github.albertosh.adidasevents.sdk.usecases.di;

public interface UserComponentManager {

    public UserUseCasesComponent getUserComponent();

    public void createUserComponent(String token);

    public void deleteUserComponent();

}
