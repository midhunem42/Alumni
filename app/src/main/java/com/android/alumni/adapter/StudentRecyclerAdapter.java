package com.android.alumni.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.alumni.R;
import com.android.alumni.model.StudentModel;

import java.util.List;

public class StudentRecyclerAdapter extends RecyclerView.Adapter<StudentRecyclerAdapter.StudentViewholder>{

    private Context context;
    private List<StudentModel> studentModel;

    public StudentRecyclerAdapter(Context context, List<StudentModel> studentModel) {
        this.context = context;
        this.studentModel = studentModel;
    }

    @NonNull
    @Override
    public StudentViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.studentitem, viewGroup, false);
        return new StudentViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewholder studentViewholder, int i) {
        StudentModel student =studentModel.get(i);

        studentViewholder.Rollno.setText(student.getRollno());
        studentViewholder.Name.setText(student.getName());
        studentViewholder.semester.setText(student.getSemester());

    }

    @Override
    public int getItemCount() {
        return studentModel.size();
    }

    public class StudentViewholder extends RecyclerView.ViewHolder {
        TextView Rollno,Name,semester;
        public StudentViewholder(@NonNull View itemView) {
            super(itemView);
            Rollno=itemView.findViewById(R.id.Tvroll);
            Name =itemView.findViewById(R.id.TvName);
            semester=itemView.findViewById(R.id.tvsem);
        }
    }
}
