package com.example.studyroomsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ManagerViewPage extends AppCompatActivity {


    private FirebaseAuth firebaseAuth;

    private DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("users");

    String name;
    String schoolid;
    String reserveUser = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_view_page);

        firebaseAuth = FirebaseAuth.getInstance();

        DatabaseReference myRef;
        DataSnapshot dataSnapshot;
        final TextView tvMVbuilding = (TextView) findViewById(R.id.tvMVbuilding);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot item : dataSnapshot.getChildren()) {
                    String reserve =item.child("reservation").getValue(String.class);
                    if(reserve != null && !reserve.equals("예약 취소")) {
                        reserveUser += reserve + " ";
                        reserveUser += item.child("name").getValue(String.class) + "\n";
                    }
                }
                tvMVbuilding.setText(reserveUser);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                throw error.toException();
            }
        });

    }

}
