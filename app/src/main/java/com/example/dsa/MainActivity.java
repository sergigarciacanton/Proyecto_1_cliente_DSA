package com.example.dsa;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dsa.models.CompleteCredentials;
import com.example.dsa.models.Credentials;



public class MainActivity extends AppCompatActivity {

    TextView usernameIn;
    TextView passwordIn;
    TextView nameIn;
    TextView mailIn;
    TextView nameText;
    TextView mailText;
    TextView signUpTextView;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usernameIn = this.findViewById(R.id.usernameIn);
        passwordIn = this.findViewById(R.id.passwordIn);
        nameIn = this.findViewById(R.id.nameIn);
        mailIn = this.findViewById(R.id.mailIn);
        nameText = this.findViewById(R.id.nameText);
        mailText = this.findViewById(R.id.mailText);
        signUpTextView = this.findViewById(R.id.signUpTextView);
        loginBtn = this.findViewById(R.id.logInBtn);

        nameIn.setVisibility(View.INVISIBLE);
        mailIn.setVisibility(View.INVISIBLE);
        nameText.setVisibility(View.INVISIBLE);
        mailText.setVisibility(View.INVISIBLE);
    }

    public void logInBtn_Click(View v) {
        if(loginBtn.getText() != "Log in") {
            ControllerLogin ctrl = new ControllerLogin();
            ctrl.start(new Credentials(usernameIn.getText().toString(), passwordIn.getText().toString()));
        }
        else {
            ControllerSignUp ctrl = new ControllerSignUp();
            ctrl.start(new CompleteCredentials(usernameIn.getText().toString(), passwordIn.getText().toString(), nameIn.getText().toString(), mailIn.getText().toString()));
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