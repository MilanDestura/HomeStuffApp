package com.example.homestuffapp;

import java.util.ArrayList;

public class CartManager {
    private static CartManager instance;
    private ArrayList<BuyerModel> cartItems;

    private CartManager() {
        cartItems = new ArrayList<>();
    }

    public static CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public void addItemToCart(BuyerModel item) {
        cartItems.add(item);
    }

    public void removeItemFromCart(BuyerModel item) {
        cartItems.remove(item);
    }


    public void clearCart() {
        cartItems.clear();
    }
    public ArrayList<BuyerModel> getCartItems() {
        return cartItems;
    }
}
