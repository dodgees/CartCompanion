package com.dodge.kartcompanion.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ListView;
import android.app.ListActivity;

import com.dodge.kartcompanion.R;
import com.dodge.kartcompanion.model.ShoppingItem;
import com.dodge.kartcompanion.model.ShoppingList;

public class ShowListActivity extends Activity {

    private ShoppingList mShoppingList;
    private TextView titleText, budgetText;
    private TextView mTotalAndBudget;
    private Button mAddItemButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_show_list);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String budget = intent.getStringExtra("budget");
        //double budget = Double.valueOf(b).doubleValue();



        mShoppingList = new ShoppingList(name, budget);




        titleText = (TextView) findViewById(R.id.listNameTextView);
        titleText.setText(name);
        budgetText = (TextView) findViewById(R.id.budgetTotalTextView);
        budgetText.setText(budget);

        mAddItemButton = (Button) findViewById(R.id.addItemButton);

        mAddItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create new item, maybe recall on create?
            //    addItem();

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

        String [] itemNames = new String[mShoppingList.getItemCount()];
        ShoppingItem [] currentItems = mShoppingList.getShoppingList();

        for (int i = 0; i <= mShoppingList.getItemCount(); i++){
            itemNames[i] = currentItems[i].getItemName();
        }

//        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemNames));
    }

}
