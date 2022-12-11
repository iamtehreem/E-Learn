package com.i190405.task2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class enroll_course extends AppCompatActivity {

    Button notify;
    private FirebaseDatabase db=FirebaseDatabase.getInstance();
    private DatabaseReference root= db.getReference().child("Users");
    EditText name,coursename,age;
    String nme,cnme,agee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll_course);

        notify=findViewById(R.id.notify);
        name=findViewById(R.id.name);
        coursename=findViewById(R.id.coursename);
        age=findViewById(R.id.age);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel=new NotificationChannel("My Notification","My Notification", NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);


        }

        notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nme=name.getText().toString();
                String cnme=coursename.getText().toString();
                String agee=age.getText().toString();

                HashMap<String, String> userMap=new HashMap<>();

                userMap.put("name",nme);
                userMap.put("coursename",cnme);
                userMap.put("age",agee);

                root.push().setValue(userMap);

                Toast.makeText(
                        enroll_course.this,
                        "User Enrolled",
                        Toast.LENGTH_LONG
                ).show();


                //For notification
                NotificationCompat.Builder builder = new NotificationCompat.Builder(enroll_course.this, "My Notification");
                builder.setContentTitle("Course Enrollment");
                builder.setContentText("Hi, You have successfully enrolled in the course.");

                builder.setSmallIcon(R.drawable.logo);
                builder.setAutoCancel(true);

                NotificationManagerCompat managerCompat=NotificationManagerCompat.from(enroll_course.this);
                managerCompat.notify(1,builder.build());



            }
        });

    }
}