package com.app.mvvmsample.retrofit;

import com.app.mvvmsample.models.login.LoginRequest;
import com.app.mvvmsample.models.login.LoginResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Elmohandes on 20/09/2018.
 */

public interface Api {
    @POST("auth/login")
    Observable<LoginResponse> login(@Body LoginRequest loginRequest);
}
