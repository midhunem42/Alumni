package com.android.alumni.Admin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.alumni.R;
import com.android.alumni.common.Common;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class ViewPic extends AppCompatActivity {
    private static final int GALLERY_REQUEST = 999;
    DatabaseReference mAdmin;
    StorageReference mPic;
    private Uri mImageUri;
    Uri downloadUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pic);

        ImageView pic =findViewById(R.id.viewAdminPic);
        String Url = Common.ADMIN.getImgUrl();
        Picasso.get().load(Url).into(pic);

        Button update = findViewById(R.id.UpdateproPic);

        update.setOnClickListener(new View.OnClickListener() {
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

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            mImageUri = data.getData();
            Log.d("imageUri",mImageUri.toString());
        }
    }
}
