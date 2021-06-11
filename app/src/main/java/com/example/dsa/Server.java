package com.example.dsa;

import com.example.dsa.models.CompleteCredentials;
import com.example.dsa.models.Credentials;
import com.example.dsa.models.FullObject;
import com.example.dsa.models.Game;
import com.example.dsa.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;


public interface Server {
    @POST("/dsaApp/auth/login")
    Call<Integer> login(@Body Credentials c);

    @POST("/dsaApp/auth/signup")
    Call<Integer> signUp(@Body CompleteCredentials c);

    @GET("/dsaApp/user/get/{username}")
    Call<User> getUser(@Path("username") Integer ID);

    @PUT("dsaApp/user/update")
    Call<Void> updateUser(@Body User u);

    @PUT("dsaApp/user/update/password")
    Call<Void> updatePassword(@Body User u);

    @DELETE("dsaApp/user/delete/{username}")
    Call<Void> deleteUser(@Path("username") Integer ID);

    @GET("/dsaApp/object/get/{id}")
    Call<List<FullObject>> getObjects(@Path("id") Integer id);

    @GET("/dsaApp/game/get/user/{id}")
    Call<List<Game>> getGames(@Path("id") Integer id);
}
