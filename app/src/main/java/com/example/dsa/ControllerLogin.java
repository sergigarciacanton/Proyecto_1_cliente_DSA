package com.example.dsa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Toast;

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

public class ControllerLogin implements Callback<Integer> {

    //static final String BASE_URL = "http://10.0.2.2:8080/";
    static final String BASE_URL = "http://192.168.1.41:8080/";
    MainActivity main;
    Activity_My_Profile activity;
    Credentials c;

    public void start(MainActivity main, Credentials c) {
        this.main = main;
        this.activity = null;
        this.c = c;

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

        Call<Integer> call = server.login(c);
        call.enqueue(this);
    }

    public void start(Activity_My_Profile activity, Credentials c) {
        this.activity = activity;
        this.main = null;
        this.c = c;

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

        Call<Integer> call = server.login(c);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Integer> call, Response<Integer> response) {
        if(activity == null) {
            if (response.isSuccessful()) {
                System.out.println("Login successful!");
                Toast.makeText(main.getApplicationContext(), "Login successful!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(main, MainMenu.class);
                intent.putExtra("username", c.getUsername());
                intent.putExtra("password", c.getPassword());
                intent.putExtra("ID", response.body());
                main.progressBar.setVisibility(View.INVISIBLE);
                main.passwordText.setVisibility(View.VISIBLE);
                main.usernameText.setVisibility(View.VISIBLE);
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
                Toast.makeText(main.getApplicationContext(), "Error: Wrong credentials.", Toast.LENGTH_LONG).show();
                main.progressBar.setVisibility(View.INVISIBLE);
                main.passwordText.setVisibility(View.VISIBLE);
                main.usernameText.setVisibility(View.VISIBLE);
                main.passwordIn.setVisibility(View.VISIBLE);
                main.usernameIn.setVisibility(View.VISIBLE);
                main.loginBtn.setVisibility(View.VISIBLE);
                main.signUpTextView.setVisibility(View.VISIBLE);
            }
        }
        else {
            if(response.isSuccessful()) {
                ControllerUpdatePassword ctrl = new ControllerUpdatePassword();
                ctrl.start(activity, new User(String.valueOf(activity.editPasswordNew1.getText()), activity.id));
            }
            else {
                Toast.makeText(activity.getApplicationContext(), "Error: Current password is wrong.", Toast.LENGTH_LONG).show();
                activity.titleTextView.setVisibility(View.VISIBLE);
                activity.editPasswordOld.setVisibility(View.VISIBLE);
                activity.editPasswordNew1.setVisibility(View.VISIBLE);
                activity.editPasswordNew2.setVisibility(View.VISIBLE);
                activity.usernameText.setVisibility(View.VISIBLE);
                activity.nameText.setVisibility(View.VISIBLE);
                activity.mailText.setVisibility(View.VISIBLE);
                activity.deleteBtn.setVisibility(View.VISIBLE);
                activity.returnBtn.setVisibility(View.VISIBLE);
                activity.progressBar.setVisibility(View.INVISIBLE);
                activity.loadingTextView.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public void onFailure(Call<Integer> call, Throwable t) {
        if(activity == null) {
            main.progressBar.setVisibility(View.INVISIBLE);
            main.passwordText.setVisibility(View.VISIBLE);
            main.usernameText.setVisibility(View.VISIBLE);
            main.passwordIn.setVisibility(View.VISIBLE);
            main.usernameIn.setVisibility(View.VISIBLE);
            main.loginBtn.setVisibility(View.VISIBLE);
            main.signUpTextView.setVisibility(View.VISIBLE);
            Toast.makeText(main.getApplicationContext(), "Unexpected error.", Toast.LENGTH_LONG).show();
            t.printStackTrace();
        }
        else {
            Toast.makeText(activity.getApplicationContext(), "Unexpected error.", Toast.LENGTH_LONG).show();
            activity.titleTextView.setVisibility(View.VISIBLE);
            activity.editPasswordOld.setVisibility(View.VISIBLE);
            activity.editPasswordNew1.setVisibility(View.VISIBLE);
            activity.editPasswordNew2.setVisibility(View.VISIBLE);
            activity.usernameText.setVisibility(View.VISIBLE);
            activity.nameText.setVisibility(View.VISIBLE);
            activity.mailText.setVisibility(View.VISIBLE);
            activity.deleteBtn.setVisibility(View.VISIBLE);
            activity.returnBtn.setVisibility(View.VISIBLE);
            activity.progressBar.setVisibility(View.INVISIBLE);
            activity.loadingTextView.setVisibility(View.INVISIBLE);
        }
    }
}
