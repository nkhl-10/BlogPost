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
import com.example.postblog.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class postlistadapter extends RecyclerView.Adapter<postlistadapter.Myclass> {
    Context context;
    ArrayList<Post>arrayList;

    public postlistadapter(Context context,ArrayList<Post>arrayList) {
         this.context=context;
         this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public postlistadapter.Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.postlist,parent,false);
       return new Myclass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull postlistadapter.Myclass holder, int position) {
        Picasso.get().load(arrayList.get(position).getPoster()).into(holder.imageView);
        holder.title.setText(arrayList.get(position).getTitle());
        holder.content.setText(arrayList.get(position).getContent());
        holder.count.setText(arrayList.get(position).getLikes() + " Likes And " + arrayList.get(position).getComments() + " Comments");

    }

        @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Myclass extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title,content,count;
        public Myclass(@NonNull View v) {
            super(v);
            imageView=v.findViewById(R.id.poster2);
            title=v.findViewById(R.id.text1);
            content=v.findViewById(R.id.text2);
            count=v.findViewById(R.id.text3);


        }
    }
}
