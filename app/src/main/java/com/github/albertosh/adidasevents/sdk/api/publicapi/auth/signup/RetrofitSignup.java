package com.github.albertosh.adidasevents.sdk.api.publicapi.auth.signup;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Single;

public interface RetrofitSignup {

    @POST("auth/signup")
    Single<SignupServiceOutput> signup(@Body SignupServiceInput body);

}
