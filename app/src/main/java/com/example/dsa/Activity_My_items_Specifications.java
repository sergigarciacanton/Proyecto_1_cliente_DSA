package com.example.dsa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Activity_My_items_Specifications extends AppCompatActivity {

    TextView nameOut;
    TextView quantityOut;
    TextView lifeOut;
    TextView attackOut;
    TextView defenseOut;
    TextView priceOut;
    ImageView imageOut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_items_specifications);
        nameOut = findViewById(R.id.itemsSpecificationsNameOut);
        quantityOut = findViewById(R.id.itemsSpecificationsQuantityOut);
        lifeOut = findViewById(R.id.itemsSpecificationsLifeOut);
        attackOut = findViewById(R.id.itemsSpecificationsAttackOut);
        defenseOut = findViewById(R.id.itemsSpecificationsDefenseOut);
        priceOut = findViewById(R.id.itemsSpecificationsPriceOut);
        imageOut = findViewById(R.id.itemsSpecificationsImage);

        String name = getIntent().getStringExtra("name");
        String quantity = getIntent().getStringExtra("quantity");
        String imageURL = getIntent().getStringExtra("image");
        String life = getIntent().getStringExtra("life");
        String attack = getIntent().getStringExtra("attack");
        String defense = getIntent().getStringExtra("defense");
        String price = getIntent().getStringExtra("price");

        nameOut.setText(name);
        quantityOut.setText(quantity);
        lifeOut.setText(life + " %");
        attackOut.setText(attack + " %");
        defenseOut.setText(defense + " %");
        priceOut.setText(price);
        Picasso.with(this.getApplicationContext())
                .load(imageURL)
                .into(imageOut);
    }

    public void returnBtn_Click(View v) {
        finish();
    }
}