package com.example.homestuffapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class OrderDetailsActivity extends AppCompatActivity implements OrderItemsAdapter.OnDeleteButtonClickListener{

    private DBHelper dbHelper;
    private ListView orderItemsListView;
    private OrderItemsAdapter orderItemsAdapter;
    private ArrayList<BuyerModel> orderItemsList;

    private boolean editMode = false;
    int orderId;
    double itemPrice;
    private double totalAmount = 0.0;

    private Button btnEditOrder,btnCancelOrder,btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        btnEditOrder = findViewById(R.id.btnEditOrder);
        btnCancelOrder = findViewById(R.id.btnDetailsOrderCancel);
        btnBack = findViewById(R.id.btnOrderDetailsBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }

        });



        btnEditOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < orderItemsListView.getChildCount(); i++) {
                    View itemView = orderItemsListView.getChildAt(i);
                    Button deleteButton = itemView.findViewById(R.id.deleteItemButton);
                    deleteButton.setVisibility(View.VISIBLE);

                }

                editMode = !editMode; // Toggle edit mode
                orderItemsAdapter.setEditMode(editMode); // Set edit mode for the adapter

                // Change the text of the button based on edit mode
                Button editOrderButton = findViewById(R.id.btnEditOrder);
                editOrderButton.setText(editMode ? "Done Editing" : "Edit Order");

            }
        });


        dbHelper = new DBHelper(this);
        orderItemsListView = findViewById(R.id.orderItemsListView);



        // Fetch order details from the database
        Intent intent = getIntent();
        orderId = intent.getIntExtra("orderId",-1);
        totalAmount = intent.getDoubleExtra("totalPrice", 0.0);
        String shippingMethod = intent.getStringExtra("shippingMethod");
        String oDate = intent.getStringExtra("orderDate");

        // Display order details
        TextView orderIdTextView = findViewById(R.id.orderIdTextView);
        TextView totalAmountTextView = findViewById(R.id.orderTotalTextView);
        TextView dateOrdered = findViewById(R.id.orderDateTextView);
        TextView shippingMethodTextView = findViewById(R.id.shippingMethodTextView);
        Button editOrder = findViewById(R.id.btnEditOrder);

        orderIdTextView.setText("Order ID: " + orderId);
        dateOrdered.setText(oDate);
        shippingMethodTextView.setText("Shipping Method: " + shippingMethod);


        // Fetch order items from the database
        orderItemsList = new ArrayList<>();
        Cursor cursor = dbHelper.getOrderItems(orderId);
        if (cursor.moveToFirst()) {
            do {
                int itemId = cursor.getInt(0);
                String itemName = cursor.getString(1);
                String itemDescription = cursor.getString(2);
                itemPrice = cursor.getDouble(3);
                // Add the item to the list
                orderItemsList.add(new BuyerModel(itemId,itemName, itemDescription, "", itemPrice, null));
            } while (cursor.moveToNext());
        }
        cursor.close();

        // Set up the adapter for the ListView
        orderItemsAdapter = new OrderItemsAdapter(this, orderItemsList);
        orderItemsAdapter.setOnDeleteButtonClickListener(this); // Set listener
        orderItemsListView.setAdapter(orderItemsAdapter);
        calculateTotalAmount();
    }

    @Override
    public void onDeleteButtonClick(BuyerModel item) {
        dbHelper.deleteItemFromOrder(item,orderId);

        Toast.makeText(OrderDetailsActivity.this,item.gettId()+" "+orderId,Toast.LENGTH_SHORT).show();
        // Then, update the list view and notify the adapter
         orderItemsList.remove(item);
         orderItemsAdapter.notifyDataSetChanged();
        calculateTotalAmount();
    }

    private void calculateTotalAmount() {
        totalAmount = 0.0; // Reset total amount
        for (BuyerModel item : orderItemsList) {
            totalAmount += item.gettPrice();
        }
        // Update the TextView displaying the total amount
        TextView totalAmountTextView =  findViewById(R.id.orderTotalTextView);
        totalAmountTextView.setText("Total Amount: $" + totalAmount);
    }
}
