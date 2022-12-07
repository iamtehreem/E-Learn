package com.i190405.task2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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
    String name,number,address;
    SharedPreferences mPref;
    SharedPreferences.Editor editmPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv=findViewById(R.id.rv);
        add=findViewById(R.id.add);
        name="How To learn?";
        number="Dr. Tehreem";
        address="Have you mastered effective communication strategies? Delivering a presentation can cause even the most confident among us to break a sweat. Whether you are delivering a formal presentation, making a pitch, or leading a group discussion, communicating your message with poise, confidence, and conviction is essential in connecting with your audience and inspiring them by building trust and credibility.\n" +
                "\n" +
                "Through oral presentations and small group activities, you will put proven techniques and tools into practice, test out new approaches, and learn to communicate clearly and confidently. Discover the powerful impact of storytelling and practical persuasion skills to authentically illustrate your message. Learn how to effectively organize materials to blend analytical and emotional content into a compelling story, and incorporate dynamic introductions and memorable endings into your presentations. You’ll leave the program with the skills needed to engage, inform, and inspire others—and improve your ability to communicate as a leader.\n" +
                "\n" +
                "Note: For online sessions, all program content will be delivered live and will not be recorded.";
        ls=new ArrayList<>();
        mPref = getSharedPreferences("com.i190405.task2", MODE_PRIVATE);
        editmPref = mPref.edit();//This allows us to edit the values as well
        ls.add(new MyModel(name,number,address));
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
    @Override
    protected void onResume() {
        super.onResume();
        name=mPref.getString("name","name");
        address=mPref.getString("address","address");
        number=mPref.getString("number","number");
        ls.add(new MyModel(name,number,address));
        adapter=new MyAdapter(ls,MainActivity.this);
        rv.setAdapter(adapter);
        RecyclerView.LayoutManager lm=new LinearLayoutManager(MainActivity.this);
        rv.setLayoutManager(lm);



    }


}