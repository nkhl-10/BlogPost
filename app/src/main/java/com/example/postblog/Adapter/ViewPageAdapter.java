package com.example.postblog.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.postblog.Fragment.LikedPostPager;
import com.example.postblog.Fragment.PostPager;
import com.example.postblog.Fragment.SavedPostAdapter;

import java.util.ArrayList;

public class ViewPageAdapter extends FragmentStatePagerAdapter {

    ArrayList<Fragment> arrayList=new ArrayList<>();
    ArrayList<String> arrayList1=new ArrayList<>();
    String id;
    public ViewPageAdapter(@NonNull FragmentManager fm) {

        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return  arrayList.get(position);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }


    public void addFragment(Fragment fragment, String name){
        arrayList.add(fragment);
        arrayList1.add(name);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
            return arrayList1.get(position);
    }
}
