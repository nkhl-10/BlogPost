package com.example.postblog.Adapter;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.content.Intent;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.postblog.Fragment.Bottomsheet;
import com.example.postblog.Fragment.Home;
import com.example.postblog.Fragment.comment;
import com.example.postblog.MainActivity;
import com.example.postblog.Method.Post;
import com.example.postblog.R;
import com.example.postblog.content;
import com.example.postblog.profile;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class HomePostAdapter extends RecyclerView.Adapter<HomePostAdapter.Myclass> {
    
    Context context;
    ArrayList<Post>arrayList;

    public HomePostAdapter(Context context, ArrayList<Post> arrayList){
        this.context=context;
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.homepostpdapter,parent,false);
        return new Myclass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HomePostAdapter.Myclass holder, int i) {

        holder.ccontent.setText(arrayList.get(i).getContent());
        holder.title.setText(arrayList.get(i).getTitle());
        holder.uname.setText(arrayList.get(i).getUserName());

        Picasso.get().load(arrayList.get(i).getPoster()).into(holder.poster);
//        Picasso.get().load(arrayList.get(i).getDp()).into(holder.usericon);

        holder.count.setText(arrayList.get(i).getLikes() +" Likes And "+ arrayList.get(i).getComments() +" Comments");

        String timeago=calculateago(arrayList.get(i).getCreatedTime());
        holder.time.setText(timeago);



    }

    @NonNull
    private String calculateago(String createdTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss");
        try {
            long time = sdf.parse(createdTime).getTime();
            long now = System.currentTimeMillis();
            CharSequence ago =
                    DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS);
            return  ago+"";
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class Myclass extends RecyclerView.ViewHolder{

    TextView title,ccontent;
    ImageView usericon,commenticon,poster;
    TextView uname,count,time;
    ImageView cmtbtn;
    CardView cardView;

        public Myclass(@NonNull View v){
            super(v);

            title =v.findViewById(R.id.posttitle);
            ccontent=v.findViewById(R.id.postcontent);
            usericon=v.findViewById(R.id.usericon);
            commenticon=v.findViewById(R.id.cicon);
            poster=v.findViewById(R.id.poster);
            uname=v.findViewById(R.id.username);
            count=v.findViewById(R.id.commentandlikecount);
//            cmtbtn=v.findViewById(R.id.commentbtn);
            time=v.findViewById(R.id.ptime);
            cardView=v.findViewById(R.id.postcard);


            usericon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, profile.class);
                    intent.putExtra("id" , arrayList.get(getAdapterPosition()).getPostId());
                    context.startActivity(intent);

                }
            });
            uname.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, profile.class);
                    intent.putExtra("id" , arrayList.get(getAdapterPosition()).getUserId());
                    context.startActivity(intent);

                }
            });

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, content.class);
                    intent.putExtra("id" , arrayList.get(getAdapterPosition()).getUserId());
                    intent.putExtra("postid" , arrayList.get(getAdapterPosition()).getPostId());
                    context.startActivity(intent);
                }
            });

//            cmtbtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    Intent intent=new Intent(view.getContext(), comment.class);
//                    intent.putExtra("postid",arrayList.get(getAdapterPosition()).getPostId());
//                    view.getContext().startActivity(intent);
////
////                    comment com=new comment();
////                    com.dismiss();
//               }
//            });


        }

    }
}
