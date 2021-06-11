package com.example.dsa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Activity_Statistics_Advanced extends AppCompatActivity {

    TextView gamesOut;
    TextView victoryOut;
    TextView minTimeOut;
    TextView maxScoreOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics_advanced);
        gamesOut = findViewById(R.id.statisticsAdvancedPlayedOut);
        victoryOut = findViewById(R.id.statisticsAdvancedWonOut);
        minTimeOut = findViewById(R.id.statisticsAdvancedTimeOut);
        maxScoreOut = findViewById(R.id.statisticsAdvancedScoreOut);

        String gamesNum = getIntent().getStringExtra("gamesNum");
        String gamesWon = getIntent().getStringExtra("gamesWon");
        String minTime = getIntent().getStringExtra("minTime");
        String maxScore = getIntent().getStringExtra("maxScore");

        gamesOut.setText(gamesNum);
        victoryOut.setText(gamesWon);
        minTimeOut.setText(minTime + " seconds");
        maxScoreOut.setText(maxScore + " points");
    }

    public void returnBtn_Click(View v) {
        finish();
    }
}