package com.pnytrainings.myapplication.ui.multi_media;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.pnytrainings.myapplication.R;

public class MultiMediaActivity extends AppCompatActivity {


    ImageView img;

    private static final int REQUEST_PERMISSIONS_CAMERA = 0;
    private static final int REQUEST_PERMISSIONS_GALLERY = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_media);
        img  = (ImageView) findViewById(R.id.imageView);
    }

    public void onCameraClicked(View view) {

        if (ActivityCompat.checkSelfPermission(MultiMediaActivity.this,
                Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {

            if (Build.VERSION.SDK_INT > 22) {

                requestPermissions(new String[]{Manifest.permission.CAMERA},
                        REQUEST_PERMISSIONS_CAMERA);

            }

        } else {
            camera();
        }

    }


    private void camera() {

        //  Toast.makeText(this, "Camera permission is allowed", Toast.LENGTH_SHORT).show();

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(takePictureIntent, REQUEST_PERMISSIONS_CAMERA);
    }

    private void gallery() {

        // Toast.makeText(this, "gallery permission is allowed", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(intent, REQUEST_PERMISSIONS_GALLERY);
    }

    public void onGalleryClicked(View view) {
        if (ActivityCompat.checkSelfPermission(MultiMediaActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {

            if (Build.VERSION.SDK_INT > 22) {

                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_PERMISSIONS_GALLERY);

            }

        } else {
            gallery();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == REQUEST_PERMISSIONS_GALLERY){

            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                gallery();
            }else {
                Toast.makeText(this, "Gallery Permission is Denied", Toast.LENGTH_SHORT).show();
            }

        }else if(requestCode ==  REQUEST_PERMISSIONS_CAMERA){

            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                camera();
            }else {
                Toast.makeText(this, "Camera Permission is Denied", Toast.LENGTH_SHORT).show();

            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_PERMISSIONS_CAMERA) {
            if (resultCode == RESULT_OK) {

                // data is the varable carrying image data .

                Bundle extras = data.getExtras();

                //A bitmap is a type of memory organization or image file format
                // used to store digital
                // images. The term bitmap comes from the computer programming
                // terminology, meaning just
                // a map of bits, a spatially mapped array of bits.

                Bitmap imageBitmap = (Bitmap) extras.get("data");
                img.setImageBitmap(imageBitmap);

            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled Image capture
                Toast.makeText(getApplicationContext(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();
            }
        }

        if (requestCode == REQUEST_PERMISSIONS_GALLERY) {
            if (resultCode == RESULT_OK) {
                if (data != null) {

                    Uri selectedImage = data.getData();

                    img.setImageURI(selectedImage);
                }

            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled Image capture
                Toast.makeText(getApplicationContext(),
                        "User cancelled gallery ", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }


}
