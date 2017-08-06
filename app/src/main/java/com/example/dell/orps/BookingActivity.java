package com.example.dell.orps;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class BookingActivity extends AppCompatActivity {
Button confirmbooking;
    RadioButton half,one,two,three;
    RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        radioGroup=(RadioGroup)findViewById(R.id.radio);
        confirmbooking=(Button)findViewById(R.id.confirmbooking);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
             if(checkedId==R.id.halfhour)
             {
                 Toast.makeText(BookingActivity.this, "Balle balle", Toast.LENGTH_SHORT).show();
             }
             if(checkedId==R.id.onehour)
             {}
                if(checkedId==R.id.twohour)
                {}
                if(checkedId==R.id.threehour) {
                }

                }


        });
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
confirmbooking.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i9=new Intent(BookingActivity.this,DetailsActivity.class);
        startActivity(i9);

    }
});
    }

}
