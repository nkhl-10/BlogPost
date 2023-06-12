package com.example.postblog.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.postblog.API.APIClient;
import com.example.postblog.API.APIinterface;
import com.example.postblog.Adapter.postlistadapter;
import com.example.postblog.Method.Post;
import com.example.postblog.Method.PostResult;
import com.example.postblog.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SavedPostAdapter extends Fragment {

    ArrayList<Post> arrayList=new ArrayList<>();
    RecyclerView recyclerView;
    String id;
    public SavedPostAdapter( String id) {
        this.id=id;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_saved_post_adapter, container, false);
        recyclerView=v.findViewById(R.id.postsaveadapter);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        Refresh("1");
        return v;
    }   public void Refresh(String id) {
        APIinterface apIinterface= APIClient.getclient().create(APIinterface.class);
        Call<PostResult> call=apIinterface.postlistsave(id);
        call.enqueue(new Callback<PostResult>() {
            @Override
            public void onResponse(Call<PostResult> call, Response<PostResult> response) {
                arrayList=(ArrayList<Post>) response.body().getPost();
                postlistadapter homePostAdapter=new postlistadapter(getContext(),arrayList);
                recyclerView.setAdapter(homePostAdapter);


            }

            @Override
            public void onFailure(Call<PostResult> call, Throwable t) {

            }
        });

    }

}