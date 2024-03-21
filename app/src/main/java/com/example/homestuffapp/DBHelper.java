package com.example.homestuffapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {


    byte[] imgInBytes;
    public DBHelper(Context context){
        super(context,"homestuffapp.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table tblItem(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT , " +
                "description TEXT, " +
                "listing_type TEXT,"+
                "price DECIMAL," +
                "seller TEXT," +
                "image BLOB)");

        DB.execSQL("create Table tblOrder(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "order_date TEXT, " +
                "total_price DECIMAL, " +
                "shipping_method TEXT)");

        DB.execSQL("create Table tblOrderItem(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "order_id INTEGER, " +
                "item_id INTEGER, " +
                "quantity INTEGER, " +
                "FOREIGN KEY(order_id) REFERENCES tblOrder(id), " +
                "FOREIGN KEY(item_id) REFERENCES tblItem(id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("Drop Table IF exists tblItem");

    }

    public Cursor getData(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from tblItem ",null);
        return cursor;
    }


    public Cursor getOrderItems(int orderId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT tblItem.name, tblItem.description, tblItem.price " +
                "FROM tblOrderItem " +
                "JOIN tblItem ON tblOrderItem.item_id = tblItem.id " +
                "WHERE tblOrderItem.order_id = ?";
        String[] selectionArgs = {String.valueOf(orderId)};
        return db.rawQuery(query, selectionArgs);
    }

    public Cursor getAllOrdersCursor() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM tblOrder", null);
    }



    public boolean insertItemToDB(String name, String desc,String lType,Double price,String sName, Bitmap img){
        SQLiteDatabase DB = this.getWritableDatabase();

        ByteArrayOutputStream objByteOutputStream = new ByteArrayOutputStream();
        img.compress(Bitmap.CompressFormat.JPEG,50,objByteOutputStream);
        imgInBytes = objByteOutputStream.toByteArray();

        ContentValues contentValues = new ContentValues();


        contentValues.put("name",name);
        contentValues.put("description",desc);
        contentValues.put("listing_type",lType);
        contentValues.put("price",price);
        contentValues.put("seller",sName);
        contentValues.put("image",imgInBytes);
        long result = DB.insert("tblItem",null,contentValues);
        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean placeOrder(String orderDate, double totalPrice,String shippingMethod, ArrayList<BuyerModel> items) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues orderValues = new ContentValues();
        orderValues.put("order_date", orderDate);
        orderValues.put("total_price", totalPrice);
        orderValues.put("shipping_method",shippingMethod);
        long orderId = DB.insert("tblOrder", null, orderValues);

        if (orderId == -1) {
            return false; // Failed to insert order
        }

        // Insert order items
        for (BuyerModel item : items) {
            ContentValues orderItemValues = new ContentValues();
            orderItemValues.put("order_id", orderId);
            orderItemValues.put("item_id", item.gettId());
            orderItemValues.put("quantity", 1);
            long result = DB.insert("tblOrderItem", null, orderItemValues);
            if (result == -1) {
                return false; // Failed to insert order item
            }
        }
        return true;
    }


}

