package com.example.postblog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.postblog.API.APIClient;
import com.example.postblog.API.APIinterface;
import com.example.postblog.Adapter.HomePostAdapter;
import com.example.postblog.Adapter.SubPostAdapter;
import com.example.postblog.Method.Post;
import com.example.postblog.Method.PostResult;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class content extends AppCompatActivity {

    ArrayList<Post> arrayList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        Toast.makeText(this, ""+id, Toast.LENGTH_SHORT).show();

        recyclerView=findViewById(R.id.postcontentlist);
        arrayList=new ArrayList<>();

        Refresh(id);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        ImageView dp=findViewById(R.id.cicon);
        ImageView poster=findViewById(R.id.cposter);
        TextView uname=findViewById(R.id.cusername);
        TextView title=findViewById(R.id.ctitle);
        TextView count=findViewById(R.id.ccount);
        TextView content=findViewById(R.id.ccontent);
        APIinterface apIinterface= APIClient.getclient().create(APIinterface.class);
        Call<PostResult> call=apIinterface.postid(id);
        call.enqueue(new Callback<PostResult>() {
            @Override
            public void onResponse(Call<PostResult> call, Response<PostResult> response) {
                arrayList=(ArrayList<Post>) response.body().getPost();

                Picasso.get().load(arrayList.get(0).getDp()).into(dp);
                Picasso.get().load(arrayList.get(0).getPoster()).into(poster);
                uname.setText(arrayList.get(0).getUserName());
                title.setText(arrayList.get(0).getTitle());
                content.setText(arrayList.get(0).getContent());
                count.setText(arrayList.get(0).getLikes() +" Likes And "+ arrayList.get(0).getComments() +" Comments");

            }

            @Override
            public void onFailure(Call<PostResult> call, Throwable t) {

            }
        });
    }

    public void Refresh(String id) {
        APIinterface apIinterface= APIClient.getclient().create(APIinterface.class);
        Call<PostResult> call=apIinterface.contentlist(id);
        call.enqueue(new Callback<PostResult>() {
            @Override
            public void onResponse(Call<PostResult> call, Response<PostResult> response) {
                arrayList=(ArrayList<Post>) response.body().getPost();
                SubPostAdapter subPostAdapter=new SubPostAdapter(content.this,arrayList);
                recyclerView.setAdapter(subPostAdapter);

            }

            @Override
            public void onFailure(Call<PostResult> call, Throwable t) {

            }
        });

    }
}