package com.example.dsa;

import com.example.dsa.models.CompleteCredentials;
import com.example.dsa.models.Credentials;

import retrofit2.Call;
import retrofit2.http.*;


public interface Server {
    @POST("/dsaApp/auth/login")
    Call<Void> login(@Body Credentials c);

    @POST("/dsaApp/auth/signup")
    Call<Void> signUp(@Body CompleteCredentials c);
}
