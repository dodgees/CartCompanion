package com.dodge.kartcompanion.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dodge.kartcompanion.R;

public class AddItemActivity extends Activity {

    private Button mAddItemButton;
    private EditText mItemNameField, mItemPriceField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        mAddItemButton = (Button) findViewById(R.id.addItemButton);

        mAddItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemNameField = (EditText) findViewById(R.id.itemNameEditText);
                mItemPriceField = (EditText) findViewById(R.id.priceEditText);

                String itemName = mItemNameField.getText().toString();
                String p = mItemPriceField.getText().toString();
                double itemPrice = Double.valueOf(p).doubleValue();

                Intent data = new Intent();
                data.putExtra("itemName", itemName);
                data.putExtra("itemPrice", itemPrice);

                setResult(RESULT_OK, data);
                finish();
            }
        });


    }



}
