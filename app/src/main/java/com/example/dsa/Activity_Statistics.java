package com.example.dsa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.dsa.models.Game;

import java.util.List;

public class Activity_Statistics extends AppCompatActivity {

    AdapterStatistics adapter;

    ProgressBar progressBar;
    TextView titleText;

    int id;
    List<Game> gamesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        RecyclerView recyclerView = findViewById(R.id.statisticsRecyclerView);
        progressBar = findViewById(R.id.statisticsProgressBar);
        titleText = findViewById(R.id.statisticsTitleText);

        progressBar.setVisibility(View.VISIBLE);
        titleText.setVisibility(View.INVISIBLE);
        this.id = getIntent().getIntExtra("ID", 0);

        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Activity_Statistics.this);
        recyclerView.setLayoutManager(layoutManager);

        // Set the adapter
        adapter = new AdapterStatistics();
        recyclerView.setAdapter(adapter);

        ControllerGetGames ctrl = new ControllerGetGames();
        ctrl.start(this, id);
    }

    public void advancedStatisticsBtn_Click(View v) {
        Intent intent = new Intent(Activity_Statistics.this, Activity_Statistics_Advanced.class);
        intent.putExtra("gamesNum", String.valueOf(gamesList.size()));
        int won = 0;
        for(Game g : gamesList)
            if(g.getVictory() == 1) won++;
        intent.putExtra("gamesWon", String.valueOf(won));
        int minTime = 0;
        boolean found = false;
        for(Game g : gamesList) {
            if (g.getVictory() == 1 && !found) {
                found = true;
                minTime = g.getDuration();
            }
            else if (g.getVictory() == 1 && g.getDuration() < minTime)
                minTime = g.getDuration();
        }
        intent.putExtra("minTime", String.valueOf(minTime));
        int maxScore = 0;
        for(Game g : gamesList)
            if (g.getScore() > maxScore) maxScore = g.getScore();
        intent.putExtra("maxScore", String.valueOf(maxScore));
        startActivity(intent);
    }

    public void returnBtn_Click(View v) {
        finish();
    }
}