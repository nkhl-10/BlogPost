package com.example.postblog.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.postblog.Method.Post;
import com.example.postblog.Method.PostResult;
import com.example.postblog.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Callback;

public class SubPostAdapter extends RecyclerView.Adapter <SubPostAdapter.Myclass>{

    Context context;
    ArrayList<Post> arrayList;

     public   SubPostAdapter(Context context, ArrayList<Post> arrayList){
        this.context=context;
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.subpost,parent,false);
        return new Myclass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SubPostAdapter.Myclass holder, int i) {
        holder.t1.setText(arrayList.get(i).getContent1());
        holder.t2.setText(arrayList.get(i).getContent2());

        Picasso.get().load(arrayList.get(i).getImg1()).into(holder.img1);
        Picasso.get().load(arrayList.get(i).getImg2()).into(holder.img2);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Myclass extends RecyclerView.ViewHolder {

        ImageView img1,img2;
        TextView t1,t2;

        public Myclass(@NonNull View v) {
            super(v);
            img1=v.findViewById(R.id.subimage1);
            t1=v.findViewById(R.id.subtxt1);
            img2=v.findViewById(R.id.subimage2);
            t2=v.findViewById(R.id.subtxt2);

        }
    }
}
