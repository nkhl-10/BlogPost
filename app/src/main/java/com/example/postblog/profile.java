package com.example.postblog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.postblog.API.APIClient;
import com.example.postblog.API.APIinterface;
import com.example.postblog.Adapter.ViewPageAdapter;
import com.example.postblog.Fragment.Bottomsheet;
import com.example.postblog.Fragment.LikedPostPager;
import com.example.postblog.Fragment.PostPager;
import com.example.postblog.Fragment.SavedPostAdapter;
import com.example.postblog.Method.User;
import com.example.postblog.Method.UserResult;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class profile extends AppCompatActivity {

    boolean show =true;
    TabLayout tabLayout;
    ViewPager viewPager;
    ArrayList<User> arrayList;
    TextView post,follower,following,bio;
    ImageView dp;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        arrayList=new ArrayList<>();
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.pager);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        fetchdata(id);

        ViewPageAdapter viewPageAdapter=new ViewPageAdapter(getSupportFragmentManager());
        viewPageAdapter.addFragment(new PostPager(id),"Postd");
        viewPageAdapter.addFragment(new LikedPostPager(id),"Liked");
        viewPageAdapter.addFragment(new SavedPostAdapter(id),"Saved");
        viewPager.setAdapter(viewPageAdapter);
        tabLayout.setupWithViewPager(viewPager);

        post=findViewById(R.id.postcount);
        follower=findViewById(R.id.follower);
        following=findViewById(R.id.following);
        bio=findViewById(R.id.bio);
        dp=findViewById(R.id.dp);
        bio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(show) {
                    bio.setMaxLines(6);
                    show=false;
                }else if(!show){
                    bio.setMaxLines(3);
                    show=true;
                }
            }
        });
        toolbar =findViewById(R.id.toolbarprofile);
        setSupportActionBar(toolbar);

    }

    public void fetchdata(String id) {
        APIinterface apIinterface= APIClient.getclient().create(APIinterface.class);
        Call<UserResult> call=apIinterface.fetchprofile(id);
        call.enqueue(new Callback<UserResult>() {
            @Override
            public void onResponse(Call<UserResult> call, Response<UserResult> response) {
                arrayList= (ArrayList<User>) response.body().getPost();
                follower.setText(arrayList.get(0).getFollower());
                following.setText(arrayList.get(0).getFollowing());
                bio.setText(arrayList.get(0).getBio());
                post.setText(arrayList.get(0).getPosts());
                toolbar.setTitle("@"+arrayList.get(0).getUserName());
                Picasso.get().load(arrayList.get(0).getDp()).into(dp);
            }

            @Override
            public void onFailure(Call<UserResult> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.toolmenuprofile,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}