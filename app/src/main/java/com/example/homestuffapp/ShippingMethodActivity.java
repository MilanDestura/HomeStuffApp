package com.example.homestuffapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ShippingMethodActivity extends AppCompatActivity {
    private DBHelper dbHelper;

    private String shippingMethod;
    private String orderStatus="Placed";

    private String buyer="Buyer";

    RadioButton rdbDelivery;
    RadioButton rdbPickup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping_method);

        Intent cartIntent = this.getIntent();
        String totalAmount = cartIntent.getStringExtra("tPrice");
        TextView tvTotalAmount = findViewById(R.id.tvShippingTotal);

        rdbDelivery= findViewById(R.id.rdbDelivery);
        rdbPickup = findViewById(R.id.rdbPickup);
        tvTotalAmount.setText("$ "+totalAmount);
        dbHelper = new DBHelper(this);
    }

    public void cancelOrder(View view){
        finish();
    }

    public void placeOrder(View view) {

        if(rdbDelivery.isChecked()){
            shippingMethod=rdbDelivery.getText().toString();
        }
        if(rdbPickup.isChecked()){
            shippingMethod=rdbPickup.getText().toString();
        }

        // Get the current date and time
        String orderDate = getCurrentDateTime();

        // Get the total price from the intent
        Intent cartIntent = getIntent();
        String totalAmount = cartIntent.getStringExtra("tPrice");
        double totalPrice = Double.parseDouble(totalAmount);

        // Get the cart items from CartManager
        CartManager cartManager = CartManager.getInstance();
        ArrayList<BuyerModel> cartItems = cartManager.getCartItems();

        // Place the order
        boolean success = dbHelper.placeOrder(orderDate, totalPrice,shippingMethod,orderStatus,buyer, cartItems);
        if (success) {
            // Order placed successfully, clear the cart or perform any other action
            cartManager.clearCart();
            // Redirect to a confirmation page or display a success message
           // Intent intent = new Intent(this, OrderConfirmationActivity.class);
            //startActivity(intent);
            setResult(RESULT_OK);
            Toast.makeText(ShippingMethodActivity.this,"Order placed.",Toast.LENGTH_SHORT).show();
            finish();

        } else {
            // Handle order placement failure
            Toast.makeText(this, "Failed to place order", Toast.LENGTH_SHORT).show();
        }
    }

    private String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
    }

}