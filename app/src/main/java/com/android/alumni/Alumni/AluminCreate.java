package com.android.alumni.Alumni;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.alumni.R;
import com.android.alumni.model.Post;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AluminCreate extends AppCompatActivity {
    private static final int GALLERY_REQUEST = 999;
    EditText edTitle,edDes;
    Button post,choose;
    private Uri mImageUri;
    StorageReference mStorageRef;
    DatabaseReference mDatabase;
    Uri downloadUri;
    String title_val,desc_val,times;
    ProgressBar progress;
    Post data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumin_create);
        initil();
        times=currentDate();
        mDatabase= FirebaseDatabase.getInstance().getReference().child("posts");
        clickEvents();
    }
    private void initil() {
        progress=findViewById(R.id.pbUpload);
        edDes=findViewById(R.id.postdescription);
        edTitle=findViewById(R.id.posttitle);
        choose=findViewById(R.id.postImagechoose);
        post=findViewById(R.id.submitpost);
        mStorageRef= FirebaseStorage.getInstance().getReference().child("images");
        mDatabase=FirebaseDatabase.getInstance().getReference().child("posts");
    }
    private void clickEvents() {
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");

                startActivityForResult(Intent.createChooser(galleryIntent,"SelectImage"), GALLERY_REQUEST);
            }
        });
        post.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
            @Override
            public void onClick(View view) {
                startPosting();
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    private void startPosting() {
        progress.setVisibility(View.VISIBLE);
        title_val = edTitle.getText().toString().trim();
        desc_val = edDes.getText().toString().trim();
        Log.d("post","inside post method");
        if(title_val.isEmpty()){
            edTitle.setError("Please Enter Title");
        }
        if(desc_val.isEmpty()){
            edDes.setError("Please Enter Description");
        }
        if (mImageUri ==null){
            Toast.makeText(AluminCreate.this,"Please Choose Image",Toast.LENGTH_SHORT).show();
        }
        if (!TextUtils.isEmpty(title_val) && !TextUtils.isEmpty(desc_val) && mImageUri != null) {
            uploadImage();


        }



    }

    private void uploadImage() {
        final StorageReference filepath= mStorageRef.child(mImageUri.getLastPathSegment());
        Log.d("imageUri","Storage reference ");
        UploadTask uploadTask=filepath.putFile(mImageUri);
        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }
                return filepath.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {

                    downloadUri = task.getResult();
                    String url=downloadUri.toString();
                    String user="users";
                    data=new Post(title_val,desc_val,times,user,url);
                    Log.d("Url",downloadUri.toString());
                }
            }
        }).addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Log.d("Status","Uploaded ");
                final DatabaseReference newPost = mDatabase.push();
                newPost.setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.d("Status","Database updated ");
                    }
                });
                progress.setVisibility(View.GONE);
                startActivity(new Intent(AluminCreate.this,AluminiPosts.class));


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("Status","failed: "+e.getMessage());
            }
        });



    }

    public static String currentDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        // get current date time with Date()139`
        Date date = new Date();
        // System.out.println(dateFormat.format(date));
        // don't print it, but save it!
        return dateFormat.format(date);
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
