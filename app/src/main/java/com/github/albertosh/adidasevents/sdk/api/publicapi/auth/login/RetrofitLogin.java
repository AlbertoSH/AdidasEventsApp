package com.github.albertosh.adidasevents.sdk.api.publicapi.auth.login;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Single;

public interface RetrofitLogin {

    @POST("user/login")
    Single<LoginServiceOutput> login(@Body LoginServiceInput body);

}
