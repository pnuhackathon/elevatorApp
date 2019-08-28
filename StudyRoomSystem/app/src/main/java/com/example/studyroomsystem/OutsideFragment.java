package com.example.studyroomsystem;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

public class OutsideFragment extends Fragment {
    private Socket socket;
    {
        try {
            socket = IO.socket("http://15.164.68.143:9000");
            Log.d("tag", "ddd!");
        } catch (URISyntaxException e) {

            throw new RuntimeException(e);
        }
    }
    public OutsideFragment() {
        // Required empty public constructor
    }
    int floor = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_outside,null);
        socket.connect();
        ImageButton one_btn = (ImageButton)view.findViewById(R.id.out_one);
        one_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                floor = 1;
            }
        });

        ImageButton two_btn = (ImageButton)view.findViewById(R.id.out_two);
        two_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                floor = 2;
            }
        });

        ImageButton three_btn = (ImageButton)view.findViewById(R.id.out_three);
        three_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                floor = 3;
            }
        });

        ImageButton four_btn = (ImageButton)view.findViewById(R.id.out_four);
        four_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                floor = 4;
            }
        });

        ImageButton up_btn = (ImageButton)view.findViewById(R.id.up);
        up_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                if (floor != 0) {
                    String s = "u" + Integer.toString(floor);
                    socket.emit("normal", s);
                    floor = 0;

                }
            }
        });

        ImageButton down = (ImageButton)view.findViewById(R.id.down);
        down.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                if (floor != 0) {
                    String s = "d" + Integer.toString(floor);
                    socket.emit("normal", s);
                    floor = 0;
                }
            }
        });

        return view;
    }

}
