package com.example.dsa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
        Toast.makeText(getApplicationContext(), "Ooops. Not implemented.", Toast.LENGTH_LONG).show();
    }

    public void returnBtn_Click(View v) {
        finish();
    }
}