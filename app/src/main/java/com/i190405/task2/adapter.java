package com.i190405.task2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.MyViewHolder> {
    List<enroll_model> ls;
    Context c;

    public adapter(List<enroll_model> ls, Context c) {
        this.ls = ls;
        this.c = c;
    }

    @NonNull
    @Override
    public adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {   //this generates a row hence
        View row= LayoutInflater.from(c).inflate(R.layout.activity_enroll_course,parent,false);
        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.name.setText(ls.get(position).getName());
        holder.coursename.setText(ls.get(position).getCoursename());
        holder.age.setText(ls.get(position).getAge());


    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,coursename,age;
        ImageView image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            coursename=itemView.findViewById(R.id.coursename);
            age=itemView.findViewById(R.id.age);

        }
    }
}