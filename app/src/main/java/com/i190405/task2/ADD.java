package com.i190405.task2;

import android.app.Activity;
import android.content.Intent;
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

import de.hdodenhof.circleimageview.CircleImageView;

public class ADD extends AppCompatActivity {
    //ImageView dp;
    CircleImageView dp;
    EditText name,number,address;
    Button add;
    Bitmap bitmap;
    byte[] bytearr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        dp=findViewById(R.id.dp);
        number=findViewById(R.id.number);
        address=findViewById(R.id.address);
        name=findViewById(R.id.name);
        add=findViewById(R.id.add);


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
                Intent intent=new Intent(ADD.this,MainActivity.class);
                intent.putExtra("nam",nam);
                intent.putExtra("addrs",addrs);
                intent.putExtra("num",num);
                intent.putExtra("img",bytearr);
                setResult(Activity.RESULT_OK,intent);
                finish();

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
}