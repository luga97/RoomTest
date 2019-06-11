package com.test.room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class CreateUser extends AppCompatActivity {
    public static final int CREATE_REQUEST = 1;
    private static final String TAG = "CreateUser";
    EditText firstName;
    EditText lastName;
    EditText email;
    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_user);

        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        email = findViewById(R.id.email);
        button = findViewById(R.id.button);

        final AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"production")
                .allowMainThreadQueries() //don't make this please, it's a bad practic
                .build();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 8/6/2019 save to database
                Log.d(TAG,"Onclick: first_name "+firstName.getText().toString()+ " lastname: "+lastName.getText().toString()+" email: "+email.getText().toString());
                User user = new User(firstName.getText().toString(),lastName.getText().toString(),email.getText().toString());
                db.userDao().insetAll(user);
                setResult(RESULT_OK);
                finish();
            }
        });
    }
}
