package com.android.alumni.Alumni;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.android.alumni.R;
import com.android.alumni.adapter.AluminAdapter;
import com.android.alumni.adapter.StudentRecyclerAdapter;
import com.android.alumni.model.Post;
import com.android.alumni.model.StudentModel;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AluminiPosts extends AppCompatActivity {
    Button create;
    List<Post> posts  = new ArrayList<>();
    RecyclerView recyclerView;
    AluminAdapter adapter ;
    DatabaseReference mDatabase ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumini_posts);
        create=findViewById(R.id.createPost);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AluminiPosts.this,AluminCreate.class));
            }
        });
        mDatabase= FirebaseDatabase.getInstance().getReference("posts");
        recyclerView = (RecyclerView) findViewById(R.id.Aluminpostrecycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                posts.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    Post post =postSnapshot.getValue(Post.class);
                    String title=post.getTitle();
                    String desc =post.getDesc();
                    String time=post.getTime();
                    String username=post.getUsername();
                    String imageurl =post.getImageurl();
                    Post postData=new Post();
                    postData.setTitle(title);
                    postData.setDesc(desc);
                    postData.setImageurl(imageurl);
                    postData.setUsername(username);
                    postData.setTime(time);

                    posts.add(postData);
                    Log.d("login", "Student added : "+postData.toString() );
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        adapter=new AluminAdapter(this,posts);

        recyclerView.setAdapter(adapter);

    }
}
