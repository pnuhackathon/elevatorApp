package com.example.studyroomsystem;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.Context.MODE_PRIVATE;

// 서비스3 : 개인정보 확인, 수정, 회원가입 탈퇴
public class ProfileFragment extends Fragment{
    private FirebaseAuth firebaseAuth;

    private static final String TAG = "MainActivity";
    private TextView textViewUserName;
    private TextView textViewUserSchoolid;
    private DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("users");

    String name;
    String schoolid;
    String authority;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        final String userId = user.getUid();
        final DatabaseReference myRef;

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users").child(userId);

        textViewUserName = (TextView) view.findViewById(R.id.textViewUserName);
        textViewUserSchoolid = (TextView) view.findViewById(R.id.textViewUserSchoolid);

        Button btnModify = (Button) view.findViewById(R.id.btnModify);
        btnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), ModifyPI.class);
                startActivity(in);
            }
        });

        final Button btnManagerReservation = (Button) view.findViewById(R.id.btn_manager_reservation);
        btnManagerReservation.setVisibility(View.GONE);
        btnManagerReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                if (authority!=null && authority.equals("manager")) {
                    Intent in = new Intent(getActivity(), ManagerReservationActivity.class);
                    startActivity(in);
                } else {
                    Toast.makeText(getActivity(), "권한이 부족합니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        final Button btn_manager_view = (Button) view.findViewById(R.id.btn_manager_view);
        btn_manager_view.setVisibility(View.GONE);
        btn_manager_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), ManagerViewPage.class);
                startActivity(in);
            }
        });

        final Button btnGetOut = (Button) view.findViewById(R.id.btn_getout);
        btnGetOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                user.delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()) {
                                    Log.d(TAG, "User account deleted.");
                                }
                            }
                        });
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

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
                authority = dataSnapshot.child("authority").getValue(String.class);
                if (authority!=null && authority.equals("manager")) {
                    btnManagerReservation.setVisibility(View.VISIBLE);
                    btn_manager_view.setVisibility(View.VISIBLE);
                    btnGetOut.setVisibility(View.GONE);
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                throw error.toException();
            }
        });

        Button btnLogout = (Button) view.findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                SharedPreferences pref = getContext().getSharedPreferences( "pref" , MODE_PRIVATE);
                SharedPreferences.Editor ed = pref.edit();
                ed.remove("id");
                ed.remove("pw");
                ed.commit();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });






        return view;

    }
}
