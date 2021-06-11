package com.example.dsa;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.dsa.models.Credentials;

public class Activity_Dashboard extends AppCompatActivity {

    RelativeLayout dashboardNewGame, dashboardMyItems, dashboardStore, dashboardMyProfile, dashboardStatistics, dashboardLogout;
    Credentials c;
    Toast toast;
    Activity_Dashboard main = this;

    @SuppressLint("QueryPermissionsNeeded")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        String username = getIntent().getStringExtra("username");
        String password = getIntent().getStringExtra("password");
        int ID = getIntent().getIntExtra("ID", 0);
        c = new Credentials(username, password);

        dashboardNewGame = this.findViewById(R.id.rellayNewGame);
        dashboardMyItems = this.findViewById(R.id.rellayMyItems);
        dashboardStore = this.findViewById(R.id.rellayStore);
        dashboardMyProfile = this.findViewById(R.id.rellayMyProfile);
        dashboardStatistics = this.findViewById(R.id.rellayStatistics);
        dashboardLogout = this.findViewById(R.id.rellayLogout);

        dashboardNewGame.setOnClickListener(v -> {
            Intent intent = new Intent(Activity_Dashboard.this, Activity_New_Game.class);
            startActivity(intent);
        });

        dashboardMyItems.setOnClickListener(v -> {
            Intent intent = new Intent(Activity_Dashboard.this, Activity_My_Items.class);
            intent.putExtra("ID", ID);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        });

        dashboardStore.setOnClickListener(v -> {
            Uri web = Uri.parse("http://192.168.1.41:8080/");
            Intent intent = new Intent(Intent.ACTION_VIEW, web);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        });

        dashboardMyProfile.setOnClickListener(v -> {
            Intent intent = new Intent(Activity_Dashboard.this, Activity_My_Profile.class);
            intent.putExtra("ID", ID);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivityForResult(intent, 1);
        });

        dashboardStatistics.setOnClickListener(v -> {
            Intent intent = new Intent(Activity_Dashboard.this, Activity_Statistics.class);
            intent.putExtra("ID", ID);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        });

        dashboardLogout.setOnClickListener(v -> {
            finish();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) finish();
    }
}