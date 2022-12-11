package com.i190405.task2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    List<MyModel> ls;
    MyAdapter adapter;
    ImageView add;
    Button profilebtn,enrollcourse;
    String name,number,address;
    SharedPreferences mPref;
    SharedPreferences.Editor editmPref;
    private ArrayList<MyModel> courseModelArrayList;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv=findViewById(R.id.rv);
        add=findViewById(R.id.add);
        profilebtn=findViewById(R.id.profilebtn);

        enrollcourse=findViewById(R.id.enrollcourse);
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



        enrollcourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, enroll_course.class));
            }
        });

        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, profile.class));
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
    // calling on create option menu
    // layout to inflate our menu file.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // below line is to get our inflater
        MenuInflater inflater = getMenuInflater();

        // inside inflater we are inflating our menu file.
        inflater.inflate(R.menu.search_menu, menu);

        // below line is to get our menu item.
        MenuItem searchItem = menu.findItem(R.id.actionSearch);

        // getting search view of our item.
        SearchView searchView = (SearchView) searchItem.getActionView();

        // below line is to call set on query text listener method.
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                filter(newText);
                return false;
            }
        });
        return true;
    }

    private void filter(String text) {
        // creating a new array list to filter our data.
        ArrayList<MyModel> filteredlist = new ArrayList<MyModel>();

        // running a for loop to compare elements.
        for (MyModel item : ls) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            adapter.filterList(filteredlist);
        }
    }

    private void buildRecyclerView() {

        // below line we are creating a new array list
        courseModelArrayList = new ArrayList<MyModel>();

        // below line is to add data to our array list.
        courseModelArrayList.add(new MyModel("DSA", "DSA Self Paced Course","hello"));
        courseModelArrayList.add(new MyModel("JAVA", "JAVA Self Paced Course","hello1"));
        courseModelArrayList.add(new MyModel("C++", "C++ Self Paced Course","hello2"));
        courseModelArrayList.add(new MyModel("Python", "Python Self Paced Course","hello3"));
        courseModelArrayList.add(new MyModel("Fork CPP", "Fork CPP Self Paced Course","hello4"));

        // initializing our adapter class.
        adapter = new MyAdapter(courseModelArrayList, MainActivity.this);

        // adding layout manager to our recycler view.
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rv.setHasFixedSize(true);

        // setting layout manager
        // to our recycler view.
        rv.setLayoutManager(manager);

        // setting adapter to
        // our recycler view.
        rv.setAdapter(adapter);
    }


}