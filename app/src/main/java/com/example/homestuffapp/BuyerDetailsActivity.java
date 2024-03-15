package com.example.homestuffapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.homestuffapp.databinding.ActivityBuyerDetailsBinding;

public class BuyerDetailsActivity extends AppCompatActivity {

    ActivityBuyerDetailsBinding binding;
    BuyerModel itemClicked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBuyerDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();
        if(intent!=null ){
            int intId = intent.getIntExtra("extId",0);
            String strName = intent.getStringExtra("extName");
            String strDesc = intent.getStringExtra("extDesc");
            String strListing = intent.getStringExtra("extListing");
            String strSeller = intent.getStringExtra("extSeller");
            Double dblPrice = intent.getDoubleExtra("extPrice",0.0);

            byte[] byteArray = intent.getByteArrayExtra("img");
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

            binding.detailImage.setImageBitmap(bitmap);
            binding.tvItemName.setText(strName);
            binding.tvItemDesc.setText(strDesc);
            binding.tvItemListing.setText(strListing);
            binding.tvItemSeller.setText(strSeller);
            binding.tvItemPrice.setText("$"+dblPrice.toString());

            itemClicked = new BuyerModel(intId,strName, strDesc, strListing,dblPrice, bitmap);

            binding.btnAddToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CartManager myCartManager = CartManager.getInstance();
                    myCartManager.addItemToCart(itemClicked);
                    Toast.makeText(BuyerDetailsActivity.this, "Item added to cart", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });

            binding.btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
    }
    }
}