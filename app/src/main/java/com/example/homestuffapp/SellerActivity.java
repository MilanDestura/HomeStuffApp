package com.example.homestuffapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

public class SellerActivity extends AppCompatActivity {

    private EditText itemNameEditText;
    private EditText itemDescriptionEditText;
    private EditText itemPriceEditText;
    private ImageView itemImageView;
    private Uri imageUri;

    private DBHelper dbHelper;
    private double itemPrice= 0.0;
    private String itemListingType;
    String sellerName="Seller.Id";

    Bitmap mbitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller);

        itemNameEditText = findViewById(R.id.itemNameEditText);
        itemDescriptionEditText = findViewById(R.id.itemDescriptionEditText);
        itemPriceEditText = findViewById(R.id.itemPriceEditText);
        itemImageView = findViewById(R.id.itemImageView);
        mbitmap = BitmapFactory.decodeResource(getResources(), R.drawable.homestufflogo);
        dbHelper = new DBHelper(this);


        Intent i = getIntent();
        sellerName = i.getStringExtra("userName");

        Button selectImageButton = findViewById(R.id.selectImageButton);
        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImageChooser();
            }
        });

        Button addItemButton = findViewById(R.id.addItemButton);
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItemToDatabase();
                Toast.makeText(SellerActivity.this, "Item posted.", Toast.LENGTH_SHORT).show();

                finish();
            }
        });
    }

    public void onClickListingType(View view) {
        RadioButton rdForSale = findViewById(R.id.rdbForSale);
        RadioButton rdForSharing = findViewById(R.id.rdbForSharing);

        if (rdForSale.isChecked()) {
            itemListingType = "For Sale";
            itemPrice=Double.parseDouble(itemPriceEditText.getText().toString().trim());
        }
        if (rdForSharing.isChecked()) {
            itemListingType = "For Sharing";
            itemPrice = 0.0;
        }

    }

    private void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            try {
                mbitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                itemImageView.setImageBitmap(mbitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void addItemToDatabase() {
        String itemName = itemNameEditText.getText().toString().trim();
        String itemDescription = itemDescriptionEditText.getText().toString().trim();
        dbHelper.insertItemToDB(itemName, itemDescription,itemListingType, itemPrice,sellerName,mbitmap);
        Toast.makeText(this, "Item added successfully", Toast.LENGTH_SHORT).show();
    }
}