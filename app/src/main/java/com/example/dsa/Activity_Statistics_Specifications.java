package com.example.dsa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Activity_Statistics_Specifications extends AppCompatActivity {

    TextView idOut;
    TextView durationOut;
    TextView victoryOut;
    TextView scoreOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__statistics__specifications);
        idOut = findViewById(R.id.statisticsSpecificationsIdOut);
        durationOut = findViewById(R.id.statisticsSpecificationsDurationOut);
        victoryOut = findViewById(R.id.statisticsSpecificationsVictoryOut);
        scoreOut = findViewById(R.id.statisticsSpecificationsScoreOut);

        String id = getIntent().getStringExtra("id");
        String duration = getIntent().getStringExtra("duration");
        String victory = getIntent().getStringExtra("victory");
        String score = getIntent().getStringExtra("score");

        idOut.setText(id);
        durationOut.setText(duration + " seconds");
        if(victory == "0") victoryOut.setText("You lost");
        else victoryOut.setText("You won");
        scoreOut.setText(score + " points");
    }

    public void returnBtn_Click(View v) {
        finish();
    }
}