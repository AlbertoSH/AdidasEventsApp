package com.github.albertosh.adidasevents.sdk.api.di.publicapi;

import com.github.albertosh.adidasevents.sdk.api.RxErrorHandlingCallAdapterFactory;
import com.github.albertosh.adidasevents.sdk.api.di.publicapi.auth.AuthApiModule;
import com.github.albertosh.adidasevents.sdk.api.di.publicapi.events.PublicEventsApiModule;
import com.github.albertosh.adidasevents.sdk.api.publicapi.custom.ICustomService;
import com.github.albertosh.adidasevents.sdk.scopes.PerApplication;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.github.albertosh.adidasevents.sdk.api.di.Defaults.DEFAULT_API_BASE_URL;

@Module(includes = {
        AuthApiModule.class,
        PublicEventsApiModule.class
})
public class PublicApiModule {

    private final String apiBaseUrl;

    public PublicApiModule() {
        this(DEFAULT_API_BASE_URL);
    }

    public PublicApiModule(String apiBaseUrl) {
        this.apiBaseUrl = apiBaseUrl;
    }

    @Provides
    @PerApplication
    Retrofit.Builder retrofitBuilder(@Named("apiBaseUrl") String apiBaseUrl,
                                     CallAdapter.Factory adapterFactory,
                                     Converter.Factory converterFactory) {
        return new Retrofit.Builder()
                .baseUrl(apiBaseUrl)
                .addCallAdapterFactory(adapterFactory)
                .addConverterFactory(converterFactory);
    }

    @Provides
    @Named("apiBaseUrl")
    String apiBaseUrl() {
        return apiBaseUrl;
    }

    @Provides
    CallAdapter.Factory adapterFactory() {
        return RxErrorHandlingCallAdapterFactory.create();
    }

    @Provides
    Converter.Factory converterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    @PerApplication
    Retrofit retrofit(OkHttpClient client, Retrofit.Builder builder) {
        return builder.client(client).build();
    }

    @Provides
    @PerApplication
    OkHttpClient httpClient() {
        return new OkHttpClient.Builder().build();
    }

    @Provides @PerApplication
    ICustomService customService(Retrofit retrofit) {
        return retrofit.create(ICustomService.class);
    }
}
