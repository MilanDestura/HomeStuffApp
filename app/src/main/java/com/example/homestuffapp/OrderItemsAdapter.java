package com.example.homestuffapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class OrderItemsAdapter extends ArrayAdapter<BuyerModel> {

    private Context mContext;
    private ArrayList<BuyerModel> mOrderItems;
    private boolean mEditMode = false;

    private OnDeleteButtonClickListener onDeleteButtonClickListener;


    public interface OnDeleteButtonClickListener {
        void onDeleteButtonClick(BuyerModel item);
    }
    public void setOnDeleteButtonClickListener(OnDeleteButtonClickListener listener) {
        onDeleteButtonClickListener = listener;
    }
    public OrderItemsAdapter(Context context, ArrayList<BuyerModel> orderItems) {
        super(context, 0, orderItems);
        mContext = context;
        mOrderItems = orderItems;
    }

    public void setEditMode(boolean editMode) {
        mEditMode = editMode;
        notifyDataSetChanged(); // Refresh the adapter to reflect the change in edit mode
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(mContext).inflate(R.layout.order_item_list_item, parent, false);
        }

        BuyerModel orderItem = mOrderItems.get(position);

        TextView itemIdTextView = listItem.findViewById(R.id.itemIdTextView);
        TextView itemNameTextView = listItem.findViewById(R.id.itemNameTextView);
        TextView itemPriceTextView = listItem.findViewById(R.id.itemPriceTextView);
        Button deleteItemButton = listItem.findViewById(R.id.deleteItemButton);

        //itemIdTextView.setText(String.valueOf(orderItem.gettId()));
        itemNameTextView.setText(orderItem.gettName());
        itemPriceTextView.setText("$" + orderItem.gettPrice());

        // Toggle visibility of delete button based on edit mode
        deleteItemButton.setVisibility(mEditMode ? View.VISIBLE : View.GONE);

        // Set click listener for delete button
        deleteItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onDeleteButtonClickListener != null) {
                    onDeleteButtonClickListener.onDeleteButtonClick(orderItem);
                }
            }
        });

        return listItem;
    }
}

