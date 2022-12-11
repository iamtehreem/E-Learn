package com.i190405.task2;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class third  extends AppCompatActivity {
    TextView name;
    TextView number;
    TextView address;
    ImageView img;
    SharedPreferences mPref;
    SharedPreferences.Editor editmPref;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thirdactivity);
        name=findViewById(R.id.name);
        number=findViewById(R.id.number);
        address=findViewById(R.id.address);
        img=findViewById(R.id.image);


        name.setText(getIntent().getStringExtra("nam"));
        number.setText(getIntent().getStringExtra("addrs"));
        address.setText(getIntent().getStringExtra("num"));
        byte[] bytearr=getIntent().getByteArrayExtra("img");
        Bitmap bitmap= BitmapFactory.decodeByteArray(bytearr,0,bytearr.length);
        img.setImageBitmap(bitmap);

//        notify.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NotificationCompat.Builder builder = new NotificationCompat.Builder(third.this, "My Notification");
//                builder.setContentTitle("Course Enrollment");
//                builder.setContentText("Hi, You have successfully enrolled in the course.");
//
//                builder.setSmallIcon(R.drawable.ic_launcher_background);
//                builder.setAutoCancel(true);
//
//                NotificationManagerCompat managerCompat=NotificationManagerCompat.from(third.this);
//                managerCompat.notify(1,builder.build());
//
//
//
//            }
//        });



    }



}


