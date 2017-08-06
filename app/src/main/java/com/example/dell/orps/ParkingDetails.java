
package com.example.dell.orps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ParkingDetails extends AppCompatActivity {
    private static final int MY_SOCKET_TIMEOUT_MS =5000 ;
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
    String url = "http://192.168.1.103/OPRS-server-master/OPRS-server-master/getStationParkingStatus.php";
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
        stringrequest();
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent i1=new Intent(ParkingDetails.this,BookingActivity.class);
                startActivity(i1);
            }
        });

 // Get a support ActionBar corresponding to this toolbar
 ActionBar ab = getSupportActionBar();
 // Enable the Up button
 ab.setDisplayHomeAsUpEnabled(true);


    }

    public  void stringrequest(){
        StringRequest json=new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
            @Override
            public void onResponse(final String response) {
                if (response != null) {
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
                        stationadapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.stationdetailslayout, stationdetails);
                        station_details.setAdapter(stationadapter);
                        twowheelerlist = new ArrayList<>();
                        twowheelerlist.add("Total Parking : " + tot_2w_park);
                        twowheelerlist.add("Available Parking : " + avail_2w_park);
                        fourwheelerlist = new ArrayList<>();
                        fourwheelerlist.add("Total Parking : " + tot_4w_park);
                        fourwheelerlist.add("Available Parking : " + avail_4w_park);
                        TwoWheeleradapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.parkingdetailslayout, twowheelerlist);
                        two_wheeler.setAdapter(TwoWheeleradapter);
                        FourWheeleradapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.parkingdetailslayout, fourwheelerlist);
                        four_wheeler.setAdapter(FourWheeleradapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    Toast.makeText(ParkingDetails.this, "No response from server", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                if (error instanceof TimeoutError) {
                    Toast.makeText(ParkingDetails.this, "Noconnection"+error, Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(ParkingDetails.this, "Failureerror", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError) {
                    Toast.makeText(ParkingDetails.this, "Server error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof NetworkError) {
                    Toast.makeText(ParkingDetails.this, "NetworkError", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    Toast.makeText(ParkingDetails.this, "ParseError", Toast.LENGTH_SHORT).show();
                }

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
        json.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getInstance(this).addToRequestQueue(json);
    }
}

