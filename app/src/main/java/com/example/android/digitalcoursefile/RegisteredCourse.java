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

public class RegisteredCourse extends RecyclerView.Adapter<RegisteredCourse.MyViewHolder> {
    private List<course> dataList;
    private Context context;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView courseName,courseID;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            context=itemView.getContext();
            courseName=itemView.findViewById(R.id.textView35);
            courseID=itemView.findViewById(R.id.textView20);

        }
    }
    public RegisteredCourse(List<course> dataList) {
        this.dataList = dataList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_registered_course, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final course c=dataList.get(position);
        holder.courseName.setText("Course Name : "+c.getCourseName());
        holder.courseID.setText("Course ID : "+c.getCourseID());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


}
