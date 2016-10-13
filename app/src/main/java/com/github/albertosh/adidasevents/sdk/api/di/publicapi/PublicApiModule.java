package com.github.albertosh.adidasevents.sdk.api.di.publicapi;

import com.github.albertosh.adidasevents.sdk.api.RxErrorHandlingCallAdapterFactory;
import com.github.albertosh.adidasevents.sdk.api.di.publicapi.auth.AuthModule;
import com.github.albertosh.adidasevents.sdk.api.di.publicapi.events.EventsModule;
import com.github.albertosh.adidasevents.sdk.scopes.PerApplication;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = {
        AuthModule.class,
        EventsModule.class
})
public class PublicApiModule {

    private static final String DEFAULT_API_BASE_URL = "http://192.168.1.103:9000/";

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

}
