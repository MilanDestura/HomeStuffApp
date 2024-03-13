package com.example.homestuffapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.homestuffapp.databinding.ActivityBuyerBinding;
import com.example.homestuffapp.databinding.ActivityCartBinding;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {


    private ListView cartListView;
    private CartAdapter cartAdapter;
    private ArrayList<BuyerModel> cartItems;
    private CartManager cartManager;
    public Double total=0.0;

    TextView tvTotal;
    Button btnBuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


            cartManager = CartManager.getInstance();
            cartItems = cartManager.getCartItems();


            cartListView = findViewById(R.id.cartListView);
            cartAdapter = new CartAdapter(this, cartItems);
            cartListView.setAdapter(cartAdapter);

            for (BuyerModel item : cartItems) {
                total += item.gettPrice();
            }


            tvTotal = findViewById(R.id.tvTotal);
            btnBuy = findViewById(R.id.btnBuyNow);

            tvTotal.setText("Total Amount: $"+total.toString());}

    public void clickBuyNow(View mView){
        Intent shippingIntent=new Intent(CartActivity.this,ShippingMethodActivity.class);
        shippingIntent.putExtra("tPrice",total.toString());
        startActivityForResult(shippingIntent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Finish CartActivity
            finish();
        }
    }

}