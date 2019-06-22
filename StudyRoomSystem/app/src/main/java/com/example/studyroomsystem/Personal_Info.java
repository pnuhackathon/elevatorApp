package com.example.studyroomsystem;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Personal_Info extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    private TextView textViewUserName;
    private TextView textViewUserSchoolid;
    private DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("users");

    String name;
    String schoolid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String userId = user.getUid();
        DatabaseReference myRef;
        DataSnapshot dataSnapshot;

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users").child(userId);
        textViewUserName = (TextView) findViewById(R.id.textViewUserName);
        textViewUserSchoolid = (TextView) findViewById(R.id.textViewUserSchoolid);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot item : dataSnapshot.getChildren()) {
                    if (item.getKey().equals("name"))
                        name = item.getValue(String.class);

                    if (item.getKey().equals("schoolid"))
                        schoolid = item.getValue(String.class);
                }
                textViewUserName.setText("이름: "+name);
                textViewUserSchoolid.setText("학번: "+schoolid);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                throw error.toException();
            }
        });
        Button btnModify = (Button) findViewById(R.id.btnModify);
        btnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Personal_Info.this, ModifyPI.class);
                startActivity(in);
            }
        });


    }


}
