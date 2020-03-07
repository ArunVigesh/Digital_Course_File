package com.example.android.digitalcoursefile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class trackRegistrationAdapter extends RecyclerView.Adapter<trackRegistrationAdapter.MyViewHolder>{

    private List<trackCourse> dataList;
    private Context context;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView courseName,courseID,status;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            context=itemView.getContext();
            courseName=itemView.findViewById(R.id.textView37);
            courseID=itemView.findViewById(R.id.textView36);
            status=itemView.findViewById(R.id.textView38);

        }
    }
    public trackRegistrationAdapter(List<trackCourse> dataList) {
        this.dataList = dataList;
    }
    @NonNull
    @Override
    public trackRegistrationAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_track_registration_adapter, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull trackRegistrationAdapter.MyViewHolder holder, int position) {
        final trackCourse tc=dataList.get(position);
        holder.courseName.setText("Course Name : "+tc.getCourseName());
        holder.courseID.setText("Course ID : "+tc.getCourseID());
        int app=tc.getApproval();
        if(app==0)
        {
            holder.status.setText("Status : Not Approved");
        }
        else if(app==1)
        {
            holder.status.setText("Status : Approved");
        }

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}
