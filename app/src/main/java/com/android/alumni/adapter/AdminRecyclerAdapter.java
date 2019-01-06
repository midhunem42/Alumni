package com.android.alumni.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.alumni.R;
import com.android.alumni.model.AdminModel;
import com.android.alumni.model.StudentModel;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdminRecyclerAdapter extends RecyclerView.Adapter<AdminRecyclerAdapter.AdminViewHolder> {
    private Context context;
    private List<AdminModel> adminModelList;

    public AdminRecyclerAdapter(Context context, List<AdminModel> adminModelList) {
        this.context = context;
        this.adminModelList = adminModelList;
    }


    @NonNull
    @Override
    public AdminViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.admins, viewGroup, false);
        return new AdminRecyclerAdapter.AdminViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminViewHolder adminViewHolder, int i) {
        AdminModel admin = new AdminModel();

        adminViewHolder.name.setText(admin.getName());
        adminViewHolder.dep.setText(admin.getDepartment());
        Picasso.get().load(admin.getImgUrl()).into(adminViewHolder.pic);

    }

    @Override
    public int getItemCount() {
        return adminModelList.size();
    }

    public class AdminViewHolder extends RecyclerView.ViewHolder{
        CircleImageView pic;
        TextView name,dep;
        public AdminViewHolder(@NonNull View itemView) {
            super(itemView);
            pic=itemView.findViewById(R.id.ViewadminPropic);
            name=itemView.findViewById(R.id.ViewAdName);
            dep=itemView.findViewById(R.id.ViewAdDep);
        }
    }
}
