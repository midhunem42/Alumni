package com.android.alumni.Student;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.alumni.R;
import com.android.alumni.adapter.AluminAdapter;
import com.android.alumni.model.Post;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StudentViewPost extends AppCompatActivity {
    List<Post> posts  = new ArrayList<>();;
    RecyclerView recyclerView;
    AluminAdapter adapter ;
    DatabaseReference mDatabase ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_view_post);


        mDatabase= FirebaseDatabase.getInstance().getReference("posts");
        recyclerView = (RecyclerView) findViewById(R.id.studentRecycler);
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
