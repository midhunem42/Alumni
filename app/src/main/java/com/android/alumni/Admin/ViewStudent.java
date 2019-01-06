package com.android.alumni.Admin;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListPopupWindow;

import com.android.alumni.R;
import com.android.alumni.adapter.StudentRecyclerAdapter;
import com.android.alumni.model.StudentModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewStudent extends AppCompatActivity {

    List<StudentModel> students  = new ArrayList<>();;
    RecyclerView recyclerView;
    StudentRecyclerAdapter adapter ;
    DatabaseReference mDatabase ;

    String department , yearj , batchs ;
    EditText dep,year ,batch;
    Button submit ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);

        dep=findViewById(R.id.vsdep);
        year=findViewById(R.id.vsyoj);
        batch=findViewById(R.id.vsbatch);
        submit=findViewById(R.id.vsSubmit);
        recyclerView = findViewById(R.id.viewRecycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data();

            }
        });
    }

    private void data() {
        department=dep.getText().toString().trim();
        yearj=year.getText().toString().trim();
        batchs=batch.getText().toString().trim();

        if (department.isEmpty())
            return;
        if (yearj.isEmpty())
            return;
        if (batchs.isEmpty())
            return;

        mDatabase= FirebaseDatabase.getInstance().getReference("student").child("2014").child("cseA");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                students.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    StudentModel student =postSnapshot.getValue(StudentModel.class);
                    String roll=student.getRollno();
                    String name =student.getName();
                    String sem=student.getSemester();
                    StudentModel studentss=new StudentModel();
                    studentss.setName(name);
                    studentss.setRollno(roll);
                    studentss.setSemester(sem);

                    students.add(studentss);
                    Log.d("login", "Student added : "+student.toString() );
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        adapter=new StudentRecyclerAdapter(this,students);
        recyclerView.setAdapter(adapter);
    }
}
