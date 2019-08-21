package com.example.studyroomsystem;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;


public class SelectButton extends AppCompatActivity {
    private Socket socket;
    {
        try {
            socket = IO.socket("http://15.164.68.143:9000");
            Log.d("tag", "ddd!?");
        } catch (URISyntaxException e) {
            Log.d("tag", "ddd?");
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_button);
        socket.connect();

        /*ImageButton special_btn = (ImageButton)findViewById(R.id.up);
        special_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                socket.emit("SpecialButton", "enter");
                Log.d("tag", "ddddd");
            }
        });*/

        Button one_btn = (Button)findViewById(R.id.out_one);
        one_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                socket.emit("SpecialCall", "1");
            }
        });

        Button two_btn = (Button)findViewById(R.id.out_two);
        two_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                socket.emit("SpecialCall", "2");
            }
        });

        Button three_btn = (Button)findViewById(R.id.out_three);
        three_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                socket.emit("SpecialCall", "3");
            }
        });

        Button four_btn = (Button)findViewById(R.id.out_four);
        four_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                socket.emit("SpecialCall", "4");
            }
        });

    }
}
