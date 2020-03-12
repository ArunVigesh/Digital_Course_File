package com.example.android.digitalcoursefile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

import static com.example.android.digitalcoursefile.MainActivity.USERNAME;

public class PasswordUpdate extends AppCompatActivity {
    String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_update);
        Intent i=getIntent();
        Bundle bundle=i.getExtras();
        if(USERNAME==null)
        {
            userName=bundle.getString("user");
        }
        else
        {
            userName=USERNAME;
        }
        final EditText newpass=findViewById(R.id.editText13);
        final EditText cnfpass=findViewById(R.id.editText14);
        Button submit=findViewById(R.id.button26);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(newpass.getText().toString().equals(cnfpass.getText().toString())))
                {
                    Toast.makeText(getApplicationContext(),"Password Don't Match",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(newpass.getText()))
                {
                    newpass.setError("Field is Empty");
                }
                else if(TextUtils.isEmpty(cnfpass.getText()))
                {
                    cnfpass.setError("Field is Empty");
                }
                else if(newpass.getText().toString().equals(cnfpass.getText().toString()))
                {

                    AsyncHttpClient client = new AsyncHttpClient();
                    RequestParams params = new RequestParams();
                    params.add( "password", newpass.getText().toString().trim() );
                    params.add( "username", userName );
                    client.post( "https://dcfse.000webhostapp.com/updatePassword.php", params, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            try {

                                Log.e( "ER", new String( responseBody ) );
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                        }

                    });
                    Toast.makeText(getApplicationContext(),"Password Updated",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(PasswordUpdate.this,MainActivity.class);
                    startActivity(i);

                }
            }
        });

    }
}
