package com.example.medicinapp.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.File;
import com.example.medicinapp.R;
import com.example.medicinapp.providers.ImageProvider;
import com.example.medicinapp.utils.FileUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.storage.UploadTask;

public class PostActivity extends AppCompatActivity {

    ImageView mImageViewPost;
    Button mButtonPost;
    File mImageFile;
    ImageProvider mImageProvider;
    TextInputEditText mTextInputTitle;
    TextInputEditText mTextInputDescription;
    private final int GALERY_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        mImageViewPost = findViewById(R.id.imageViewPost);
        mButtonPost = findViewById(R.id.btnPost);

        mImageProvider = new ImageProvider();

        mButtonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveImage();
            }
        });

        mImageViewPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGalley();
            }
        });

    }

    private void saveImage() {
        mImageProvider.save(PostActivity.this, mImageFile).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(PostActivity.this, "Se almaceno la imagen", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(PostActivity.this, "Hubo un error al almacenar la imagen", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void openGalley() {
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, GALERY_REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALERY_REQUEST_CODE && resultCode == RESULT_OK){
            try {
                mImageFile = FileUtil.from(this, data.getData());
                mImageViewPost.setImageBitmap(BitmapFactory.decodeFile(mImageFile.getAbsolutePath()));
            } catch (Exception e) {
                Log.d("ERROR", "Se produjo un error " + e.getMessage());
                Toast.makeText(this, "Se ha producido un error" + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }
}