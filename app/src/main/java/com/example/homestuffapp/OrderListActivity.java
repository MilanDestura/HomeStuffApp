package com.example.homestuffapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class OrderListActivity extends AppCompatActivity {

    private DBHelper dbHelper;
    private ListView orderListView;
    private OrderListAdapter orderListAdapter;
    private ArrayList<OrderModel> orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        dbHelper = new DBHelper(this);
        orderListView = findViewById(R.id.orderListView);

        // Fetch orders from the database
        orderList = new ArrayList<>();
        Cursor cursor = dbHelper.getAllOrdersCursor();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int orderId = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String orderDate = cursor.getString(cursor.getColumnIndex("order_date"));
                @SuppressLint("Range") double totalPrice = cursor.getDouble(cursor.getColumnIndex("total_price"));
                @SuppressLint("Range") String shippingMethod = cursor.getString(cursor.getColumnIndex("shipping_method"));

                // Create an OrderModel object and add it to the list
                OrderModel order = new OrderModel(orderId, orderDate, totalPrice,shippingMethod);
                orderList.add(order);
            } while (cursor.moveToNext());

            cursor.close();
        } else {
            // Handle case where there are no orders
            Toast.makeText(this, "No orders found.", Toast.LENGTH_SHORT).show();
        }

        // Set up the adapter for the ListView
        orderListAdapter = new OrderListAdapter(this, orderList);
        orderListView.setAdapter(orderListAdapter);


        // Set item click listener
        orderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected order
                OrderModel selectedOrder = orderList.get(position);

                // Start OrderDetailsActivity with selected order details
                Intent intent = new Intent(OrderListActivity.this, OrderDetailsActivity.class);
                intent.putExtra("orderId", selectedOrder.getOrderId());
                intent.putExtra("orderDate", selectedOrder.getOrderDate());
                intent.putExtra("totalPrice", selectedOrder.getTotalPrice());
                intent.putExtra("shippingMethod", selectedOrder.getShippingMethod());
                startActivity(intent);
            }
        });
    }
}
