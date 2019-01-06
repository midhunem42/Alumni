package com.android.alumni.Admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.android.alumni.R;

public class ManageStudent extends AppCompatActivity {

    CardView addStudent,viewStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_student);

        addStudent=findViewById(R.id.cvAddStudent);
        viewStudent=findViewById(R.id.cvViewStudent);

        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ManageStudent.this,AddStudent.class));
            }
        });

        viewStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ManageStudent.this,ViewStudent.class));
            }
        });
    }
}
