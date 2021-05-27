package com.example.dsa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Toast;

import com.example.dsa.models.CompleteCredentials;
import com.example.dsa.models.Credentials;
import com.example.dsa.models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ControllerGetUser implements Callback<User> {

    static final String BASE_URL = "http://10.0.2.2:8080/";
    Activity_My_Profile activity;

    public void start(Activity_My_Profile activity, String username) {

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

        Call<User> call = server.getUser(username);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<User> call, Response<User> response) {
        if(response.isSuccessful()) {
            User user = response.body();
            activity.usernameOut.setText(user.getUsername());
            activity.nameOut.setText(user.getFullName());
            activity.mailOut.setText(user.getEmail());
            activity.moneyOut.setText(String.valueOf(user.getMoney()));

            activity.titleTextView.setVisibility(View.VISIBLE);
            activity.usernameText.setVisibility(View.VISIBLE);
            activity.usernameOut.setVisibility(View.VISIBLE);
            activity.nameText.setVisibility(View.VISIBLE);
            activity.nameOut.setVisibility(View.VISIBLE);
            activity.mailText.setVisibility(View.VISIBLE);
            activity.mailOut.setVisibility(View.VISIBLE);
            activity.returnBtn.setVisibility(View.VISIBLE);
            activity.moneyOut.setVisibility(View.VISIBLE);
            activity.moneyText.setVisibility(View.VISIBLE);
            activity.progressBar.setVisibility(View.INVISIBLE);
            activity.loadingTextView.setVisibility(View.INVISIBLE);
        }
        else {
            Toast.makeText(activity.getApplicationContext(), "Unexpected error.", Toast.LENGTH_LONG).show();
            activity.returnBtn.setVisibility(View.VISIBLE);
            activity.progressBar.setVisibility(View.INVISIBLE);
            activity.loadingTextView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onFailure(Call<User> call, Throwable t) {
        Toast.makeText(activity.getApplicationContext(), "Unexpected error.", Toast.LENGTH_LONG).show();
        activity.returnBtn.setVisibility(View.VISIBLE);
        activity.progressBar.setVisibility(View.INVISIBLE);
        activity.loadingTextView.setVisibility(View.INVISIBLE);
    }
}
