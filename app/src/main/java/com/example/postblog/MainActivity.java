package com.example.postblog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MenuItem;

import com.example.postblog.Fragment.Add_Post;
import com.example.postblog.Fragment.Explore;
import com.example.postblog.Fragment.Home;
import com.example.postblog.Fragment.Notification;
import com.example.postblog.Fragment.Profile;
import com.example.postblog.Fragment.comment;
import com.example.postblog.Method.Post;
import com.example.postblog.Method.PostResult;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;
    Vibrator vibrator;
    AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mAdView = findViewById(R.id.adView);
        MobileAds.initialize(this);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        bottomNavigation = findViewById(R.id.bottomNavigation);
        vibrator =(Vibrator)  getSystemService(Context.VIBRATOR_SERVICE);

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        fragment = new Home();
                        vibrator.vibrate(50);
                        break;
                    case R.id.navigation_search:
                        fragment = new Explore();
                        vibrator.vibrate(50);
                        break;
                    case R.id.navigation_Add_Post:
                        fragment = new Add_Post();
                        vibrator.vibrate(50);
                        break;
                    case R.id.navigation_notifications:
                        fragment = new Notification();
                        vibrator.vibrate(50);
                        break;
                    case R.id.navigation_profile:
                        fragment = new Profile();
                        vibrator.vibrate(50);
                        break;
                    default:
                        return false;
                }
                showFragment(fragment);
                return true;
            }
        });

        // Show the default fragment
        showFragment(new Home());


        //pager




    }


    private void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        // Check if the current item is not the first item
        if (bottomNavigation.getSelectedItemId() != R.id.navigation_home) {
            // Select the first item
            bottomNavigation.setSelectedItemId(R.id.navigation_home);
        } else {
            // If the first item is already selected, exit the app
            super.onBackPressed();
        }
    }

}