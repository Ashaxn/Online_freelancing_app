package com.example.onlinefreelaceapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.onlinefreelaceapp.DataBase.DBHelperCHARU;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class Create_A_Gig extends AppCompatActivity {

    ImageView gImageView;
    EditText GigTitleET, GigCatET, GigDesET, GigDelInfoET, GigPriceInfoET, GigConInfoET;
    Button saveinfobtn;


    private static final int CAMERA_REQUEST_CODE=100;
    private static final int STORAGE_REQUEST_CODE=101;

    private static final int IMAGE_PIC_CAMERA_CODE=100;
    private static final int IMAGE_PIC_GALLERY_CODE=103;

    private String[] cameraPermission;
    private String[] storagePermission;

    private Uri imageUri;

    private String title,category,description,deliinfo,priceinfo,coninfo,timestamp;
    private DBHelperCHARU dbHelperCHARU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__a__gig);

                gImageView=findViewById(R.id.insertgigimage);
                GigTitleET=findViewById(R.id.creategig_title);
                GigCatET=findViewById(R.id.creategig_category);
                GigDesET=findViewById(R.id.creategig_des);
                GigDelInfoET=findViewById(R.id.creategig_delinfo);
                GigPriceInfoET=findViewById(R.id.creategig_pricinginfo);
                GigConInfoET=findViewById(R.id.creategig_contactinfo);
                saveinfobtn=findViewById(R.id.savegigdetailsbutton);


         cameraPermission = new String[]{
            Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

         storagePermission = new String[]{
                 Manifest.permission.WRITE_EXTERNAL_STORAGE
         };



         //initiate database object in main function
        dbHelperCHARU = new DBHelperCHARU(this);



         gImageView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 imagePickDialog();

             }
         });


         saveinfobtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 //when click on save button insert the data to database
                 getData();

             }
         });

    }

    private void getData() {

        //getting data
        title = ""+GigTitleET.getText().toString().trim();
        category = ""+GigCatET.getText().toString().trim();
        description = ""+GigDesET.getText().toString().trim();
        deliinfo = ""+GigDelInfoET.getText().toString().trim();
        priceinfo = ""+GigPriceInfoET.getText().toString().trim();
        coninfo = ""+GigConInfoET.getText().toString().trim();


        timestamp = ""+System.currentTimeMillis();


        long id = dbHelperCHARU.insertinfo(
                ""+title,
                ""+category,
                ""+description,
                ""+deliinfo,
                ""+priceinfo,
                ""+coninfo,
                ""+imageUri,
                ""+timestamp,
                ""+timestamp

        );
        Toast.makeText(this,"Record added to id: "+id,Toast.LENGTH_SHORT).show();

    }

    private void imagePickDialog() {

        String[] options = {"Camera","Gallery"};

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Select for image");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (which == 0){

                    if (!checkCameraPermission()){

                        requestCameraPermission();
                    }
                    else {

                        pickFromCamera();
                    }
                }

                else if (which == 1){

                    if (!checkStoragePermission()){

                        requestStoragePermission();
                    }
                    else {
                        
                        pickFromStorage();
                    }
                }

            }
        });
        builder.create().show();
    }

    private void pickFromStorage() {

        //get image from gallery

        Intent galleryIntent = new Intent(Intent.ACTION_PICK);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,IMAGE_PIC_GALLERY_CODE);

    }

    private void pickFromCamera() {

        //get image from camera
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "Image title");
        values.put(MediaStore.Images.Media.DESCRIPTION, "Image description");

        imageUri=getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
        startActivityForResult(cameraIntent,IMAGE_PIC_CAMERA_CODE);


    }

    private boolean checkStoragePermission(){
        boolean result = ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == (PackageManager.PERMISSION_GRANTED);
        return result;
    }



    private void requestStoragePermission(){
        ActivityCompat.requestPermissions(this,storagePermission,STORAGE_REQUEST_CODE);
    }



    private boolean checkCameraPermission(){
        boolean result = ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA)
                == (PackageManager.PERMISSION_GRANTED);

        boolean result1 = ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == (PackageManager.PERMISSION_GRANTED);

        return result && result1;
    }



    private void requestCameraPermission(){
        ActivityCompat.requestPermissions(this,cameraPermission,CAMERA_REQUEST_CODE);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){

            case CAMERA_REQUEST_CODE:{

                if (grantResults.length>0){

                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean storageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if (cameraAccepted && storageAccepted){

                        pickFromCamera();
                    }
                    else {

                        Toast.makeText(this,"camera permission required!",Toast.LENGTH_SHORT).show();
                    }

                }
            }
            break;
            case STORAGE_REQUEST_CODE:{

                if (grantResults.length>0){


                    boolean storageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                    if (storageAccepted){

                       pickFromStorage();
                    }
                    else {

                        Toast.makeText(this,"storage permission required!",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (resultCode == RESULT_OK){

            if (requestCode == IMAGE_PIC_GALLERY_CODE){

                CropImage.activity(data.getData())
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1,1)
                        .start(this);

            }
            else if (requestCode == IMAGE_PIC_CAMERA_CODE){
                CropImage.activity(imageUri)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1,1)
                        .start(this);

            }
            else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){

                CropImage.ActivityResult result = CropImage.getActivityResult(data);

                if (resultCode == RESULT_OK){

                    Uri resultUri = result.getUri();
                    imageUri=resultUri;
                    gImageView.setImageURI(resultUri);

                }
                else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){

                    Exception error = result.getError();
                    Toast.makeText(this,""+error,Toast.LENGTH_SHORT).show();

                }

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

}