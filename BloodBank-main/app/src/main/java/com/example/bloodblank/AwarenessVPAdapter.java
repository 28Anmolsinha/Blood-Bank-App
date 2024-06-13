package com.example.bloodblank;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class AwarenessVPAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> listOfFragments = new ArrayList<>();

    public AwarenessVPAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment) {
        listOfFragments.add(fragment);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return listOfFragments.get(position);
    }

    @Override
    public int getCount() {
        return listOfFragments.size();
    }
}
