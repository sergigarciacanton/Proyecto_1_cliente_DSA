package com.example.dsa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.dsa.models.FullObject;

import java.util.List;

public class Activity_My_Items extends AppCompatActivity {

    AdapterItems adapter;

    ProgressBar progressBar;
    TextView titleText;

    int id;
    List<FullObject> objectsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_items);
        RecyclerView recyclerView = findViewById(R.id.itemsRecyclerView);
        progressBar = findViewById(R.id.itemsProgressBar);
        titleText = findViewById(R.id.itemsTitleText);

        progressBar.setVisibility(View.VISIBLE);
        titleText.setVisibility(View.INVISIBLE);
        this.id = getIntent().getIntExtra("ID", 0);

        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Activity_My_Items.this);
        recyclerView.setLayoutManager(layoutManager);

        // Set the adapter
        adapter = new AdapterItems();
        recyclerView.setAdapter(adapter);

        ControllerGetObjects ctrl = new ControllerGetObjects();
        ctrl.start(this, id);
    }

    public void returnBtn_Click(View v) {
        finish();
    }
}