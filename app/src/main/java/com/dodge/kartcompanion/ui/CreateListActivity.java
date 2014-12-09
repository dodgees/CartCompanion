package com.dodge.kartcompanion.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dodge.kartcompanion.R;
import com.dodge.kartcompanion.model.ShoppingList;

public class CreateListActivity extends Activity {

    private Button mCreateListButton;
    private EditText mNameField, mBudgetField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list);

        mCreateListButton = (Button) findViewById(R.id.createListButton);

        mCreateListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Add empty list to arrayList of shoppingLists
                mNameField = (EditText) findViewById(R.id.itemNameEditText);
                mBudgetField = (EditText) findViewById(R.id.budgetEditText);

                String name = "New List";
                String budget;

                name = mNameField.getText().toString();
                budget = mBudgetField.getText().toString();

                createNewList(name, budget);

            }
        });

    }

    public void createNewList(String name, String budget){
        Intent intent = new Intent(this, ShowListActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("budget", budget);

        startActivity(intent);

    }


}
