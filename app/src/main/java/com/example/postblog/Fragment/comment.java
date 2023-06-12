package com.example.postblog.Fragment;

import static android.content.Intent.getIntent;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.postblog.API.APIClient;
import com.example.postblog.API.APIinterface;
import com.example.postblog.Adapter.commentfrag;
import com.example.postblog.Method.Post;
import com.example.postblog.Method.PostResult;
import com.example.postblog.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class comment extends BottomSheetDialogFragment {


    public comment() {    }

    ArrayList<Post> arrayList;
    RecyclerView recyclerView;

//    Intent intent =  getActivity().getIntent();
//    String postid = intent.getStringExtra("bookid");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_comment, container, false);

        recyclerView=v.findViewById(R.id.commentfrag);
        arrayList=new ArrayList<>();

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

//
 //       APIinterface apIinterface= APIClient.getclient().create(APIinterface.class);
//        Call<PostResult> call=apIinterface.allcomment(postid);
//        call.enqueue(new Callback<PostResult>() {
//            @Override
//            public void onResponse(Call<PostResult> call, Response<PostResult> response) {
//                try {
//                    arrayList= (ArrayList<Post>) response.body().getPost();
//                    commentfrag Commentfrag=new commentfrag(getContext(),arrayList);
//                    recyclerView.setAdapter(Commentfrag);
//
//                }catch (Exception e){
//                    Toast.makeText(getContext(), ""+e, Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<PostResult> call, Throwable t) {
//
//            }
//        });

        return v;
    }
}