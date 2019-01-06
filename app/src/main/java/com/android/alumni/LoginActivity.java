package com.android.alumni;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.alumni.Alumni.AluminiPage;
import com.android.alumni.Alumni.AluminiPosts;
import com.android.alumni.Login.AdminLogin;
import com.android.alumni.Login.AlumniLogin;
import com.android.alumni.Login.StudentLogin;

public class LoginActivity extends AppCompatActivity {

    private Button admin ,student, alumni ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        admin=findViewById(R.id.adminLogin);
        student=findViewById(R.id.studentLogin);
        alumni=findViewById(R.id.alumniLogin);

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, AdminLogin.class));
            }
        });
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, StudentLogin.class));
            }
        });
        alumni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, AluminiPage.class));
            }
        });
    }
}
