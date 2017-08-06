package com.example.dell.orps;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Mukul on 31-07-2017.
 */

public class DetailsFragment extends Fragment {
    public Button next;
    Context context;
    public DetailsFragment(){}
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final CarDetailsFragment carDetailsFragment=new CarDetailsFragment();
        final FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
        View rootview=inflater.inflate(R.layout.details_fragment,container,false);
        EditText name=(EditText)rootview.findViewById(R.id.name);
        EditText contact=(EditText)rootview.findViewById(R.id.contact);
        EditText email=(EditText)rootview.findViewById(R.id.email);
        next=(Button)rootview.findViewById(R.id.Next);
        SharedPreferences sharedPreferences=getContext().getSharedPreferences("Mypref",0);
        String name1=sharedPreferences.getString("NAME",null);
        name.setText(name1);
        String contact1=sharedPreferences.getString("CONTACT",null);
        contact.setText(contact1);
        String email1=sharedPreferences.getString("EMAIL",null);
        email.setText(email1);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction().replace(R.id.headcontainer,carDetailsFragment).commit();
            }
        });
        return rootview;
    }
}
