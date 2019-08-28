package com.example.studyroomsystem;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPWD extends AppCompatActivity {

    EditText etMail;
    Button btnSendMail;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pwd);

        etMail = findViewById(R.id.etEmail);
        btnSendMail = findViewById(R.id.btnSendEmail);

        firebaseAuth = FirebaseAuth.getInstance();

        btnSendMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.sendPasswordResetEmail(etMail.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(ResetPWD.this,
                                    "비밀번호 재설정 메일이 전송 되었습니다.", Toast.LENGTH_LONG).show();
                            ResetPWD.this.finish();
                        }
                        else { // 등록된 이메일이 아닌 경우
                            Toast.makeText(ResetPWD.this,
                                    task.getException().getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }
                });
            }
        });
    }
}