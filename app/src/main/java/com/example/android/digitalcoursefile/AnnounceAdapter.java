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

public class AnnounceAdapter extends RecyclerView.Adapter<AnnounceAdapter.MyViewHolder> {

    private List<announce> dataList;
    private Context context;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView time,announce;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            context=itemView.getContext();
            time=itemView.findViewById(R.id.textView63);
            announce=itemView.findViewById(R.id.textView64);

        }
    }
    public AnnounceAdapter(List<announce> dataList) {
        this.dataList = dataList;
    }
    @NonNull
    @Override
    public AnnounceAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_announce_adapter, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AnnounceAdapter.MyViewHolder holder, int position) {
        final announce tc=dataList.get(position);
        holder.time.setText("Time : "+tc.getTime());
        holder.announce.setText("Announcement : "+tc.getAnnouncement());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
