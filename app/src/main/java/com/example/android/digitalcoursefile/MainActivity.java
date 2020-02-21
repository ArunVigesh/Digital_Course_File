package com.example.android.digitalcoursefile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    TextView signup;
    Button signin;
    EditText username,password;
    public static String USERNAME;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog = new ProgressDialog(this);
        username=findViewById(R.id.editText);
        password=findViewById(R.id.editText2);
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
                    progressDialog.setMessage("Logging in...");
                    progressDialog.show();
                    AsyncHttpClient myClient = new AsyncHttpClient();
                    RequestParams params = new RequestParams();
                    params.add("userName",username.getText().toString());
                    params.add("password",password.getText().toString());
                    myClient.post("https://dcfse.000webhostapp.com/userLogin.php",params,new AsyncHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        try {
                            progressDialog.dismiss();

                            Log.e("ER",new String(responseBody));
                            JSONObject jsonObject = new JSONObject(new  String(responseBody));
                            JSONArray jsonArray = jsonObject.getJSONArray("getlist");
                            if(jsonArray.length()==0)
                                Toast.makeText( getApplicationContext(), "Invalid Credentials ", Toast.LENGTH_SHORT ).show();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonobject = jsonArray.getJSONObject(i);
                                String userName = jsonobject.getString("userName");
                                USERNAME=userName;
                                Log.e("ID", "onSuccess: "+USERNAME);
                            }
                            Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                            if(username.getText().toString().trim().equals("admin")) {
                                Intent i = new Intent(MainActivity.this, AdminDashboard.class);
                                startActivity(i);
                                username.getText().clear();
                                password.getText().clear();
                            }
                            else
                            {
                                Intent i=new Intent(MainActivity.this,Dashboard.class);
                                startActivity(i);
                                username.getText().clear();
                                password.getText().clear();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Toast.makeText( getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT ).show();
                    }
                });




                }
            });
        }




}
