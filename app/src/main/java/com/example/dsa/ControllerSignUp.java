package com.example.dsa;

import com.example.dsa.models.CompleteCredentials;
import com.example.dsa.models.Credentials;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ControllerSignUp implements Callback<CompleteCredentials> {

    static final String BASE_URL = "http://10.0.2.2:8080/";

    public void start(CompleteCredentials c) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        Server server = retrofit.create(Server.class);

        Call<CompleteCredentials> call = server.signUp(c);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<CompleteCredentials> call, Response<CompleteCredentials> response) {
        if(response.isSuccessful()) {
            System.out.println("Sign up successful!");
            //Snackbar snackbar = Snackbar.make(v, "Sign up successful!", 5);
        } else {
            System.out.println("Error: " + response.errorBody());
            //Snackbar snackbar = Snackbar.make(v, "Log in successful!", 5);
        }
    }

    @Override
    public void onFailure(Call<CompleteCredentials> call, Throwable t) {
        t.printStackTrace();
    }
}
