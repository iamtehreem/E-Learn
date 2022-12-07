package com.i190405.task2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    List<MyModel> ls;
    Context c;

    public MyAdapter(List<MyModel> ls, Context c) {
        this.ls = ls;
        this.c = c;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {   //this generates a row hence
        View row= LayoutInflater.from(c).inflate(R.layout.row,parent,false);
        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.name.setText(ls.get(position).getName());
        holder.number.setText(ls.get(position).getNumber());
        holder.address.setText(ls.get(position).getAddress());
        holder.image.setImageBitmap(ls.get(position).getDp());
        holder.address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(c,third.class);
                intent.putExtra("nam",holder.name.getText().toString());
                intent.putExtra("addrs",holder.address.getText().toString());
                intent.putExtra("num",holder.number.getText().toString());
                ByteArrayOutputStream stream=new ByteArrayOutputStream();
                holder.image.buildDrawingCache();
                Bitmap bitmap=holder.image.getDrawingCache();
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
                byte[] bytearr=stream.toByteArray();
                intent.putExtra("img",bytearr);
                c.startActivity(intent);
               // Intent i=new Intent();
                //c.startActivity();
                Toast.makeText(c,position+"",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,number,address;
        ImageView image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            number=itemView.findViewById(R.id.number);
            address=itemView.findViewById(R.id.address);
            image=itemView.findViewById(R.id.image);
        }
    }
}

