package com.example.android.digitalcoursefile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

import static com.example.android.digitalcoursefile.ActivityLog.ExceptionString;
import static com.example.android.digitalcoursefile.ActivityLog.JSONExceptionString;

public class MainActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    TextView signup;
    Button signin;
    EditText username,password;
    TextView forgotPass;
    public static String USERNAME;
    public static final String PREFS_NAME = "PrefFile";
    private static final String PREF_USERNAME = "Username";
    private static final String PREF_PASS = "Password";
    private static final String PREF_ISCHECKED = "IsChecked";
    CheckBox remember;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog = new ProgressDialog(this);
        username=findViewById(R.id.editText);
        forgotPass=findViewById(R.id.textView80);
        password=findViewById(R.id.editText2);
        signup=findViewById(R.id.textView3);
        remember=findViewById(R.id.checkBox);
        SharedPreferences spref = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        if(spref.contains(PREF_USERNAME)) {
            username.setText(spref.getString(PREF_USERNAME, ""));
        }
        if(spref.contains((PREF_PASS))) {
            password.setText(spref.getString(PREF_PASS, ""));
        }
        if(spref.contains((PREF_ISCHECKED)))
        {
            Boolean c=spref.getBoolean(PREF_ISCHECKED,false);
            remember.setChecked(c);
        }

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
                            Log.e("Size", "JSON Array: "+jsonArray.length());
                            if(jsonArray.length()==0)
                                Toast.makeText( getApplicationContext(), "Invalid Credentials ", Toast.LENGTH_SHORT ).show();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonobject = jsonArray.getJSONObject(i);
                                USERNAME= jsonobject.getString("userName");
                                Log.e("ID", "onSuccess: "+USERNAME);
                            }

                            if((username.getText().toString().trim().equals("admin") )&& (username.getText().toString().trim().equals(USERNAME))) {
                                Intent i = new Intent(MainActivity.this, AdminDashboard.class);
                                startActivity(i);
                                Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                            }
                            else if(username.getText().toString().trim().equals(USERNAME))
                            {
                                Intent i=new Intent(MainActivity.this,Dashboard.class);
                                startActivity(i);
                                Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText( getApplicationContext(), "Invalid Credentials ", Toast.LENGTH_SHORT ).show();
                            }

                            SharedPreferences pref;
                            SharedPreferences.Editor ed;
                            pref=getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                            ed=pref.edit();
                            if(remember.isChecked()) {
                                Boolean boolisChecked=remember.isChecked();
                                ed.putString(PREF_USERNAME, username.getText().toString());
                                ed.putString(PREF_PASS, password.getText().toString());
                                ed.putBoolean(PREF_ISCHECKED, boolisChecked);
                                ed.apply();
                            }
                            else
                            {
                                pref.edit().clear().apply();
                            }

                        } catch (Exception e) {
                            Log.e(ExceptionString,JSONExceptionString+e );
                        }

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        progressDialog.dismiss();
                        Toast.makeText( getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT ).show();
                    }
                });




                }
            });
            forgotPass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(MainActivity.this,forgotPassword.class);
                    startActivity(i);
                }
            });
        }




}
