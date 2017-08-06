package com.example.dell.orps;


import android.content.Intent;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
TextView welcome;
    Button findparking;
    Spinner stationspinner;
    ArrayAdapter<String> adapter;
    List<String> stationlist;
    String selectedstation;
    String TAG="mainactivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        stationspinner=(Spinner)findViewById(R.id.stationspinner);
        welcome=(TextView)findViewById(R.id.welcome);
        SharedPreferences sharedPreferences=getSharedPreferences("Mypref",0);
        String name=sharedPreferences.getString("NAME",null);
        welcome.append(name);
        stationlist=new ArrayList<String>();
        stationlist.add("ghy");
        stationlist.add("ndls");
        adapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_item,stationlist);
        stationspinner.setAdapter(adapter);
        stationspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
              selectedstation=adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        findparking=(Button)findViewById(R.id.findparking);
        findparking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent post=new Intent(MainActivity.this,ParkingDetails.class);
                post.putExtra(Intent.EXTRA_TEXT,selectedstation);
                startActivity(post);

            }
        });
    }

}
