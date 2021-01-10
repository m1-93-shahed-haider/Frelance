package com.example.frelance0;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter {

    ArrayList<Users> mArray;
    Context context;

    public Adapter(ArrayList<Users> mArray, Context context) {
        this.mArray = mArray;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((ViewHolder) holder).FullName.setText(mArray.get(position).getFullName());
        ((ViewHolder) holder).JobTitle.setText(mArray.get(position).getJobTitle());
        ((ViewHolder) holder).view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,Freelancer_Profile.class);
                i.putExtra("user",mArray.get(position));
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mArray.size();
    }

    public static  class ViewHolder extends RecyclerView.ViewHolder{

        public TextView FullName;
        public TextView JobTitle;
        public View view;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            FullName = itemView.findViewById(R.id.FullName);
            JobTitle = itemView.findViewById(R.id.JobTitle);
        }
    }
}


