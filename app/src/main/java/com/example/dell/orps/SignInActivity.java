package com.example.dell.orps;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity {
EditText name,contact,email;
    Button signup;
    String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        name=(EditText)findViewById(R.id.name);
        contact=(EditText)findViewById(R.id.phone);
        email=(EditText)findViewById(R.id.email);
        signup=(Button)findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().equals("") || contact.getText().toString().equals("") || email.getText().toString().equals("")){
                    Toast.makeText(SignInActivity.this, "Please Fill All The Fields", Toast.LENGTH_SHORT).show();}
                else {
                    if(!email.getText().toString().matches(emailPattern)){
                        Toast.makeText(SignInActivity.this, "Please Enter a valid Email Address", Toast.LENGTH_SHORT).show();
                    }
                   else if(contact.getText().length()!=10)
                    {
                        Toast.makeText(SignInActivity.this, "Please Enter 10 digit Mobile Number", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        SharedPreferences sharedPreferences = getSharedPreferences("Mypref", 0);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("NAME", name.getText().toString().trim());
                        editor.putString("CONTACT", contact.getText().toString().trim());
                        editor.putString("EMAIL", email.getText().toString().trim());
                        editor.apply();
                        Intent i = new Intent(SignInActivity.this, MainActivity.class);
                        startActivity(i);
                    }
                }
            }
        });
        InputFilter filter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (Character.isWhitespace(source.charAt(i))) {
                        return "";
                    }
                }
                return null;
            }

        };
        contact.setFilters(new InputFilter[] { filter });
        email.setFilters(new InputFilter[] { filter });
    }
}
