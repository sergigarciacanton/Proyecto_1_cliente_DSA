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
    Activity_My_Items activity;

    public void start(Activity_My_Items activity, int ID) {

        this.activity = activity;

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
        if(response.isSuccessful()) {
            activity.objectsList = response.body();
            activity.progressBar.setVisibility(View.INVISIBLE);
            activity.adapter.setData(response.body());
        }
        else {
            Toast.makeText(activity.getApplicationContext(), "Unexpected error.", Toast.LENGTH_LONG).show();
            activity.progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onFailure(Call<List<FullObject>> call, Throwable t) {
        Toast.makeText(activity.getApplicationContext(), "Unexpected error.", Toast.LENGTH_LONG).show();
        activity.progressBar.setVisibility(View.INVISIBLE);
    }
}
