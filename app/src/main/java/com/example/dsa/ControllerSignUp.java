package com.example.dsa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Toast;

import com.example.dsa.models.CompleteCredentials;
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

public class ControllerSignUp implements Callback<Integer> {

    static final String BASE_URL = "http://192.168.1.41:8080/";
    Toast t;
    MainActivity main;
    Credentials c;

    public void start(MainActivity main, CompleteCredentials c) {

        this.main = main;
        this.t = main.toast;
        this.c = new Credentials(c.getUsername(), c.getPassword());

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

        Call<Integer> call = server.signUp(c);
        call.enqueue(this);

    }

    @Override
    public void onResponse(Call<Integer> call, Response<Integer> response) {
        if(response.isSuccessful()) {
            System.out.println("Sign up successful!");
            Toast.makeText(main.getApplicationContext(), "Sign up successful!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(main, MainMenu.class);
            intent.putExtra("username", c.getUsername());
            intent.putExtra("password", c.getPassword());
            intent.putExtra("ID", response.body());
            main.progressBar.setVisibility(View.INVISIBLE);
            main.mailText.setVisibility(View.VISIBLE);
            main.nameText.setVisibility(View.VISIBLE);
            main.passwordText.setVisibility(View.VISIBLE);
            main.usernameText.setVisibility(View.VISIBLE);
            main.mailIn.setVisibility(View.VISIBLE);
            main.nameIn.setVisibility(View.VISIBLE);
            main.passwordIn.setVisibility(View.VISIBLE);
            main.usernameIn.setVisibility(View.VISIBLE);
            main.loginBtn.setVisibility(View.VISIBLE);
            main.signUpTextView.setVisibility(View.VISIBLE);

            SharedPreferences sharedPreferences = main.getSharedPreferences("MySharedPref", 0);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();

            myEdit.putString("username", main.usernameIn.getText().toString());
            myEdit.putString("password", main.passwordIn.getText().toString());
            myEdit.apply();

            main.startActivity(intent);
        } else {
            System.out.println("Error: " + response.errorBody());
            Toast.makeText(main.getApplicationContext(), "Error. Username does already exist.", Toast.LENGTH_LONG).show();
            main.progressBar.setVisibility(View.INVISIBLE);
            main.mailText.setVisibility(View.VISIBLE);
            main.nameText.setVisibility(View.VISIBLE);
            main.passwordText.setVisibility(View.VISIBLE);
            main.usernameText.setVisibility(View.VISIBLE);
            main.mailIn.setVisibility(View.VISIBLE);
            main.nameIn.setVisibility(View.VISIBLE);
            main.passwordIn.setVisibility(View.VISIBLE);
            main.usernameIn.setVisibility(View.VISIBLE);
            main.loginBtn.setVisibility(View.VISIBLE);
            main.signUpTextView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onFailure(Call<Integer> call, Throwable t) {
        Toast.makeText(main.getApplicationContext(), "Unexpected error.", Toast.LENGTH_LONG).show();
        t.printStackTrace();
        main.progressBar.setVisibility(View.INVISIBLE);
        main.mailText.setVisibility(View.VISIBLE);
        main.nameText.setVisibility(View.VISIBLE);
        main.passwordText.setVisibility(View.VISIBLE);
        main.usernameText.setVisibility(View.VISIBLE);
        main.mailIn.setVisibility(View.VISIBLE);
        main.nameIn.setVisibility(View.VISIBLE);
        main.passwordIn.setVisibility(View.VISIBLE);
        main.usernameIn.setVisibility(View.VISIBLE);
        main.loginBtn.setVisibility(View.VISIBLE);
        main.signUpTextView.setVisibility(View.VISIBLE);
    }
}
