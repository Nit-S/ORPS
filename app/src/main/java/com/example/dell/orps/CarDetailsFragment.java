package com.example.dell.orps;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mukul on 31-07-2017.
 */

public class CarDetailsFragment extends Fragment {
    List<String> vehicletypelist;
    SpinnerAdapter adapter;
    Context context;
    Button  confirmandpay;
    Button previous;
    public CarDetailsFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview=inflater.inflate(R.layout.car_details_fragment,container,false);
        final DetailsFragment detailsFragment=new DetailsFragment();
        final FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
        Spinner vehicletype=(Spinner)rootview.findViewById(R.id.vehicletypespinner);
        EditText vehiclenumber=(EditText)rootview.findViewById(R.id.vehiclenumber);
        EditText vehiclecolor=(EditText)rootview.findViewById(R.id.vehiclecolor);
        EditText vehiclename=(EditText)rootview.findViewById(R.id.vehiclename);
        confirmandpay=(Button)rootview.findViewById(R.id.confirmandpay);
        previous=(Button)rootview.findViewById(R.id.previous);
        vehicletypelist=new ArrayList<String>();
        vehicletypelist.add("Two Wheeler");
        vehicletypelist.add("Four Wheeler");
        adapter=new ArrayAdapter<String>(getContext(),R.layout.spinner_item,vehicletypelist);
        vehicletype.setAdapter(adapter);
        vehicletype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        confirmandpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i10=new Intent(getActivity(),PaymentActivity.class);
                startActivity(i10);
            }
        });
       previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction().replace(R.id.headcontainer,detailsFragment).commit();
            }
        });
        return rootview;
    }
}
