package com.example.dsa;

import android.view.View;
import com.example.dsa.models.Credentials;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ControllerLogin implements Callback<Credentials> {

    static final String BASE_URL = "https://192.168.1.42:8080/";

    public void start(Credentials c) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        Server server = retrofit.create(Server.class);

        Call<Credentials> call = server.login(c);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Credentials> call, Response<Credentials> response) {
        if(response.isSuccessful()) {
            System.out.println("Sign in successful!");
            //Snackbar snackbar = Snackbar.make(v, "Log in successful!", 5);
        } else {
            System.out.println("Error: " + response.errorBody());
            //Snackbar snackbar = Snackbar.make(v, "Log in successful!", 5);
        }
    }

    @Override
    public void onFailure(Call<Credentials> call, Throwable t) {
        t.printStackTrace();
    }
}
