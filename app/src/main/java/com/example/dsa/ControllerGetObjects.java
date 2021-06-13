package com.example.dsa;

import android.view.View;
import android.widget.Toast;

import com.example.dsa.models.FullObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ControllerGetObjects implements Callback<List<FullObject>> {

    //static final String BASE_URL = "http://10.0.2.2:8080/";
    static final String BASE_URL = "http://192.168.1.41:8080/";
    Activity_My_Items activityItems;
    Activity_New_Game activityNewGame;

    public void start(Activity_My_Items activity, int ID) {

        this.activityItems = activity;
        this.activityNewGame = null;

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

        Call<List<FullObject>> call = server.getObjects(ID);
        call.enqueue(this);
    }

    public void start(Activity_New_Game activity, int ID) {

        this.activityNewGame = activity;
        this.activityItems = null;

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

        Call<List<FullObject>> call = server.getObjects(ID);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<FullObject>> call, Response<List<FullObject>> response) {
        if(activityNewGame == null) {
            if (response.isSuccessful()) {
                activityItems.objectsList = response.body();
                activityItems.progressBar.setVisibility(View.INVISIBLE);
                activityItems.titleText.setVisibility(View.VISIBLE);
                activityItems.adapter.setData(response.body());
            } else {
                Toast.makeText(activityItems.getApplicationContext(), "Unexpected error.", Toast.LENGTH_LONG).show();
                activityItems.progressBar.setVisibility(View.INVISIBLE);
            }
        }
        else {
            if (response.isSuccessful()) {
                activityNewGame.objectsList = response.body();
                activityNewGame.progressBar.setVisibility(View.INVISIBLE);
                activityNewGame.titleText.setVisibility(View.VISIBLE);
                activityNewGame.adapter.setData(response.body());
            } else {
                Toast.makeText(activityNewGame.getApplicationContext(), "Unexpected error.", Toast.LENGTH_LONG).show();
                activityNewGame.progressBar.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public void onFailure(Call<List<FullObject>> call, Throwable t) {
        if(activityNewGame == null) {
            Toast.makeText(activityItems.getApplicationContext(), "Unexpected error.", Toast.LENGTH_LONG).show();
            activityItems.progressBar.setVisibility(View.INVISIBLE);
        }
        else {
            Toast.makeText(activityNewGame.getApplicationContext(), "Unexpected error.", Toast.LENGTH_LONG).show();
            activityNewGame.progressBar.setVisibility(View.INVISIBLE);
        }
    }
}
