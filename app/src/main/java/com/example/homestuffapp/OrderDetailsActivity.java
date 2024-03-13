package com.example.homestuffapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class OrderDetailsActivity extends AppCompatActivity {

    private DBHelper dbHelper;
    private ListView orderItemsListView;
    private OrderItemsAdapter orderItemsAdapter;
    private ArrayList<BuyerModel> orderItemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        dbHelper = new DBHelper(this);
        orderItemsListView = findViewById(R.id.orderItemsListView);

        // Fetch order details from the database
        Intent intent = getIntent();
        int orderId = intent.getIntExtra("orderId",-1);
        double totalAmount = intent.getDoubleExtra("totalAmount", 0.0);
        String shippingMethod = intent.getStringExtra("shippingMethod");

        // Display order details
        TextView orderIdTextView = findViewById(R.id.orderIdTextView);
        TextView totalAmountTextView = findViewById(R.id.orderTotalTextView);
        TextView shippingMethodTextView = findViewById(R.id.shippingMethodTextView);

        orderIdTextView.setText("Order ID: " + orderId);
        totalAmountTextView.setText("Total Amount: $" + totalAmount);
        shippingMethodTextView.setText("Shipping Method: " + shippingMethod);

        // Fetch order items from the database
        orderItemsList = new ArrayList<>();
        Cursor cursor = dbHelper.getOrderItems(orderId);
        if (cursor.moveToFirst()) {
            do {
                String itemName = cursor.getString(0);
                String itemDescription = cursor.getString(1);
                double itemPrice = cursor.getDouble(2);
                // Add the item to the list
                orderItemsList.add(new BuyerModel(itemName, itemDescription, "", itemPrice, null));

            } while (cursor.moveToNext());
        }
        cursor.close();

        // Set up the adapter for the ListView
        orderItemsAdapter = new OrderItemsAdapter(this, orderItemsList);
        orderItemsListView.setAdapter(orderItemsAdapter);
    }

}
