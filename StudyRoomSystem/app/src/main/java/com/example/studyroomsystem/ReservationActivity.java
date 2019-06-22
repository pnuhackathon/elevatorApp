package com.example.studyroomsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.Hashtable;

public class ReservationActivity extends AppCompatActivity {

    public static String RoomName;
    public static String[] NameArray;
    int capacity;
    int current;
    DatabaseReference myRef;
    FirebaseAuth mAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        Intent in = getIntent();
        RoomName = in.getStringExtra("RoomName");
        NameArray = RoomName.split("#");
        String SelectBuildingName;

        TextView BuildingName = findViewById(R.id.buildingname);
        BuildingName.setText(NameArray[0]);

        TextView StudyRoomName = findViewById(R.id.studyroomname);
        StudyRoomName.setText(NameArray[1]);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("building").child(NameArray[0]).child("Class" + NameArray[1]); // 건물명, 강의실명
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot item : dataSnapshot.getChildren()) {
                    if (item.getKey().equals("capacity"))
                        capacity = item.getValue(Integer.class);

                    if (item.getKey().equals("current"))
                        current = item.getValue(Integer.class);
                }
                //Toast.makeText(ReservationActivity.this, "capacity: " + capacity + " current: " + current, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(DatabaseError error) {
                throw error.toException();
            }
        });

        Button btnReservation = findViewById(R.id.btnreservation);
        btnReservation.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (capacity > current) {
                    Hashtable<String, Object> profile = new Hashtable<String, Object>();
                    profile.put("capacity", capacity);
                    profile.put("current", current+1);
                    myRef.updateChildren(profile);

                    FirebaseDatabase.getInstance().getReference("users").child(user.getUid()).child("reservation").setValue(RoomName);

                    Toast.makeText(ReservationActivity.this, "예약 완료", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    Toast.makeText(ReservationActivity.this, "정원 초과", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
