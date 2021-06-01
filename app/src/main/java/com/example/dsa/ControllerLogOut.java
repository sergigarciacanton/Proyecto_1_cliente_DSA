package com.example.dsa;

import android.widget.Toast;

import com.example.dsa.models.Credentials;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ControllerLogOut implements Callback<Void> {

    //static final String BASE_URL = "http://10.0.2.2:8080/";
    static final String BASE_URL = "http://192.168.1.41:8080/";
    Toast t;
    MainMenu menu;

    public void start(MainMenu menu, Credentials c) {
        this.menu = menu;
        this.t = menu.toast;

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        Server server = retrofit.create(Server.class);

        Call<Void> call = server.logout(c);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Void> call, Response<Void> response) {
        if(response.isSuccessful()) {
            System.out.println("Log Out successful!");
        } else {
            System.out.println("Error: " + response.errorBody());
            Toast.makeText(menu.getApplicationContext(), "Error while logging out", Toast.LENGTH_LONG).show();
        }
        menu.finish();
    }

    @Override
    public void onFailure(Call<Void> call, Throwable t) {
        Toast.makeText(menu.getApplicationContext(), "Unexpected error.", Toast.LENGTH_LONG).show();
        t.printStackTrace();
        menu.finish();
    }
}
