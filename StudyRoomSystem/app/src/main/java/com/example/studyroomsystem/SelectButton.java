package com.example.studyroomsystem;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.studyroomsystem.InsideFragment;
import com.example.studyroomsystem.OutsideFragment;
import com.example.studyroomsystem.R;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

public class SelectButton extends AppCompatActivity {
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private Fragment insideFragment = new InsideFragment();
    private Fragment outsideFragment = new OutsideFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_button);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView_main_menu);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, outsideFragment).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        FragmentTransaction transaction = fragmentManager.beginTransaction();

                        // 어떤 메뉴 아이템이 터치되었는지 확인합니다.
                        switch (item.getItemId()) {

                            case R.id.menuitem_bottombar_inside:

                                transaction.replace(R.id.frame_layout, insideFragment).commit();
                                break;

                            case R.id.menuitem_bottombar_ouside:

                                transaction.replace(R.id.frame_layout, outsideFragment).commit();
                                break;

                            case R.id.menuitem_bottombar_settings:


                                return true;
                        }
                        return false;
                    }


                });


    }
}