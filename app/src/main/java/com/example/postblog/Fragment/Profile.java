package com.example.postblog.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.postblog.API.APIClient;
import com.example.postblog.API.APIinterface;
import com.example.postblog.Adapter.ViewPageAdapter;
import com.example.postblog.Method.User;
import com.example.postblog.Method.UserResult;
import com.example.postblog.R;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Profile extends Fragment {

    public Profile() {
        // Required empty public constructor
    }
    boolean show =true;
    TabLayout tabLayout;
    ViewPager viewPager;
    ArrayList<User>arrayList;
    TextView post,follower,following,bio;
    ImageView dp;
    Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_profile, container, false);

        tabLayout = v.findViewById(R.id.tablayout);
        viewPager = v.findViewById(R.id.pager);
        arrayList=new ArrayList<>();


        SharedPreferences pref = requireContext().getSharedPreferences( "UserData", Context.MODE_PRIVATE);
        String id= pref.getString("id",null);
        fetchdata(id);

        ViewPageAdapter viewPageAdapter=new ViewPageAdapter(requireActivity().getSupportFragmentManager());
        viewPageAdapter.addFragment(new PostPager(id),"Postd");
        viewPageAdapter.addFragment(new LikedPostPager(id),"Liked");
        viewPageAdapter.addFragment(new SavedPostAdapter(id),"Saved");
        viewPager.setAdapter(viewPageAdapter);
        tabLayout.setupWithViewPager(viewPager);

        post=v.findViewById(R.id.postcount);
        follower=v.findViewById(R.id.follower);
        following=v.findViewById(R.id.following);
        bio=v.findViewById(R.id.bio);
        dp=v.findViewById(R.id.dp);
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




        toolbar =v.findViewById(R.id.toolbarprofile);
        AppCompatActivity appCompatActivity=(AppCompatActivity)getActivity();
        appCompatActivity.setSupportActionBar(toolbar);



        return v;
    }

    public void fetchdata(String id) {

        APIinterface apIinterface= APIClient.getclient().create(APIinterface.class);
        Call<UserResult>call=apIinterface.fetchprofile(id);
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
    public void onCreateOptionsMenu( Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolmenuprofile, menu);
        super.onCreateOptionsMenu(menu,inflater);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id==R.id.profilemenu){
            Bottomsheet b =new Bottomsheet();
            b.show(requireActivity().getSupportFragmentManager(),"tag");
        }


        return super.onOptionsItemSelected(item);
    }


}