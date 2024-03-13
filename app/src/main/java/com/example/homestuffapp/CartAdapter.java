package com.example.homestuffapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import java.util.ArrayList;

public class CartAdapter extends ArrayAdapter<BuyerModel> {


    private Context context;
    private ArrayList<BuyerModel> cartItems;

    public CartAdapter(Context context, ArrayList<BuyerModel> cartItems) {
        super(context, 0, cartItems);
        this.context = context;
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(context).inflate(R.layout.cart_listview, parent, false);
        }

        BuyerModel currentItem = cartItems.get(position);


        TextView itemId = listItem.findViewById(R.id.tvCartId);
        TextView itemName = listItem.findViewById(R.id.tvCartName);
        TextView itemPrice = listItem.findViewById(R.id.tvCartPrice);
        ImageView itemImage = listItem.findViewById(R.id.cartImg);
        Button deleteCartItem = listItem.findViewById(R.id.btnDeleteCartItem);

        itemId.setText(Integer.toString(currentItem.gettId()));
        itemName.setText(currentItem.gettName());
        itemPrice.setText("$"+String.valueOf(currentItem.gettPrice()));
        itemImage.setImageBitmap(currentItem.getImgId());

        deleteCartItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartItems.remove(position);
                notifyDataSetChanged();
                Double mTotal=0.0;
                for (BuyerModel item : cartItems) {
                    mTotal += item.gettPrice();
                }
                ((CartActivity) context).tvTotal.setText("Total Amount: $"+mTotal.toString());

                if (cartItems.isEmpty()){
                    ((CartActivity) context).tvTotal.setText("");
                    ((CartActivity) context).btnBuy.setEnabled(false);

                }

            }
        });

        return listItem;
    }
}
