package com.example.midtronicsdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ListoCountries extends AppCompatActivity {
    ArrayList<String> countries;
    String[] places;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listo_countries);

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        final ListView CountryList=(ListView)findViewById(R.id.listview);

        //part of list view test
        countries = new ArrayList<String>();

        //getCountries
        places = getResources().getStringArray(R.array.countries_array);

        // Create The Adapter with passing ArrayList as 3rd parameter
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,places);
        // Set The Adapter

        CountryList.setAdapter(arrayAdapter);

        CountryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedFromList =(CountryList.getItemAtPosition(position).toString());

                Intent StartNewActivity = new Intent(ListoCountries.this, DisplayInfo.class);
                StartNewActivity.putExtra("message", selectedFromList);
                startActivity(StartNewActivity);
            }
        });

    }
//this was to initally test list view
    void getCountries(){
        countries.add("test1");
        countries.add("test2");
        countries.add("test3");
    }

}
