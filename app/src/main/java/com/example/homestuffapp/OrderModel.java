package com.example.homestuffapp;

public class OrderModel {
    private int orderId;
    private String orderDate;
    private double totalPrice;

    public OrderModel(int orderId, String orderDate, double totalPrice) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}

