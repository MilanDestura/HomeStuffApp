package com.example.homestuffapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    Button cmdBuyer,cmdSeller,cmdCart,cmdOrderDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cmdBuyer = findViewById(R.id.btnBuyer);
        cmdSeller = findViewById(R.id.btnSeller);
        cmdCart = findViewById(R.id.btnCart);
        cmdOrderDetails = findViewById(R.id.btnOrderDetails);

        cmdBuyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent buyerIntent = new Intent(MenuActivity.this, BuyerActivity.class);
                startActivity(buyerIntent);
            }
        });

        cmdCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent CartIntent = new Intent(MenuActivity.this, CartActivity.class);
                startActivity(CartIntent);
            }
        });

        cmdSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sellerIntent = new Intent(MenuActivity.this, SellerActivity.class);
                startActivity(sellerIntent);
            }
        });

        cmdOrderDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent orderDetailsIntent = new Intent(MenuActivity.this, OrderDetailsActivity.class);
                startActivity(orderDetailsIntent);
            }
        });
    }
}