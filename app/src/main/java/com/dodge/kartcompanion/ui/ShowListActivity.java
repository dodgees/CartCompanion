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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        ArrayList<Double> itemPrices = new ArrayList<Double>();
        ShoppingItem [] currentItems = mShoppingList.getShoppingList();

        String text1 = "text1";
        String text2 = "text2";

        List<Map<String, String>> listItem = new ArrayList<Map<String, String>>();
        Map<String, String> listItemMap = new HashMap<String, String>();

        double total = 0.00;

        // Put names and prices in separate arrayLists. Calculate total price.
        for (int i = 0; i < mShoppingList.getItemCount(); i++){


            itemNames.add(currentItems[i].getItemName());
            itemPrices.add(currentItems[i].getPrice());

            //put items and prices into a hashMap
            listItemMap.put(text1, currentItems[i].getItemName());
            listItemMap.put(text2, Double.valueOf(currentItems[i].getPrice()).toString());
            listItem.add(listItemMap);

            //Calculates total cost of items in list.
            total += currentItems[i].getPrice();
        }

        costTotalText.setText(String.format("$ %.2f (%.2f)", total, Double.valueOf(mBudget) - total));

        if(total > Double.valueOf(mBudget)){
            costTotalText.setTextColor(Color.RED);
        }
        else{
            costTotalText.setTextColor(Color.BLACK);
        }

        //Used for only name implementation
       // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemNames);

        //Used for Only name implementation
        //setListAdapter(adapter);

        String [] fromMapKey = new String[]{text1, text2};
        int[] toLayoutId = new int[]{android.R.id.text1, android.R.id.text2};

        SimpleAdapter adapter = new SimpleAdapter(this, listItem, android.R.layout.simple_list_item_2, fromMapKey, toLayoutId);
        setListAdapter(adapter);

    }
}
