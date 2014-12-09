package com.dodge.kartcompanion.model;

/**
 * Created by dodge_000 on 12/2/2014.
 */
public class ShoppingItem {
    
    private String mItemName;
    private double mPrice;

    
    public ShoppingItem(String name, double price){
        mItemName = name;
        mPrice = price;

    }

    public String getItemName() {
        return mItemName;
    }

    public void setItemName(String itemName) {
        mItemName = itemName;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double price) {
        mPrice = price;
    }

}
