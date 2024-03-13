package com.example.homestuffapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderListAdapter extends ArrayAdapter<OrderModel> {

    private Context mContext;
    private ArrayList<OrderModel> mOrderList;

    public OrderListAdapter(Context context, ArrayList<OrderModel> orderList) {
        super(context, 0, orderList);
        mContext = context;
        mOrderList = orderList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(mContext).inflate(R.layout.order_list_item, parent, false);
        }

        OrderModel currentOrder = mOrderList.get(position);

        TextView orderIdTextView = listItem.findViewById(R.id.orderIdTextView);
        TextView orderDateTextView = listItem.findViewById(R.id.orderDateTextView);
        TextView orderTotalTextView = listItem.findViewById(R.id.orderTotalTextView);

        orderIdTextView.setText("Order ID: " + currentOrder.getOrderId());
        orderDateTextView.setText("Order Date: " + currentOrder.getOrderDate());
        orderTotalTextView.setText("Total Price: $" + currentOrder.getTotalPrice());

        return listItem;
    }
}
