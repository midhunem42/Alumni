package com.android.alumni.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.alumni.R;
import com.android.alumni.model.Post;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AluminAdapter extends RecyclerView.Adapter<AluminAdapter.AluminViewholder> {

    private Context context;
    private List<Post> postList;

    public AluminAdapter(Context context, List<Post> postList) {
        this.context = context;
        this.postList = postList;
    }

    @NonNull
    @Override
    public AluminViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.posts, viewGroup, false);
        return new AluminViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AluminViewholder aluminViewholder, int i) {
        Post post=postList.get(i);
        aluminViewholder.title.setText(post.getTitle());
        aluminViewholder.desc.setText(post.getDesc());
        aluminViewholder.username.setText(post.getDesc());
        aluminViewholder.time.setText(post.getTime());
        Picasso.get().load(post.getImageurl()).into(aluminViewholder.imageView);

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }
    public class AluminViewholder extends RecyclerView.ViewHolder {
        TextView title,desc,username,time;
        ImageView imageView;
        public AluminViewholder(@NonNull View itemView) {
            super(itemView);
           title=itemView.findViewById(R.id.postTitles);
           desc=itemView.findViewById(R.id.postDescr);
           time=itemView.findViewById(R.id.timePost);
           username=itemView.findViewById(R.id.usernamePost);
           imageView=itemView.findViewById(R.id.postImage);
        }
    }
}
