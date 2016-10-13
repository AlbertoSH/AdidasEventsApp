package com.github.albertosh.adidasevents.sdk.api.publicapi.auth.login;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Single;

public interface RetrofitLogin {

    @POST("auth/signup")
    Single<LoginServiceOutput> login(@Body LoginServiceInput body);

}
