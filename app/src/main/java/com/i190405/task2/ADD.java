package com.i190405.task2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ADD extends AppCompatActivity {
    //ImageView dp;
    ArrayList<MyModel> arrayList;
    CircleImageView dp;
    EditText name,number,address;
    String nam1,num1,addrs1;
    Button add;
    Bitmap bitmap;
    byte[] bytearr;
    SharedPreferences mPref;
    SharedPreferences.Editor editmPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        dp=findViewById(R.id.dp);
        number=findViewById(R.id.number);
        address=findViewById(R.id.address);
        name=findViewById(R.id.name);
        add=findViewById(R.id.add);
        mPref = getSharedPreferences("com.i190405.task2", MODE_PRIVATE);
        editmPref = mPref.edit();//This allows us to edit the values as well

        dp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(
                        Intent.createChooser(i,"Choose your Dp"),
                        100
                );
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nam=name.getText().toString();
                String addrs=address.getText().toString();
                String num=number.getText().toString();
                nam1=nam;addrs1=addrs;num1=num;
                Intent intent=new Intent(ADD.this,MainActivity.class);
                intent.putExtra("nam",nam);
                intent.putExtra("addrs",addrs);
                intent.putExtra("num",num);
                intent.putExtra("img",bytearr);
                setResult(Activity.RESULT_OK,intent);
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode==100){
            Uri dppp=data.getData();
            dp.setImageURI(dppp);

                ByteArrayOutputStream stream=new ByteArrayOutputStream();
            try {
                bitmap= MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(),dppp);
            } catch (IOException e) {
                e.printStackTrace();
            }
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
                bytearr=stream.toByteArray();

            //URI actually is not the image but the adress
            //Class activity use bitmap to import image
            //Convert bitmap into biytearray, compress it and append it to the first image
            //and by clicking a list a new page with all info should be opened
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        editmPref.putString("name", nam1);//saves the value
        editmPref.putString("number", num1);//saves the value
        editmPref.putString("address", addrs1);//saves the value
        editmPref.apply();
        editmPref.commit(); //same value appear no matter how many times you restart the app
    }
}