package com.test.room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    FloatingActionButton fab;
    List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        users = new ArrayList<>();
//
//        for(int i=0;i<100;i++){
//            User user = new User("Luis #"+i,"Garcia","ldgl1215@gmail.com");
//            users.add(user);
//        }

        getAndSetUsers();

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"onClick: pressed!");
                Intent i = new Intent(MainActivity.this,CreateUser.class);
                startActivityForResult(i,CreateUser.CREATE_REQUEST);
           }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CreateUser.CREATE_REQUEST){
            if(resultCode == RESULT_OK){
                Toast.makeText(getApplicationContext(),"Usuario creado con exito", Toast.LENGTH_LONG);

                getAndSetUsers();
            }
        }
    }

    public void getAndSetUsers(){
        final AppDatabase db  = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"production")
                .allowMainThreadQueries() //don't make this please, it's a bad practic
                .build();

        users = db.userDao().getAllUser();

        adapter = new UserAdapter(users);
        recyclerView.setAdapter(adapter);
    }
}
