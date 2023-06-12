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

public class commentfrag extends RecyclerView.Adapter<commentfrag.Myclass> {

    Context context;
    ArrayList<Post>arrayList;

    public commentfrag(Context context, ArrayList<Post> arrayList){
        this.context=context;
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.commentadapter,parent,false);
        return new Myclass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull commentfrag.Myclass holder, int i) {

        holder.Comment.setText(arrayList.get(i).getComment());
        holder.time.setText(arrayList.get(i).getCommentTime());

        Picasso.get().load(arrayList.get(i).getDp()).into(holder.icon);




    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    class Myclass extends RecyclerView.ViewHolder{

        ImageView icon;
        TextView Comment,time;

        public Myclass(@NonNull View v){
            super(v);

             icon=v.findViewById(R.id.cicon);
             Comment=v.findViewById(R.id.commenttitle);
                time=v.findViewById(R.id.ctime);



        }

    }
}
