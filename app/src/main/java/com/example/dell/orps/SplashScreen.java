package com.example.dell.orps;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {
    TextView welcomenote;
    RelativeLayout splashlayout;
    private static int SPLASH_TIME_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
// Hide the status bar.
        int uiOptions = android.view.View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
// Remember that you should never show the action bar if the
// status bar is hidden, so hide that too if necessary.
        setContentView(R.layout.activity_spalsh_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                SharedPreferences sharedPreferences=getSharedPreferences("Mypref",0);
                if(sharedPreferences.getString("NAME",null)==null || sharedPreferences.getString("CONTACT",null)==null) {
                    Intent i4 = new Intent(SplashScreen.this, SignInActivity.class);
                    startActivity(i4);

                }
                else{
                    Intent i5=new Intent(SplashScreen.this,MainActivity.class);
                    startActivity(i5);

                }
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
        splashlayout=(RelativeLayout)findViewById(R.id.splashlayout);
        welcomenote=(TextView)findViewById(R.id.welcome_note);

    }

}
