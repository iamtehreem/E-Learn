package com.i190405.task2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class logoscreen extends AppCompatActivity {

    Handler h=new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(logoscreen.this,signup.class);
                startActivity(i);
                finish();

            }
        }, 5000);
    }

}