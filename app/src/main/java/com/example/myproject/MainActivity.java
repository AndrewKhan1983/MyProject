package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> products = new ArrayList<String>();
    ArrayList<Integer> number = new ArrayList<Integer>();
    ArrayList<String> productsSelect = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ListView productsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Collections.addAll(products, "Orange");
        productsList = (ListView) findViewById(R.id.container);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice,products);
        productsList.setAdapter(adapter);
        productsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            String product = adapter.getItem(position);
            if (productsList.isItemChecked(position))
                productsSelect.add(product);
            else
                productsSelect.remove(product);

            }
        });

    }

    public void add(View v) {
        EditText productName = (EditText) findViewById(R.id.product);
        String product = productName.getText().toString();
        if (!product.isEmpty()){
            adapter.add(product);
            productName.setText("");
            adapter.notifyDataSetChanged();
        }
    }
    public void remove (View v){
        for (int i = 0; i<productsSelect.size();i++){
            adapter.remove(productsSelect.get(i));
        }
        productsList.clearChoices();
        productsSelect.clear();
        adapter.notifyDataSetChanged();
    }
}

