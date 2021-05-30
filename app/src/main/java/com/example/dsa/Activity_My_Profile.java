package com.example.dsa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dsa.models.User;

public class Activity_My_Profile extends AppCompatActivity {

    TextView titleTextView;
    ImageView editProfileImage;
    TextView usernameText;
    TextView usernameOut;
    TextView usernameEdit;
    TextView nameText;
    TextView nameOut;
    TextView nameEdit;
    TextView mailText;
    TextView mailOut;
    TextView mailEdit;
    TextView moneyText;
    TextView moneyOut;
    Button deleteBtn;
    Button returnBtn;
    ProgressBar progressBar;
    TextView loadingTextView;

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        titleTextView = findViewById(R.id.titleProfileText);
        editProfileImage = findViewById(R.id.editProfileImage);
        editProfileImage.setImageResource(R.drawable.pencil_edit);
        usernameText = findViewById(R.id.usernameProfileText);
        usernameOut = findViewById(R.id.usernameProfileOut);
        usernameEdit = findViewById(R.id.editProfileUsernameOut);
        nameText = findViewById(R.id.nameProfileText);
        nameOut = findViewById(R.id.nameProfileOut);
        nameEdit = findViewById(R.id.editProfileNameOut);
        mailText = findViewById(R.id.mailProfileText);
        mailOut = findViewById(R.id.mailProfileOut);
        mailEdit = findViewById(R.id.editProfileMailOut);
        moneyText = findViewById(R.id.moneyProfileText);
        moneyOut = findViewById(R.id.moneyProfileOut);
        deleteBtn = findViewById(R.id.deleteBtn);
        deleteBtn.setText("Delete account");
        returnBtn = findViewById(R.id.returnBtn);
        returnBtn.setText("Return");
        progressBar = findViewById(R.id.profileProgressBar);
        loadingTextView = findViewById(R.id.loadingProfileText);

        titleTextView.setVisibility(View.INVISIBLE);
        editProfileImage.setVisibility(View.INVISIBLE);
        usernameText.setVisibility(View.INVISIBLE);
        usernameOut.setVisibility(View.INVISIBLE);
        usernameEdit.setVisibility(View.INVISIBLE);
        nameText.setVisibility(View.INVISIBLE);
        nameOut.setVisibility(View.INVISIBLE);
        nameEdit.setVisibility(View.INVISIBLE);
        mailText.setVisibility(View.INVISIBLE);
        mailOut.setVisibility(View.INVISIBLE);
        mailEdit.setVisibility(View.INVISIBLE);
        deleteBtn.setVisibility(View.INVISIBLE);
        returnBtn.setVisibility(View.INVISIBLE);
        moneyOut.setVisibility(View.INVISIBLE);
        moneyText.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        loadingTextView.setVisibility(View.VISIBLE);

        int ID = getIntent().getIntExtra("ID", 0);
        this.id = ID;

        ControllerGetUser ctrl = new ControllerGetUser();
        ctrl.start(this, ID);
    }

    public void returnBtn_Click(View v) {
        if(returnBtn.getText() == "Return")
            finish();
        else {
            ControllerUpdateUser ctrl = new ControllerUpdateUser();
            ctrl.start(this, new User(String.valueOf(usernameEdit.getText()), String.valueOf(nameEdit.getText()),
                    String.valueOf(mailEdit.getText()), Integer.parseInt(String.valueOf(moneyOut.getText())), id));
        }
    }

    public void editProfileImage_Click (View v) {
        if(returnBtn.getText() == "Return") {
            titleTextView.setText("Edit profile");
            editProfileImage.setImageResource(R.drawable.key);
            usernameOut.setVisibility(View.INVISIBLE);
            usernameEdit.setVisibility(View.VISIBLE);
            nameOut.setVisibility(View.INVISIBLE);
            nameEdit.setVisibility(View.VISIBLE);
            mailOut.setVisibility(View.INVISIBLE);
            mailEdit.setVisibility(View.VISIBLE);
            moneyOut.setVisibility(View.INVISIBLE);
            moneyText.setVisibility(View.INVISIBLE);
            deleteBtn.setText("Cancel");
            returnBtn.setText("Submit changes");
        }
        else {
            Toast.makeText(getApplicationContext(), "Ooops... not implemented.", Toast.LENGTH_LONG).show();
        }
    }

    public void deleteBtn_Click (View v) {
        if(returnBtn.getText() == "Return")
            Toast.makeText(getApplicationContext(), "Ooops... not implemented.", Toast.LENGTH_LONG).show();
        else {
            titleTextView.setText("Profile");
            editProfileImage.setImageResource(R.drawable.pencil_edit);
            usernameOut.setVisibility(View.VISIBLE);
            usernameEdit.setVisibility(View.INVISIBLE);
            nameOut.setVisibility(View.VISIBLE);
            nameEdit.setVisibility(View.INVISIBLE);
            mailOut.setVisibility(View.VISIBLE);
            mailEdit.setVisibility(View.INVISIBLE);
            moneyOut.setVisibility(View.VISIBLE);
            moneyText.setVisibility(View.VISIBLE);
            returnBtn.setText("Return");
            deleteBtn.setText("Delete account");
        }
    }
}