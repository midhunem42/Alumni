package com.android.alumni.Student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.alumni.R;

public class StudentPage extends AppCompatActivity {

    Button Posts,Profile,Logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_page);
        Posts=findViewById(R.id.studentPosts);
        Profile=findViewById(R.id.studentProfile);
        Logout=findViewById(R.id.studentLogout);


        Posts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudentPage.this,StudentViewPost.class));
            }
        });


    }
}
