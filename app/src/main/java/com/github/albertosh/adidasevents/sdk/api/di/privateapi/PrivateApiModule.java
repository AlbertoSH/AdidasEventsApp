package com.github.albertosh.adidasevents.sdk.api.di.privateapi;

import com.github.albertosh.adidasevents.sdk.api.RxErrorHandlingCallAdapterFactory;
import com.github.albertosh.adidasevents.sdk.api.di.privateapi.events.PrivateEventsApiModule;
import com.github.albertosh.adidasevents.sdk.api.privateapi.PrivateApiInterceptor;
import com.github.albertosh.adidasevents.sdk.scopes.PerUser;

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
        PrivateEventsApiModule.class
})
public class PrivateApiModule {

    private static final String DEFAULT_AUTH_HEADER = "Authorization";
    private final String token;
    private final String apiBaseUrl;
    private final String authHeader;

    public PrivateApiModule(String token) {
        this(DEFAULT_API_BASE_URL, token);
    }

    public PrivateApiModule(String apiBaseUrl, String token) {
        this(apiBaseUrl, DEFAULT_AUTH_HEADER, token);
    }

    public PrivateApiModule(String apiBaseUrl, String authHeader, String token) {
        this.apiBaseUrl = apiBaseUrl;
        this.authHeader = authHeader;
        this.token = token;
    }

    @Provides @Named("token")
    String token() {
        return token;
    }

    @Provides
    @PrivateApi
    String apiBaseUrl() {
        return apiBaseUrl;
    }

    @Provides @Named("authHeader")
    String authHeader() {
        return authHeader;
    }

    @Provides
    @PerUser
    @PrivateApi
    Retrofit privateRetrofit(@PrivateApi OkHttpClient client, @PrivateApi Retrofit.Builder builder) {
        return builder
                .client(client)
                .build();
    }

    @Provides
    @PrivateApi
    @PerUser
    public OkHttpClient httpClient(PrivateApiInterceptor privateApiInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(privateApiInterceptor)
                .build();
    }

    @Provides
    @PerUser
    public PrivateApiInterceptor privateApiInterceptor(@Named("token") String token,
                                                       @Named("authHeader") String authHeader) {
        return new PrivateApiInterceptor(token, authHeader);
    }


    @Provides
    @PrivateApi
    @PerUser
    Retrofit.Builder retrofitBuilder(@PrivateApi String apiBaseUrl,
                                     @PrivateApi CallAdapter.Factory adapterFactory,
                                     @PrivateApi Converter.Factory converterFactory) {
        return new Retrofit.Builder()
                .baseUrl(apiBaseUrl)
                .addCallAdapterFactory(adapterFactory)
                .addConverterFactory(converterFactory);
    }

    @Provides
    @PrivateApi
    CallAdapter.Factory adapterFactory() {
        return RxErrorHandlingCallAdapterFactory.create();
    }

    @Provides
    @PrivateApi
    Converter.Factory converterFactory() {
        return GsonConverterFactory.create();
    }
}
