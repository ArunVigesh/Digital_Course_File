package com.example.android.digitalcoursefile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class forgotPassword extends AppCompatActivity {
 EditText otp,username;
 Button reqOTP,submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        otp=findViewById(R.id.editText24);
        username=findViewById(R.id.editText18);
        reqOTP=findViewById(R.id.button41);
        submit=findViewById(R.id.button42);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(forgotPassword.this,PasswordUpdate.class);
                i.putExtra("user",username.getText().toString());
                startActivity(i);
            }
        });
    }
}
