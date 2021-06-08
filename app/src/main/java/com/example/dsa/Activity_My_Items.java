package com.example.dsa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.dsa.models.Credentials;
import com.example.dsa.models.FullObject;
import com.example.dsa.models.User;

import java.util.List;

public class Activity_My_Items extends AppCompatActivity {

    private RecyclerView recyclerView;
    ItemsAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    ProgressBar progressBar;

    int id;
    List<FullObject> objectsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_items);
        recyclerView = findViewById(R.id.my_recycler_view);
        progressBar = findViewById(R.id.itemsProgressBar);

        progressBar.setVisibility(View.VISIBLE);
        this.id = getIntent().getIntExtra("ID", 0);

        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(Activity_My_Items.this);
        recyclerView.setLayoutManager(layoutManager);

        // Set the adapter
        adapter = new ItemsAdapter();
        recyclerView.setAdapter(adapter);

        ControllerGetObjects ctrl = new ControllerGetObjects();
        ctrl.start(this, id);
    }

    public void returnBtn_Click(View v) {
        finish();
    }
}