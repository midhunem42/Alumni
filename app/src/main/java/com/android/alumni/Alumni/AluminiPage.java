package com.android.alumni.Alumni;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.android.alumni.Admin.ViewStudent;
import com.android.alumni.R;

public class AluminiPage extends AppCompatActivity {

    CardView posts,viewStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumni_page);
        posts=findViewById(R.id.aluminiPostCv);
        viewStudent=findViewById(R.id.alumin_ViewStudent);

        viewStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AluminiPage.this, ViewStudent.class));
            }
        });
        posts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AluminiPage.this,AluminiPosts.class));
            }
        });
    }
}
