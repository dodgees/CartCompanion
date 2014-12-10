package com.dodge.kartcompanion.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.ListView;
import android.app.ListActivity;

import com.dodge.kartcompanion.R;
import com.dodge.kartcompanion.model.ShoppingItem;
import com.dodge.kartcompanion.model.ShoppingList;

import java.util.ArrayList;

public class ShowListActivity extends ListActivity {

    private ShoppingList mShoppingList;
    private TextView titleText, budgetText, costTotalText;
    private Button mAddItemButton;
    private String mBudget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        mBudget = intent.getStringExtra("budget");
        //double budget = Double.valueOf(b).doubleValue();



        mShoppingList = new ShoppingList(name, mBudget);




        titleText = (TextView) findViewById(R.id.listNameTextView);
        titleText.setText(name);
        budgetText = (TextView) findViewById(R.id.budgetTotalTextView);
        budgetText.setText("$ " + mBudget);
        costTotalText = (TextView) findViewById(R.id.costTotalTextView);
        costTotalText.setText("$ 0.00");

        mAddItemButton = (Button) findViewById(R.id.addItemButton);


        ArrayList<String> itemNames = new ArrayList<String>();
        ShoppingItem [] currentItems = mShoppingList.getShoppingList();


       // itemNames[0] = "Hello"; This didn't solve the NullPointerException
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemNames);

        //ListView listView = (ListView) findViewById(android.);
        //listView.
        setListAdapter(adapter);



        mAddItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create new item, maybe recall on create?
                addItem();



              //  for (int i = 0; i <= mShoppingList.getItemCount(); i++){
              //      itemNames[0] = currentItems[0].getItemName();
              //  }



            }
        });
    }


    public void addItem(){
        Intent addItemIntent = new Intent(this, AddItemActivity.class);
        startActivityForResult(addItemIntent, 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        String itemName = data.getStringExtra("itemName");
        double itemPrice = data.getDoubleExtra("itemPrice", 0.00);

        ShoppingItem item = new ShoppingItem(itemName, itemPrice);
        mShoppingList.addItem(item);

    }

    @Override
    protected void onResume() {
        super.onResume();

        ArrayList<String> itemNames = new ArrayList<String>();
        ShoppingItem [] currentItems = mShoppingList.getShoppingList();

        double total = 0.00;

        for (int i = 0; i < mShoppingList.getItemCount(); i++){
            itemNames.add(currentItems[i].getItemName());
            total += currentItems[i].getPrice();
        }

        costTotalText.setText(String.format("$ %.2f (%.2f)", total, Double.valueOf(mBudget) - total));

        if(total > Double.valueOf(mBudget)){
            costTotalText.setTextColor(Color.RED);
        }
        else{
            costTotalText.setTextColor(Color.BLACK);
        }

        // itemNames[0] = "Hello"; This didn't solve the NullPointerException
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemNames);

        //ListView listView = (ListView) findViewById(android.);
        //listView.
        setListAdapter(adapter);
    }
}
