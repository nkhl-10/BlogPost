package com.example.postblog.Fragment;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.postblog.API.APIClient;
import com.example.postblog.API.APIinterface;
import com.example.postblog.Adapter.HomePostAdapter;
import com.example.postblog.Method.Post;
import com.example.postblog.Method.PostResult;
import com.example.postblog.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Home extends Fragment {

    ArrayList<Post> arrayList;
    RecyclerView recyclerView;

    public Home() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_home, container, false);


        recyclerView=v.findViewById(R.id.homefrag);
        arrayList=new ArrayList<>();

        Refresh();

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        //shift
//        EditText  editText=v.findViewById(R.id.comment);
//        ImageView imageView=v.findViewById(R.id.commentsent);
//
//        if (editText.getText().toString().trim().length()>0){
//            imageView.setVisibility(View.VISIBLE);
//        }else{
//            imageView.setVisibility(View.VISIBLE);
//            editText.setWidth(100);
//        }
//        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                ImageView imageView=v.findViewById(R.id.compic);
//                imageView.setVisibility(View.GONE);
//            }
//        });


        return v;

    }

    public void Refresh() {
        APIinterface apIinterface=APIClient.getclient().create(APIinterface.class);
        Call<PostResult>call=apIinterface.getall();
        call.enqueue(new Callback<PostResult>() {
            @Override
            public void onResponse(Call<PostResult> call, Response<PostResult> response) {
                arrayList=(ArrayList<Post>) response.body().getPost();
                HomePostAdapter homePostAdapter=new HomePostAdapter(getContext(),arrayList);
                recyclerView.setAdapter(homePostAdapter);

            }

            @Override
            public void onFailure(Call<PostResult> call, Throwable t) {

            }
        });

    }



}