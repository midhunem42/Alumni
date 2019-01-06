package com.android.alumni.Admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.alumni.R;
import com.android.alumni.common.Common;
import com.android.alumni.model.AdminModel;
import com.squareup.picasso.Picasso;

public class AdminProfile extends AppCompatActivity {

    private static final int GALLERY_REQUEST = 999;
    AdminModel admin;
    ImageView pic;
    TextView name,dep;
    String depart,na,pi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);

        dep=findViewById(R.id.adProDep);
        name=findViewById(R.id.adProName);
        pic=findViewById(R.id.adPic);
        depart =Common.ADMIN.getDepartment();
        na =Common.ADMIN.getName();
        pi = Common.ADMIN.getImgUrl();
        dep.setText(depart);
        name.setText(na);

        Picasso.get().load(pi).into(pic);

        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadImage();
            }
        });
    }

    private void loadImage() {
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(Intent.createChooser(galleryIntent,"SelectImage"), GALLERY_REQUEST);
        updatePic();
    }

    private void updatePic() {

    }
}
