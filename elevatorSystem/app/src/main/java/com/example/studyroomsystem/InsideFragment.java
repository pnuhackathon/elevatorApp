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

public class InsideFragment extends Fragment {
    private Socket socket;
    {
        try {
            socket = IO.socket("http://15.164.68.143:9000");

        } catch (URISyntaxException e) {
            Log.d("tag", "ddd34");
            throw new RuntimeException(e);
        }
    }
    public InsideFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inside,null);
        socket.connect();
        ImageButton one_btn = (ImageButton)view.findViewById(R.id.in_one);
        one_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                socket.emit("normal", "1");
            }
        });

        ImageButton two_btn = (ImageButton)view.findViewById(R.id.in_two);
        two_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                socket.emit("normal", "2");
            }
        });

        ImageButton three_btn = (ImageButton)view.findViewById(R.id.in_three);
        three_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                socket.emit("normal", "3");
            }
        });

        ImageButton four_btn = (ImageButton)view.findViewById(R.id.in_four);
        four_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                socket.emit("normal", "4");
            }
        });


        return view;
    }

}
