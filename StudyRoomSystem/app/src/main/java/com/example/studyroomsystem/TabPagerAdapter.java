package com.example.studyroomsystem;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

// 페이저 액티비티 상단 탭
public class TabPagerAdapter extends FragmentStatePagerAdapter {

    private int tabCount;

    public TabPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                BuildingFragment tabFragment0 = new BuildingFragment();
                return tabFragment0;
            case 1:
                MyReservationFragment tabFragment1 = new MyReservationFragment();
                return tabFragment1;
            case 2:
                ProfileFragment tabFragment2 = new ProfileFragment();
                return tabFragment2;

            default:
                 return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
