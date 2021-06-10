package com.example.dsa;

import android.view.View;
import android.widget.Toast;

import com.example.dsa.models.Game;
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

public class ControllerGetGames implements Callback<List<Game>> {

    //static final String BASE_URL = "http://10.0.2.2:8080/";
    static final String BASE_URL = "http://192.168.1.41:8080/";
    Activity_Statistics activity;

    public void start(Activity_Statistics activity, int ID) {

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

        Call<List<Game>> call = server.getGames(ID);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Game>> call, Response<List<Game>> response) {
        if(response.isSuccessful()) {
            activity.gamesList = response.body();
            activity.progressBar.setVisibility(View.INVISIBLE);
            activity.titleText.setVisibility(View.VISIBLE);
            activity.adapter.setData(response.body());
        }
        else {
            Toast.makeText(activity.getApplicationContext(), "Unexpected error.", Toast.LENGTH_LONG).show();
            activity.progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onFailure(Call<List<Game>> call, Throwable t) {
        Toast.makeText(activity.getApplicationContext(), "Unexpected error.", Toast.LENGTH_LONG).show();
        activity.progressBar.setVisibility(View.INVISIBLE);
    }
}
