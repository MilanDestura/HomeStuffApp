package com.example.homestuffapp;

import android.graphics.Bitmap;

public class BuyerModel {

    private int tId;

    private String tName;
    private String tDesc;
    private String tListing;
    private Double tPrice;
    private String tSeller;
    private Bitmap imgId;

    private boolean inCart;

    public int gettId() {return tId; }

    public String gettName() {
        return tName;
    }

    public String gettDesc() {
        return tDesc;
    }
    public String gettListing() {
        return tListing;
    }

    public Double gettPrice() {
        return tPrice;
    }
    public String gettSeller() {
        return tSeller;
    }

    public Bitmap getImgId() {
        return imgId;
    }



    public BuyerModel(int t0,String t1, String t2,String t3, Double t4, Bitmap tId){
        this.tId =t0;
        this.tName = t1;
        this.tDesc = t2;
        this.tListing = t3;
        this.tPrice = t4;
        this.imgId=tId;
    }

    public BuyerModel(int t0,String t1, String t2,String t3, Double t4, String t5,Bitmap tId){
        this.tId =t0;
        this.tName = t1;
        this.tDesc = t2;
        this.tListing = t3;
        this.tPrice = t4;
        this.tSeller = t5;
        this.imgId=tId;
    }




}