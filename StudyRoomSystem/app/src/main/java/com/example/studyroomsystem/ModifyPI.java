package com.example.studyroomsystem;

import android.media.JetPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Hashtable;

public class ModifyPI extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    String name;
    String schoolid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_pi);
        final String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("users").child(userId);
        /*myRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot item : dataSnapshot.getChildren()) {
                    if (item.getKey().equals("name"))
                        name = item.getValue(String.class);

                    if (item.getKey().equals("schoolid"))
                        schoolid = item.getValue(String.class);
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                throw error.toException();
            }
        });*/

        Button btnModifyPI = (Button) findViewById(R.id.btnfinalmodify);
        btnModifyPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // EditText etCheckName = (EditText) findViewById(R.id.etcheck_name);
                //EditText etCheckSchoolId = (EditText) findViewById(R.id.etcheck_schoolid);
                EditText etModifyPasswd = (EditText) findViewById(R.id.etmodify_password);
                EditText etModifyRepasswd = (EditText) findViewById(R.id.etmodify_repassword);

                if (etModifyPasswd.getText().toString().equals(etModifyRepasswd.getText().toString())){
//                    Hashtable<String, Object> profile = new Hashtable<String, Object>();
//                    if (!etModifyName.getText().toString().isEmpty()) {
//                        profile.put("name",etModifyName.getText().toString());
//                    }
//                    if (!etModifySchoolnum.getText().toString().isEmpty()) {
//                        profile.put("schoolid",etModifySchoolnum.getText().toString());
//                    }
//                    mDatabase.updateChildren(profile);
/*
                    if (!etCheckSchoolId.getText().toString().equals(schoolid)) {
                        Toast.makeText(ModifyPI.this, "학번이 틀립니다. 다시 확인해 주세요", Toast.LENGTH_SHORT).show();
                    }
                    else if (!etCheckName.getText().toString().equals(name)) {
                        Toast.makeText(ModifyPI.this, "이름이 틀립니다. 다시 확인해 주세요.", Toast.LENGTH_SHORT).show();
                    }*/
                    if (etModifyPasswd.getText().toString().isEmpty()) {
                        Toast.makeText(ModifyPI.this, "비밀번호를 입력해 주세요.", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        user.updatePassword(etModifyPasswd.getText().toString());

                        Toast.makeText(ModifyPI.this, "비밀번호가 수정되었습니다.", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
                else {
                    Toast.makeText(ModifyPI.this, "비밀번호를 다시 확인해 주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button btnModifyCancel = (Button) findViewById(R.id.btnModifycancel);
        btnModifyCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
