package com.example.homestuffapp;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Toast;


import com.example.homestuffapp.databinding.ActivityBuyerBinding;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class BuyerActivity extends AppCompatActivity {

    ActivityBuyerBinding binding;


    DBHelper DB;
    ArrayList<Integer> arrID = new ArrayList<>();
    ArrayList<String> arrName = new ArrayList<>();
    ArrayList<String> arrDesc= new ArrayList<>();
    ArrayList<String> arrListing = new ArrayList<>();

    ArrayList<Double> arrPrice= new ArrayList<>();
    ArrayList<Bitmap> arrImg = new ArrayList<>();

    ArrayList<BuyerModel> dataArrayList = new ArrayList<>();
    ArrayList<BuyerModel> filteredDataArrayList = new ArrayList<>();

    boolean isFiltered = false;
    Bitmap myImg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBuyerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DB = new DBHelper(this);

        myImg= BitmapFactory.decodeResource(BuyerActivity.this.getResources(), R.drawable.visionpro);

        getAllItems();


        ArrayAdapter<BuyerModel> adapter = new CustomAdapter(BuyerActivity.this,dataArrayList);
        binding.lvView.setAdapter(adapter);
        binding.lvView.setClickable(true);

        binding.lvView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            BuyerModel selectedItem;
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if(isFiltered){
                    selectedItem = filteredDataArrayList.get(i);
                }
                else{
                    selectedItem = dataArrayList.get(i);
                }
                Intent intent=new Intent(BuyerActivity.this, BuyerDetailsActivity.class);
                intent.putExtra("extId",selectedItem.gettId());

                intent.putExtra("extName",selectedItem.gettName());
                intent.putExtra("extDesc",selectedItem.gettDesc());
                intent.putExtra("extListing",selectedItem.gettListing());
                intent.putExtra("extPrice",selectedItem.gettPrice());
                ByteArrayOutputStream stream = new ByteArrayOutputStream();

                selectedItem.getImgId().compress(Bitmap.CompressFormat.JPEG, 50, stream);
                byte[] byteArray = stream.toByteArray();

                intent.putExtra("img", byteArray);

                startActivity(intent);

            }
        });

        binding.searchItem.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String query1) {
                isFiltered = true;
                String userInput = query1.toLowerCase();
                filteredDataArrayList.clear();

                for (BuyerModel item : dataArrayList) {
                    if (item.gettName().toLowerCase().contains(userInput) || item.gettDesc().toLowerCase().contains(userInput)) {
                        filteredDataArrayList.add(item);
                    }
                }

                CustomAdapter customAdapter = new CustomAdapter(BuyerActivity.this, filteredDataArrayList);
                binding.lvView.setAdapter(customAdapter);
                customAdapter.notifyDataSetChanged();

                return false;
            }
        });


    }

    public void getAllItems(){

        Cursor res = DB.getData();
        if(res.getCount()==0){
            Toast.makeText(BuyerActivity.this,"No Data Exist", Toast.LENGTH_SHORT).show();
        }
        else{
            while(res.moveToNext()){
                arrName.add(res.getString(1));
                arrDesc.add(res.getString(2));
                arrListing.add(res.getString(3));
                arrPrice.add(res.getDouble(4));
                byte[] imagebytes = res.getBlob(5);
                Bitmap bitmap = BitmapFactory.decodeByteArray(imagebytes, 0, imagebytes.length);
                arrImg.add(bitmap);
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            for (int i=0;i< arrName.stream().count();i++){
                BuyerModel listData = new BuyerModel(i,arrName.get(i),arrDesc.get(i),arrListing.get(i),arrPrice.get(i),arrImg.get(i));
                dataArrayList.add(listData);
            }
        }
    }

}