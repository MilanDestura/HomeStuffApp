package com.example.homestuffapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderItemsAdapter extends ArrayAdapter<BuyerModel> {

    public OrderItemsAdapter(Context context, ArrayList<BuyerModel> orderItems) {
        super(context, 0, orderItems);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BuyerModel orderItem = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.order_item_list_item, parent, false);
        }

        TextView itemNameTextView = convertView.findViewById(R.id.itemNameTextView);
        TextView itemPriceTextView = convertView.findViewById(R.id.itemPriceTextView);

        itemNameTextView.setText(orderItem.gettName());
        itemPriceTextView.setText("$" + orderItem.gettPrice());

        return convertView;
    }
}
