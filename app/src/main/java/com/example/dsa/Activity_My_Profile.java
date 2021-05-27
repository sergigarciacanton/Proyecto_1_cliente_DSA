package com.example.dsa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_My_Profile extends AppCompatActivity {

    TextView titleTextView;
    TextView usernameText;
    TextView usernameOut;
    TextView nameOut;
    TextView mailOut;
    TextView nameText;
    TextView mailText;
    TextView moneyText;
    TextView moneyOut;
    Button returnBtn;
    ProgressBar progressBar;
    TextView loadingTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        titleTextView = findViewById(R.id.titleProfileText);
        usernameText = findViewById(R.id.usernameProfileText);
        usernameOut = findViewById(R.id.usernameProfileOut);
        nameText = findViewById(R.id.nameProfileText);
        nameOut = findViewById(R.id.nameProfileOut);
        mailText = findViewById(R.id.mailProfileText);
        mailOut = findViewById(R.id.mailProfileOut);
        moneyText = findViewById(R.id.moneyProfileText);
        moneyOut = findViewById(R.id.moneyProfileOut);
        returnBtn = findViewById(R.id.returnBtn);
        progressBar = findViewById(R.id.profileProgressBar);
        loadingTextView = findViewById(R.id.loadingProfileText);

        titleTextView.setVisibility(View.INVISIBLE);
        usernameText.setVisibility(View.INVISIBLE);
        usernameOut.setVisibility(View.INVISIBLE);
        nameText.setVisibility(View.INVISIBLE);
        nameOut.setVisibility(View.INVISIBLE);
        mailText.setVisibility(View.INVISIBLE);
        mailOut.setVisibility(View.INVISIBLE);
        returnBtn.setVisibility(View.INVISIBLE);
        moneyOut.setVisibility(View.INVISIBLE);
        moneyText.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        loadingTextView.setVisibility(View.VISIBLE);

        String username = getIntent().getStringExtra("username");

        ControllerGetUser ctrl = new ControllerGetUser();
        ctrl.start(this, username);
    }

    public void returnBtn_Click(View v) {
        finish();
    }
}