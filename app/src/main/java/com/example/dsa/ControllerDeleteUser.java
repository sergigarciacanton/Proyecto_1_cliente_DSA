package com.example.dsa;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ControllerDeleteUser implements Callback<Void> {

    //static final String BASE_URL = "http://10.0.2.2:8080/";
    static final String BASE_URL = "http://192.168.1.41:8080/";
    Activity_My_Profile activity;

    public void start(Activity_My_Profile activity, int id) {

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

        Call<Void> call = server.deleteUser(id);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Void> call, Response<Void> response) {
        if(response.isSuccessful()) {
            System.out.println("Account deleted!");
            Toast.makeText(activity.getApplicationContext(), "Deleted account. Thanks for playing!", Toast.LENGTH_LONG).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent returnIntent = new Intent();
                    activity.setResult(Activity.RESULT_OK,returnIntent);
                    activity.finish();
                }
            }, 2000);
        } else {
            System.out.println("Error: " + response.errorBody());
            Toast.makeText(activity.getApplicationContext(), "Unexpected error.", Toast.LENGTH_LONG).show();
            activity.titleTextView.setVisibility(View.VISIBLE);
            activity.editPasswordOld.setVisibility(View.VISIBLE);
            activity.usernameText.setVisibility(View.VISIBLE);
            activity.deleteBtn.setVisibility(View.VISIBLE);
            activity.returnBtn.setVisibility(View.VISIBLE);
            activity.progressBar.setVisibility(View.INVISIBLE);
            activity.loadingTextView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onFailure(Call<Void> call, Throwable t) {
        Toast.makeText(activity.getApplicationContext(), "Unexpected error.", Toast.LENGTH_LONG).show();
        t.printStackTrace();
        activity.titleTextView.setVisibility(View.VISIBLE);
        activity.editPasswordOld.setVisibility(View.VISIBLE);
        activity.usernameText.setVisibility(View.VISIBLE);
        activity.deleteBtn.setVisibility(View.VISIBLE);
        activity.returnBtn.setVisibility(View.VISIBLE);
        activity.progressBar.setVisibility(View.INVISIBLE);
        activity.loadingTextView.setVisibility(View.INVISIBLE);
    }
}
