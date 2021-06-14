package com.example.dsa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dsa.models.FullObject;

import java.util.ArrayList;
import java.util.List;

public class Activity_New_Game extends AppCompatActivity {

    AdapterNewGame adapter;

    ProgressBar progressBar;
    TextView titleText;

    int id;
    List<FullObject> objectsList;
    List<Integer> objectsIdList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
        RecyclerView recyclerView = findViewById(R.id.newGameRecyclerView);
        progressBar = findViewById(R.id.newGameProgressBar);
        titleText = findViewById(R.id.newGameTitleText);

        progressBar.setVisibility(View.VISIBLE);
        titleText.setVisibility(View.INVISIBLE);
        this.id = getIntent().getIntExtra("ID", 0);
        objectsIdList = new ArrayList<>();

        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Activity_New_Game.this);
        recyclerView.setLayoutManager(layoutManager);

        // Set the adapter
        adapter = new AdapterNewGame(this);
        recyclerView.setAdapter(adapter);

        ControllerGetObjects ctrl = new ControllerGetObjects();
        ctrl.start(this, id);
    }

    public void returnBtn_Click(View v) {
        finish();
    }

    public void playBtn_Click(View v) {
        for(int idObject : objectsIdList) {
            ControllerUseObject ctrl = new ControllerUseObject();
            ctrl.start(this, idObject, id);
        }
    }
}