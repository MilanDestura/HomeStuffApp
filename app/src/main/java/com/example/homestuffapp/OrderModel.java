package com.example.homestuffapp;

public class OrderModel {
    private int orderId;
    private String orderDate;
    private double totalPrice;

    private String shippingMethod;

    public OrderModel(int orderId, String orderDate, double totalPrice, String shippingMethod) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.shippingMethod = shippingMethod;
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
    public String getShippingMethod() {
        return shippingMethod;
    }
}

