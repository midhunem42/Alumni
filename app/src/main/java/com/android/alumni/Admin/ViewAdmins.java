package com.android.alumni.Admin;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.alumni.R;
import com.android.alumni.adapter.AdminRecyclerAdapter;
import com.android.alumni.model.AdminModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewAdmins extends AppCompatActivity {

    List<AdminModel> adminModelList = new ArrayList<>();
    AdminRecyclerAdapter adapter;
    DatabaseReference mAdmins;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_admins);

        mAdmins = FirebaseDatabase.getInstance().getReference().child("admins");

        recyclerView = findViewById(R.id.viewAdminRecy);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Loadadmins();
    }

    private void Loadadmins() {

        mAdmins.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                adminModelList.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    AdminModel admin = postSnapshot.getValue(AdminModel.class);
                    String Usname,name,pass,dep,imgurl;
                    Usname = admin.getUserName();
                    name =admin.getName();
                    pass =admin.getPassword();
                    dep=admin.getDepartment();
                    imgurl = admin.getImgUrl();

                    AdminModel adminList =new AdminModel();
                    adminList.setPassword(pass);
                    adminList.setUserName(Usname);
                    adminList.setDepartment(dep);
                    adminList.setName(name);
                    adminList.setImgUrl(imgurl);
                    Log.d("aa",adminList.getName());
                    adminModelList.add(adminList);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        adapter =new AdminRecyclerAdapter(this,adminModelList);
        recyclerView.setAdapter(adapter);
    }
}
