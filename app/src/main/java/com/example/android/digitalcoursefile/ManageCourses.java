package com.example.android.digitalcoursefile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class ManageCourses extends AppCompatActivity {
    EditText courseId;
    EditText courseName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_courses);
        courseId=findViewById(R.id.editText21);
        courseName=findViewById(R.id.editText22);
        Button AddC=findViewById(R.id.button25);
        AddC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();
                params.add( "courseID", courseId.getText().toString().trim());
                params.add( "courseName", courseName.getText().toString().trim() );
                client.post( "https://dcfse.000webhostapp.com/addCourse.php", params, new AsyncHttpResponseHandler() {
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
                Toast.makeText(getApplicationContext(),"Added Course",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
