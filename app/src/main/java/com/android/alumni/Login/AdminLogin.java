package com.android.alumni.Login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.alumni.Admin.AdminPage;
import com.android.alumni.R;
import com.android.alumni.common.Common;
import com.android.alumni.model.AdminModel;
import com.android.alumni.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AdminLogin extends AppCompatActivity {
    Query query,data;
    private DatabaseReference mAdmin;
    private EditText edtusername,edtpassword ;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);


        query = FirebaseDatabase.getInstance().getReference().child("admins");

        edtusername = findViewById(R.id.adminUsername);
        edtpassword = findViewById(R.id.adminPassword);
        Button login = findViewById(R.id.adminSubmit);
        progressBar=findViewById(R.id.adminProgress);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validation();
            }
        });

    }


    private void validation() {

        final String username=edtusername.getText().toString().trim();
        final String password=edtpassword.getText().toString().trim();

        String user="userName";
        if (username.isEmpty()){
            edtusername.setError("Username is Required ");
            edtusername.requestFocus();

            return;
        }
        if (password.isEmpty()){
            edtpassword.setError("Password is Required ");
            edtpassword.requestFocus();
            return;
        }
        if (password.length()<6){
            edtpassword.setError("Minimum length of password should be 6");
            edtpassword.requestFocus();
            return;
        }

        data=query.orderByChild(user).equalTo(username);
       data.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               boolean v =dataSnapshot.exists();

               Log.d("tmz", "username : " +v);

               for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                 AdminModel user1=postSnapshot.getValue(AdminModel.class);
                   Log.d("login", "key  : "+postSnapshot.getKey() );
                 if (user1.getPassword().equals(password)){
                     Log.d("login", "inside success : " );
                     Common.ADMIN= user1;
                     startActivity(new Intent(AdminLogin.this,AdminPage.class));
                 }
                 else {
                     Log.d("login", "inside fail : " );
                 }

                   Log.d("tmz", "username : " + user1.getUserName() + ", password " + user1.getPassword());
               }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });
        Log.d("login", "outside : " );



    }


}
