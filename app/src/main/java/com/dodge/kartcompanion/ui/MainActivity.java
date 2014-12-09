package com.dodge.kartcompanion.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.dodge.kartcompanion.R;
import com.dodge.kartcompanion.model.ShoppingList;

import java.util.ArrayList;


public class MainActivity extends Activity {

    private Button mNewListButton;
    private Button mSavedListsButton;
    private Button mBudgetViewButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        mNewListButton = (Button) findViewById(R.id.newListButton);

        mNewListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Open new list view
                openNewList();
            }
        });

    }

    private void openNewList(){
        Intent intent = new Intent(this, CreateListActivity.class);
        startActivity(intent);
    }



}
