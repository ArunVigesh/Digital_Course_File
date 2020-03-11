package com.example.android.digitalcoursefile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

import static com.example.android.digitalcoursefile.MainActivity.USERNAME;

public class ActivityLog extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String courseSelected;
    Button submit;
    RecyclerView log;
    Spinner spincourse;
    EditText content;
    List<String> courses;
    ArrayList<log> dataList = new ArrayList<log>();
    LogAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        spincourse=findViewById(R.id.spinner5);
        spincourse.setOnItemSelectedListener(this);
        submit=findViewById(R.id.button35);
        content=findViewById(R.id.editText15);
        log=findViewById(R.id.activityLog);
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.add("username",USERNAME);
        client.post("https://dcfse.000webhostapp.com/getCoursesReg.php", params,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.e("ER",new String(responseBody));
                JSONObject jsonObject;
                try {
                    courses=new ArrayList<String>();
                    courses.add("Select Course");
                    jsonObject = new JSONObject(new  String(responseBody));
                    JSONArray jsonArray = jsonObject.getJSONArray("courselist");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonobject = jsonArray.getJSONObject(i);
                        courses.add(jsonobject.getString("courseID"));
                    }

                    ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item,courses);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spincourse.setAdapter(adapter);

                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncHttpClient myClient = new AsyncHttpClient();
                RequestParams params = new RequestParams();
                params.add("log",content.getText().toString().trim());
                params.add("username",USERNAME);
                params.add("courseID",courseSelected);
                myClient.post("https://dcfse.000webhostapp.com/SubmitLog.php", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        try {
                            content.getText().clear();
                            Log.e( "ER", new String( responseBody ) );
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }


                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    }
                });
            }
        });

        AsyncHttpClient myClient = new AsyncHttpClient();
        RequestParams paramsl = new RequestParams();
        paramsl.add("username",USERNAME);
        myClient.post("https://dcfse.000webhostapp.com/showLog.php", paramsl, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.e("ER",new String(responseBody));
                JSONObject jsonObject;
                try {
                    jsonObject = new JSONObject(new  String(responseBody));
                    JSONArray jsonArray = jsonObject.getJSONArray("datalist");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonobject = jsonArray.getJSONObject(i);
                        String time=jsonobject.getString("Time");
                        String log=jsonobject.getString("log");
                        String courseID=jsonobject.getString("courseID");
                        log u=new log();
                        u.setTime(time);
                        u.setContent(log);
                        u.setCourseID(courseID);
                        dataList.add(u);
                    }
                    mAdapter = new LogAdapter(dataList);
                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    log.setLayoutManager(mLayoutManager);
                    log.setAdapter(mAdapter);
                    log.setItemAnimator(new DefaultItemAnimator());
                    mAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        courseSelected=parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



}
