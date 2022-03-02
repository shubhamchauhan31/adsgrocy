package com.example.payucart.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.payucart.R;
import com.example.payucart.activity.ShareActivity;
import com.example.payucart.bottomTabs.ui.home.HomeFragment;
import com.example.payucart.fragment.PackageFragments;
import com.example.payucart.fragment.ProfileFragment;
import com.example.payucart.fragment.ShareFragment;
import com.example.payucart.fragment.WalletFragemts;
import com.halzhang.android.library.BottomTabFragmentPagerAdapter;
import com.halzhang.android.library.BottomTabPagerAdapter;

public class MyPagerAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;

    public MyPagerAdapter(@NonNull FragmentManager fm, Context myContext, int totalTabs) {
        super(fm);
        this.myContext = myContext;
        this.totalTabs = totalTabs;
    }


    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                HomeFragment homeFragment = new HomeFragment();
                return homeFragment;
            case 1:
                ShareFragment shareFragment=new ShareFragment();
                return  shareFragment;
            case 2:
                PackageFragments packageFragments=new PackageFragments();
                return packageFragments;
            case 3:
                ProfileFragment profileFragment=new ProfileFragment();
                return profileFragment;

            default:
                return null;
        }
    }


    @Override
    public int getCount() {
        return totalTabs;
    }
}
