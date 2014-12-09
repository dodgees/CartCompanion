package com.dodge.kartcompanion.model;

import java.io.Serializable;

/**
 * Created by dodge_000 on 12/2/2014.
 */
public class ShoppingList implements Serializable{
    private ShoppingItem[] mShoppingList;
    private String mListName;
    private String mBudget;
    private int mItemCount;

    public int getItemCount() {
        return mItemCount;
    }

    public void setItemCount(int itemCount) {
        mItemCount = itemCount;
    }


    public ShoppingList(String name){

        mListName = name;
        mShoppingList = new ShoppingItem[200];
    }

    public ShoppingList(String name, String budget){

        mListName = name;
        mShoppingList = new ShoppingItem[200];
        mBudget = budget;
    }

    public ShoppingItem[] getShoppingList() {
        return mShoppingList;
    }

    public void setShoppingList(ShoppingItem[] shoppingList) {
        mShoppingList = shoppingList;
    }

    public String getListName() {
        return mListName;
    }

    public void setListName(String listName) {
        mListName = listName;
    }

    public String getBudget() {
        return mBudget;
    }

    public void setBudget(String budget) {
        this.mBudget = budget;
    }

    public void addItem(ShoppingItem item){

        mShoppingList[mItemCount] = item;
        mItemCount++;
    }
}
