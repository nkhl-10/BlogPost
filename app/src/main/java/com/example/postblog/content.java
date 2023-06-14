package com.example.postblog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.postblog.API.APIClient;
import com.example.postblog.API.APIinterface;
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
    TextView count;
    ImageView like,save,comment;
    boolean likebool=false;
    int likes,comments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String postid = intent.getStringExtra("postid");



          recyclerView=findViewById(R.id.postcontentlist);
        arrayList=new ArrayList<>();
        like=findViewById(R.id.likebtn);
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(likebool) {
                     like.setImageResource(R.drawable.likes);
                     likes--;
                     count.setText(likes +" Likes And "+ comments +" Comments");
                     likebool=false;
                    APIinterface apIinterface= APIClient.getclient().create(APIinterface.class);
                    Call<PostResult> call=apIinterface.deslike(id,postid);
                    call.enqueue(new Callback<PostResult>() {
                        @Override
                        public void onResponse(Call<PostResult> call, Response<PostResult> response) {
                            arrayList=(ArrayList<Post>) response.body().getPost();
                        }

                        @Override
                        public void onFailure(Call<PostResult> call, Throwable t) {

                        }
                    });
                }else if(!likebool){
                    like.setImageResource(R.drawable.liked);
                    likes++;
                    count.setText(likes +" Likes And "+ comments +" Comments");
                    likebool=true;
                    APIinterface apIinterface= APIClient.getclient().create(APIinterface.class);
                    Call<PostResult> call=apIinterface.setlike(id,postid);
                    call.enqueue(new Callback<PostResult>() {
                        @Override
                        public void onResponse(Call<PostResult> call, Response<PostResult> response) {
                            arrayList=(ArrayList<Post>) response.body().getPost();
                        }

                        @Override
                        public void onFailure(Call<PostResult> call, Throwable t) {

                        }
                    });
                }
            }
        });

        save=findViewById(R.id.savebtn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                APIinterface apIinterface = APIClient.getclient().create(APIinterface.class);
                Call<PostResult> call = apIinterface.savepost(id, postid);
                call.enqueue(new Callback<PostResult>() {
                    @Override
                    public void onResponse(Call<PostResult> call, Response<PostResult> response) {
                        arrayList = (ArrayList<Post>) response.body().getPost();
                        Toast.makeText(content.this, "This Post Save Sucessfully", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<PostResult> call, Throwable t) {

                    }
                });
            }}
        );

        Refresh(id);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        ImageView dp=findViewById(R.id.cicon);
        ImageView poster=findViewById(R.id.cposter);
        TextView uname=findViewById(R.id.cusername);
        TextView title=findViewById(R.id.ctitle);
        count=findViewById(R.id.ccount);
        TextView content=findViewById(R.id.ccontent);
        APIinterface apIinterface= APIClient.getclient().create(APIinterface.class);
        Call<PostResult> call=apIinterface.postid(id);
        call.enqueue(new Callback<PostResult>() {
            @Override
            public void onResponse(Call<PostResult> call, Response<PostResult> response) {
                arrayList=(ArrayList<Post>) response.body().getPost();
//
//                Picasso.get().load(arrayList.get(0).getDp()).into(dp);
                Picasso.get().load(arrayList.get(0).getPoster()).into(poster);
                uname.setText(arrayList.get(0).getUserName());
                title.setText(arrayList.get(0).getTitle());
                content.setText(arrayList.get(0).getContent());
                likes= Integer.parseInt(arrayList.get(0).getLikes());
                comments= Integer.parseInt(arrayList.get(0).getComments());
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

                showlike(arrayList.get(0).getUserId(),arrayList.get(0).getPostId());

            }

            @Override
            public void onFailure(Call<PostResult> call, Throwable t) {

            }
        });

    }

    public void showlike(String id,String postid){
        APIinterface apIinterface= APIClient.getclient().create(APIinterface.class);
        Call<PostResult> call=apIinterface.postlikes(id,postid);
        call.enqueue(new Callback<PostResult>() {
            @Override
            public void onResponse(Call<PostResult> call, Response<PostResult> response) {
                arrayList=(ArrayList<Post>) response.body().getPost();
              boolean  likeboo = Boolean.parseBoolean(arrayList.get(0).getaction());
                if(likeboo) {
                    like.setImageResource(R.drawable.likes);
                    count.setText(likes +" Likes And "+ comments +" Comments");
                    likebool=true;
                }else if(!likeboo){
                    like.setImageResource(R.drawable.liked);
                    count.setText(likes +" Likes And "+ comments +" Comments");
                    likebool=false;
                }
            }

            @Override
            public void onFailure(Call<PostResult> call, Throwable t) {

            }
        });
    }



}