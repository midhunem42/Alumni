package com.android.alumni.Admin;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.alumni.R;
import com.android.alumni.model.AdminModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddAdmin extends AppCompatActivity {

    private  String AdName, AdDep;
    private EditText name,department;
    private TextInputLayout nameLay,depLay;
    private AdminModel admin;
    private Button submit;
    private DatabaseReference madmins;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_admin);
        madmins=FirebaseDatabase.getInstance().getReference().child("admins");

        name=findViewById(R.id.AdName);
        department=findViewById(R.id.AdDepartMent);

        nameLay = findViewById(R.id.AdNameLay);
        depLay = findViewById(R.id.AddepLay);

        submit = findViewById(R.id.addAdmin);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submintForm();
            }
        });



    }

    private void submintForm() {

        if(!validName()){
            return;
        }
        if (!validDepartment()){
            return;
        }

        getAdmin();
        add();
    }

    private void add() {
        madmins.push().setValue(admin).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(AddAdmin.this,"Admin Added" ,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getAdmin() {
        AdName = name.getText().toString().trim();
        AdDep =department.getText().toString().trim();
        String userName = AdName + "123";
        String password = AdName + "123";

        admin =new AdminModel(userName,AdDep,AdName,password,"https://cdn.iconscout.com/icon/free/png-256/account-profile-avatar-man-circle-round-user-30452.png" );
    }

    private boolean validName(){
        if (name.getText().toString().trim().isEmpty()) {
            nameLay.setError(" Name can not be Empty");
            name.requestFocus();
            return false;
        }
        else{
            nameLay.setErrorEnabled(false);
        }

        return true;
    }
    private boolean validDepartment(){
        if (department.getText().toString().trim().isEmpty()) {
            depLay.setError(" Password can not be Empty");
            department.requestFocus();
            return false;
        }
        else{
            depLay.setErrorEnabled(false);
        }

        return true;
    }
}
