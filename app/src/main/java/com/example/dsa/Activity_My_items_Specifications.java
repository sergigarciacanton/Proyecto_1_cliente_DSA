package com.example.dsa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Activity_My_items_Specifications extends AppCompatActivity {

    private String name;
    private String imageURL;
    private String quantity;
    private String attack;
    private String defense;
    private String life;
    private String price;

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

        this.name = getIntent().getStringExtra("name");
        this.quantity = getIntent().getStringExtra("quantity");
        this.imageURL = getIntent().getStringExtra("image");
        this.life = getIntent().getStringExtra("life");
        this.attack = getIntent().getStringExtra("attack");
        this.defense = getIntent().getStringExtra("defense");
        this.price = getIntent().getStringExtra("price");

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