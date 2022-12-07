package com.i190405.task2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    List<MyModel> ls;
    MyAdapter adapter;
    ImageView add;
    TextView name;
    TextView number;
    TextView address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv=findViewById(R.id.rv);
        add=findViewById(R.id.add);

        ls=new ArrayList<>();
        ls.add(new MyModel("Hello","123456789","qwerty"));
        adapter=new MyAdapter(ls,MainActivity.this);
        rv.setAdapter(adapter);
        RecyclerView.LayoutManager lm=new LinearLayoutManager(MainActivity.this);
        rv.setLayoutManager(lm);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ADD.class);
                startActivityForResult(intent,1);

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            if(resultCode== Activity.RESULT_OK){
                String nam=data.getStringExtra("nam");
                String addrs=data.getStringExtra("addrs");
                String num=data.getStringExtra("num");
                MyModel m=new MyModel(nam,num,addrs);
                byte[] bytearr=data.getByteArrayExtra("img");
                Bitmap bitmap= BitmapFactory.decodeByteArray(bytearr,0,bytearr.length);
                m.setDp(bitmap);
                ls.add(m);
                adapter=new MyAdapter(ls,MainActivity.this);
                rv.setAdapter(adapter);
                RecyclerView.LayoutManager lm=new LinearLayoutManager(MainActivity.this);
                rv.setLayoutManager(lm);
            }

        }
    }
}