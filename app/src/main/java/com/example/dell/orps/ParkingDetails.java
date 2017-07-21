package com.example.dell.orps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ParkingDetails extends AppCompatActivity {
    String TAG="NetworkUtils";
    String res;
    ArrayAdapter<String> stationadapter;
    ArrayAdapter TwoWheeleradapter;
    ArrayAdapter FourWheeleradapter;
    List<String> stationdetails,twowheelerlist,fourwheelerlist;
    String selectedstation;
    ListView station_details,two_wheeler,four_wheeler;
    Button book;
    String station_id,station_name,station_class,tot_2w_park,avail_2w_park,tot_4w_park,avail_4w_park;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_details);
        Intent get = getIntent();
        station_details = (ListView) findViewById(R.id.stationdetails);
        book = (Button) findViewById(R.id.book);
        two_wheeler = (ListView) findViewById(R.id.Two_Wheeler);
        four_wheeler = (ListView) findViewById(R.id.Four_Wheeler);
        if (get.hasExtra(Intent.EXTRA_TEXT)) {
            selectedstation = get.getStringExtra(Intent.EXTRA_TEXT);
        }
        String url = "http://192.168.1.103/OPRS-server-master/OPRS-server-master/getStationParkingStatus.php";
        StringRequest json=new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
            @Override
            public void onResponse(final String response) {
                try {
                    JSONObject jsonobject;
                    jsonobject = new JSONObject(response);
                    station_id = jsonobject.get("station_id").toString();
                    station_name = jsonobject.get("station_name").toString();
                    station_class = jsonobject.get("station_class").toString();
                    tot_2w_park = jsonobject.get("tot_2w_park").toString();
                    avail_2w_park = jsonobject.get("avail_2w_park").toString();
                    tot_4w_park = jsonobject.get("tot_4w_park").toString();
                    avail_4w_park = jsonobject.get("avail_4w_park").toString();
                    stationdetails = new ArrayList<>();
                    stationdetails.add("Station ID : " + station_id);
                    stationdetails.add("Station Name : " + station_name);
                    stationdetails.add("Station Class : " + station_class);
                    stationadapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, stationdetails);
                    station_details.setAdapter(stationadapter);
                    twowheelerlist = new ArrayList<>();
                    twowheelerlist.add("Total Parking : " + tot_2w_park);
                    twowheelerlist.add("Available Parking : " + avail_2w_park);
                    fourwheelerlist = new ArrayList<>();
                    fourwheelerlist.add("Total Parking : " + tot_4w_park);
                    fourwheelerlist.add("Available Parking : " + avail_4w_park);
                    TwoWheeleradapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, twowheelerlist);
                    two_wheeler.setAdapter(TwoWheeleradapter);
                    FourWheeleradapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, fourwheelerlist);
                    four_wheeler.setAdapter(FourWheeleradapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("abd", "Error: " + error
                        + ">>" + error.networkResponse.statusCode
                        + ">>" + error.networkResponse.data
                        + ">>" + error.getCause()
                        + ">>" + error.getMessage());

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
                map.put("station_id",selectedstation);
                return map;
            }
        }
                ;
        MySingleton.getInstance(this).addToRequestQueue(json);


        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });





    }


    }
