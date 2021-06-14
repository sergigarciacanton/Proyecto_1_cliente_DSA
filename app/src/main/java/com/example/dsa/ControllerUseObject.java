package com.example.dsa;

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

public class ControllerUseObject implements Callback<Void> {

    //static final String BASE_URL = "http://10.0.2.2:8080/";
    static final String BASE_URL = "http://192.168.1.41:8080/";
    Activity_New_Game activity;
    int idObject;

    public void start(Activity_New_Game activity, int idObject, int idUser) {

        this.activity = activity;
        this.idObject = idObject;

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

        Call<Void> call = server.useObject(idObject, idUser);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Void> call, Response<Void> response) {
        if(response.isSuccessful()) {
            int i = 0;
            boolean found = false;
            while (i < activity.objectsIdList.size() && !found) {
                if(activity.objectsIdList.get(i).equals(idObject))
                    found = true;
                else
                    i++;
            }
            if(found) {
                int res = activity.objectsIdList.remove(i);
            }
            if(activity.objectsIdList.size() == 0) {
                Toast.makeText(activity.getApplicationContext(), "All objects selected are in use. Good game!", Toast.LENGTH_LONG).show();
                Toast.makeText(activity.getApplicationContext(), "Game is still not implemented...", Toast.LENGTH_LONG).show();
            }
        } else {
            System.out.println("Error: " + response.errorBody());
            Toast.makeText(activity.getApplicationContext(), "Unexpected error.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onFailure(Call<Void> call, Throwable t) {
        Toast.makeText(activity.getApplicationContext(), "Unexpected error.", Toast.LENGTH_LONG).show();
    }
}
