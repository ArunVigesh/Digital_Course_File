package com.example.android.digitalcoursefile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView signup;
        Button signin;
        final EditText adminText;
        adminText=findViewById(R.id.editText);
        signup=findViewById(R.id.textView3);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,UserRegister.class);
                startActivity(i);
            }
        });
        signin=findViewById(R.id.button);
            signin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                    if(adminText.getText().toString().equals("admin")) {
                        Intent i = new Intent(MainActivity.this, AdminDashboard.class);
                        startActivity(i);
                    }
                    else
                    {
                        Intent i=new Intent(MainActivity.this,Dashboard.class);
                        startActivity(i);
                    }
                }
            });
        }




}
