package com.android.alumni.Admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

import com.android.alumni.R;

public class AdminPage extends AppCompatActivity {


    CardView student,admin,alumini,profile,notification,logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
        student=findViewById(R.id.cvStudent);
        admin = findViewById(R.id.cvAdmins);
        alumini =findViewById(R.id.manageAlumin);
        profile = findViewById(R.id.cvProfile);

        alumini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminPage.this,ManageAlumini.class));
            }
        });

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminPage.this,ManageStudent.class));
            }
        });

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminPage.this,    ManageAdmins.class));
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminPage.this,    AdminProfile.class));
            }
        });
    }
}
