package com.example.myapplication.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.myapplication.fragment.DollarFragment;
import com.example.myapplication.fragment.EuroFragment;

public class MoneyPageAdapter extends FragmentStatePagerAdapter {

    public MoneyPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return 2;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new EuroFragment();
            case 1:
                return new DollarFragment();
            default:
                return new EuroFragment();
        }
    }
}
