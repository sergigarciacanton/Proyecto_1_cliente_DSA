package com.example.dsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dsa.models.CompleteCredentials;
import com.example.dsa.models.Credentials;

public class Activity_Login extends AppCompatActivity {

    TextView usernameText;
    TextView usernameIn;
    TextView passwordText;
    TextView passwordIn;
    TextView nameIn;
    TextView mailIn;
    TextView nameText;
    TextView mailText;
    TextView signUpTextView;
    Button loginBtn;
    Toast toast;
    ProgressBar progressBar;

    String username;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameText = this.findViewById(R.id.loginUsernameText);
        usernameIn = this.findViewById(R.id.loginUsernameIn);
        passwordText = this.findViewById(R.id.loginPasswordText);
        passwordIn = this.findViewById(R.id.loginPasswordIn);
        nameIn = this.findViewById(R.id.loginNameIn);
        mailIn = this.findViewById(R.id.loginMailIn);
        nameText = this.findViewById(R.id.loginNameText);
        mailText = this.findViewById(R.id.loginMailText);
        signUpTextView = this.findViewById(R.id.signUpTextView);
        loginBtn = this.findViewById(R.id.loginBtn);
        progressBar = this.findViewById(R.id.loginProgressBar);
        loginBtn.setText("Log in");

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        username = sh.getString("username", null);
        password = sh.getString("password", null);

        if(username == null || password == null || username == "" || password == "") {
            nameIn.setVisibility(View.INVISIBLE);
            mailIn.setVisibility(View.INVISIBLE);
            nameText.setVisibility(View.INVISIBLE);
            mailText.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
        }
        else {
            progressBar.setVisibility(View.VISIBLE);
            mailText.setVisibility(View.INVISIBLE);
            nameText.setVisibility(View.INVISIBLE);
            passwordText.setVisibility(View.INVISIBLE);
            usernameText.setVisibility(View.INVISIBLE);
            mailIn.setVisibility(View.INVISIBLE);
            nameIn.setVisibility(View.INVISIBLE);
            passwordIn.setVisibility(View.INVISIBLE);
            usernameIn.setVisibility(View.INVISIBLE);
            loginBtn.setVisibility(View.INVISIBLE);
            signUpTextView.setVisibility(View.INVISIBLE);
            ControllerLogin ctrl = new ControllerLogin();
            ctrl.start(this, new Credentials(username, password));
        }
    }

    public void logInBtn_Click(View v) {
        progressBar.setVisibility(View.VISIBLE);
        mailText.setVisibility(View.INVISIBLE);
        nameText.setVisibility(View.INVISIBLE);
        passwordText.setVisibility(View.INVISIBLE);
        usernameText.setVisibility(View.INVISIBLE);
        mailIn.setVisibility(View.INVISIBLE);
        nameIn.setVisibility(View.INVISIBLE);
        passwordIn.setVisibility(View.INVISIBLE);
        usernameIn.setVisibility(View.INVISIBLE);
        loginBtn.setVisibility(View.INVISIBLE);
        signUpTextView.setVisibility(View.INVISIBLE);

        if(loginBtn.getText() == "Log in") {
            ControllerLogin ctrl = new ControllerLogin();
            ctrl.start(this, new Credentials(usernameIn.getText().toString(), passwordIn.getText().toString()));
        }
        else {
            ControllerSignUp ctrl = new ControllerSignUp();
            ctrl.start(this , new CompleteCredentials(usernameIn.getText().toString(), passwordIn.getText().toString(), nameIn.getText().toString(), mailIn.getText().toString()));
        }
    }

    public void signUpTextView_Click (View v) {
        if(signUpTextView.getText().equals("Sign up")) {
            signUpTextView.setText("Log in");
            loginBtn.setText("Sign up");
            nameIn.setVisibility(View.VISIBLE);
            mailIn.setVisibility(View.VISIBLE);
            nameText.setVisibility(View.VISIBLE);
            mailText.setVisibility(View.VISIBLE);
        }
        else {
            signUpTextView.setText("Sign up");
            loginBtn.setText("Log in");
            nameIn.setVisibility(View.INVISIBLE);
            mailIn.setVisibility(View.INVISIBLE);
            nameText.setVisibility(View.INVISIBLE);
            mailText.setVisibility(View.INVISIBLE);
        }
    }
}