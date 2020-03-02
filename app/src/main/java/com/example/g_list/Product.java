package com.example.g_list;

import android.graphics.Bitmap;

public class Product {

    private int productID;
    private String productName;
    private Bitmap productPhoto;

    public int getProductID() {
        return productID;
    }

    public Product(){
        productID = -1;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Bitmap getProductPhoto() {
        return productPhoto;
    }

    public void setProductPhoto(Bitmap productPhoto) {
        this.productPhoto = productPhoto;
    }
}

