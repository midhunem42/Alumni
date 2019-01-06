package com.android.alumni.Admin;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.alumni.R;
import com.android.alumni.model.StudentModel;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class AddStudent extends AppCompatActivity {

    DatabaseReference mDatabase ;
    String name,email,rollno,department,semester,yearofjoining,mobileno,address,username,password,profilepicurl,gender,batch;
    Spinner semesterspinner;
    EditText edName,edRollno,edDep,edYoj,edMob,edEmail,edAddress;
    StudentModel student;
    Button submit ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        defaultValue();
        semesterspinner=findViewById(R.id.semester);
        edName=findViewById(R.id.edName);
        edRollno=findViewById(R.id.edrollno);
        edDep=findViewById(R.id.edDep);
        edYoj=findViewById(R.id.edyoj);
        edMob=findViewById(R.id.edmob);
        edEmail=findViewById(R.id.edemail);
        edAddress=findViewById(R.id.edAdd);
        submit=findViewById(R.id.btsubmit);

        mDatabase=  FirebaseDatabase.getInstance().getReference().child("student");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
                getStudent();
                setData();
            }
        });

        ArrayAdapter<CharSequence> adapterSemester  = ArrayAdapter.createFromResource(this,
                R.array.semesterlists, android.R.layout.simple_spinner_item);
        adapterSemester.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        semesterspinner.setAdapter(adapterSemester);
        semesterspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                semester=adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        getData();




    }

    private void setData() {

        mDatabase.child(yearofjoining).child(department+batch).push().setValue(student);
        Log.d("login", "Student added : "+yearofjoining+student );

    }

    private void getStudent() {
        student=new StudentModel();
        student.setName(name);
        student.setRollno(rollno);
        student.setDepartment(department);
        student.setSemester(semester);
        student.setYearofjoining(yearofjoining);
        student.setAddress(address);
        student.setMobileno(mobileno);
        student.setEmail(email);
        student.setGender(gender);
        student.setUsername(username);
        student.setPassword(password);
        student.setProfilepicurl(profilepicurl);
        student.setBatch(batch);
    }

    private void getData() {
        name=edName.getText().toString();
        rollno=edRollno.getText().toString();
        department=edDep.getText().toString();
        yearofjoining=edYoj.getText().toString();
        mobileno=edMob.getText().toString();
        email=edEmail.getText().toString();
        address=edAddress.getText().toString();

    }

    private void defaultValue() {
        name="name";
        email="email";
        rollno="0";
        department="cse";
        semester="1";
        yearofjoining="2014";
        mobileno="88888888";
        address="address";
        username="username";
        password="password";
        profilepicurl="ffffff";
        gender="gender";
        batch="A";
    }


}
