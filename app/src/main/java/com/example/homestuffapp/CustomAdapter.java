package com.example.homestuffapp;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<BuyerModel> {

    public CustomAdapter(Context mContext, ArrayList<BuyerModel> objList) {

        super(mContext, R.layout.buyer_listview,objList);

    }

    @Override
    public View getView(int position, View convetView, ViewGroup parent){

        LayoutInflater mInflater = LayoutInflater.from(getContext());

        View mCustomView =   mInflater.inflate(R.layout.buyer_listview,parent,false);

        BuyerModel mItem = getItem(position);

        ImageView mImageView = mCustomView.findViewById(R.id.cartImg);


        TextView itemId =  mCustomView.findViewById(R.id.tvCartItemId);
        TextView itemName =  mCustomView.findViewById(R.id.tvCartName);
        TextView itemDesc =  mCustomView.findViewById(R.id.tvCartDesc);
        TextView itemListing =  mCustomView.findViewById(R.id.tvCartListing);
        TextView itemPrice =  mCustomView.findViewById(R.id.tvCartPrice);

        mImageView.setImageBitmap(mItem.getImgId());

        itemId.setText("Item Id: "+String.valueOf(mItem.gettId()));
        itemName.setText("Item: "+mItem.gettName());
        itemDesc.setText("Desc: "+mItem.gettDesc());
        itemListing.setText("Listing: "+mItem.gettListing());
        itemPrice.setText("Price: $"+mItem.gettPrice().toString());

        return  mCustomView;

    }
}

