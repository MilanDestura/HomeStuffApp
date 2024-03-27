package com.example.homestuffapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    long userId;
    String userName;

    Button cmdBuyer,cmdSeller,cmdCart,cmdOrderDetails,cmdOrderStatus, cmdOrderHistory, cmdExit, cmdProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cmdBuyer = findViewById(R.id.btnBuyer);
        cmdSeller = findViewById(R.id.btnSeller);
        cmdCart = findViewById(R.id.btnCart);
        cmdProfile = findViewById(R.id.btnProfile);
        cmdOrderDetails = findViewById(R.id.btnOrderDetails);
        cmdExit = findViewById(R.id.btnExit);
        cmdOrderStatus = findViewById(R.id.btnOrderStatus);
        cmdOrderHistory = findViewById(R.id.btnOrderHistory);
        Intent i = getIntent();
        userId = i.getLongExtra("userId", -1);
        userName = i.getStringExtra("userName");



        //Log.e("teste", "usuario carregado " + userId);

        cmdBuyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent buyerIntent = new Intent(MenuActivity.this, BuyerActivity.class);
                startActivity(buyerIntent);
            }
        });

        cmdProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ProfileIntent = new Intent(MenuActivity.this, ProfileActivity.class);
                ProfileIntent.putExtra("id", userId);
                //Log.e("teste", "user id menu " + userId);
                startActivity(ProfileIntent);
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
                sellerIntent.putExtra("userName",userName);
                startActivity(sellerIntent);
            }
        });

        cmdOrderDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent orderDetailsIntent = new Intent(MenuActivity.this, OrderListActivity.class);
                startActivity(orderDetailsIntent);
            }
        });

        cmdOrderStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent orderStatusIntent = new Intent(MenuActivity.this, OrderStatusActivity.class);
                startActivity(orderStatusIntent);
            }
        });

        cmdOrderHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent orderHistoryIntent = new Intent(MenuActivity.this, OrderHistoryActivity.class);
                startActivity(orderHistoryIntent);
            }
        });
        cmdExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}