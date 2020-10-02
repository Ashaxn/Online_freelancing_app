package com.example.onlinefreelaceapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
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
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.onlinefreelaceapp.DataBase.DBHelper;
import com.example.onlinefreelaceapp.HelperClasses.Constants;
import com.example.onlinefreelaceapp.HelperClasses.HomeAdapter.AlertType;
import com.example.onlinefreelaceapp.HelperClasses.Utils;
import com.example.onlinefreelaceapp.adapter.GigHolder;
import com.facebook.stetho.Stetho;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import de.hdodenhof.circleimageview.CircleImageView;

public class Create_A_Gig extends AppCompatActivity {

    CircleImageView imageView;
    EditText txt_title, txt_category, txt_description, txt_delivery_info, txt_advance_amount, txt_second_payment, txt_contact;
    Button btn_save;


    private static final int CAMERA_REQUEST_CODE = 100;
    private static final int STORAGE_REQUEST_CODE = 101;

    private static final int IMAGE_PIC_CAMERA_CODE = 100;
    private static final int IMAGE_PIC_GALLERY_CODE = 103;

    private String[] cameraPermission;
    private String[] storagePermission;

    private Uri imageUri;

    private String title, category, description, deliinfo, advanceAmount, secondPayment, contactNo, timestamp;
    private DBHelper dbHelper;
    private int primaryKey;
    private boolean isUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__a__gig);

        imageView = findViewById(R.id.img_cover);
        txt_title = findViewById(R.id.txt_title);
        txt_category = findViewById(R.id.txt_category);
        txt_description = findViewById(R.id.txt_description);
        txt_delivery_info = findViewById(R.id.txt_delivery_info);
        txt_advance_amount = findViewById(R.id.txt_advance);
        txt_second_payment = findViewById(R.id.txt_second_amount);
        txt_contact = findViewById(R.id.txt_contact);
        btn_save = findViewById(R.id.btn_save);


        cameraPermission = new String[]{
                Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        storagePermission = new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };


        //initiate database object in main function
        dbHelper = new DBHelper(this);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imagePickDialog();

            }
        });


        btn_save.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                //when click on save button insert the data to database
                save();

            }
        });


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            GigHolder gigHolder = dbHelper.getGigsFromPrimaryKey(extras.getInt(Constants.BUNDLE_ID));
            setData(gigHolder);
            primaryKey = extras.getInt(Constants.BUNDLE_ID);
            btn_save.setText("Update");
            isUpdate = true;
        }

    }


    private void setData(GigHolder holder) {
        if (holder != null) {
            imageUri = holder.getImage();
            imageView.setImageURI(holder.getImage());
            txt_title.setText(holder.getTitle());
            txt_category.setText(holder.getCategory());
            txt_description.setText(holder.getDescription());
            txt_delivery_info.setText(holder.getDeliveryInfo());
            txt_advance_amount.setText(Utils.getDecimal(Double.parseDouble(holder.getAdvanceAmount())));
            txt_second_payment.setText(Utils.getDecimal(Double.parseDouble(holder.getSecondAmount())));
            txt_contact.setText(holder.getContact());
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void save() {


        //validation
        if (imageUri == null) {
            Utils.showMessage(this, AlertType.ERROR,"Select a cover image !");
            return;
        } else if (!Utils.validateField(txt_title, "Enter gig title !")) {
            return;
        } else if (!Utils.validateField(txt_category, "Enter gig category !")) {
            return;
        } else if (!Utils.validateField(txt_description, "Enter gig description !")) {
            return;

        } else if (!Utils.validateField(txt_delivery_info, "Enter delivery infomations !")) {

        } else if (!Utils.validateField(txt_delivery_info, "Enter delivery information !")) {

            return;
        } else if (!Utils.validateField(txt_advance_amount, "Enter advance price !")) {
            return;
        } else if (!Utils.validateField(txt_second_payment, "Enter second payment !")) {
            return;
        } else if (!Utils.validateField(txt_contact, "Enter your contact number !")) {
            return;
        }


        //getting data from edittext
        title = "" + txt_title.getText().toString();
        category = "" + txt_category.getText().toString();
        description = "" + txt_description.getText().toString();
        deliinfo = "" + txt_delivery_info.getText().toString();
        advanceAmount = "" + txt_advance_amount.getText().toString();
        secondPayment = "" + txt_second_payment.getText().toString();
        contactNo = "" + txt_contact.getText().toString();


        timestamp = "" + System.currentTimeMillis();


        if (isUpdate) {
            dbHelper.updateGig(
                    primaryKey,
                    title,
                    category,
                    description,
                    deliinfo,
                    advanceAmount,
                    secondPayment,
                    contactNo,
                    imageUri.toString());
            Utils.showMessage(this,AlertType.TOAST,"Operation Successful !");
            finish();
        } else {
            dbHelper.saveGig(
                    title,
                    category,
                    description,
                    deliinfo,
                    advanceAmount,
                    secondPayment,
                    contactNo,
                    imageUri.toString());
            Utils.showMessage(this,AlertType.SUCCESS,"Operation Successful !");
            clearAll();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void clearAll() {
        txt_title.setText("");
        txt_category.setText("");
        txt_description.setText("");
        txt_delivery_info.setText("");
        txt_advance_amount.setText("");
        txt_second_payment.setText("");
        txt_contact.setText("");
        imageView.setImageDrawable(getDrawable(R.drawable.freelancer));
    }

    private void imagePickDialog() {

        String[] options = {"Camera", "Gallery"};

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Select for image");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (which == 0) {

                    if (!checkCameraPermission()) {

                        requestCameraPermission();
                    } else {

                        pickFromCamera();
                    }
                } else if (which == 1) {

                    if (!checkStoragePermission()) {

                        requestStoragePermission();
                    } else {

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
        startActivityForResult(galleryIntent, IMAGE_PIC_GALLERY_CODE);

    }

    private void pickFromCamera() {

        //get image from camera
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "Image title");
        values.put(MediaStore.Images.Media.DESCRIPTION, "Image description");

        imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(cameraIntent, IMAGE_PIC_CAMERA_CODE);


    }

    private boolean checkStoragePermission() {
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == (PackageManager.PERMISSION_GRANTED);
        return result;
    }


    private void requestStoragePermission() {
        ActivityCompat.requestPermissions(this, storagePermission, STORAGE_REQUEST_CODE);
    }


    private boolean checkCameraPermission() {
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == (PackageManager.PERMISSION_GRANTED);

        boolean result1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == (PackageManager.PERMISSION_GRANTED);

        return result && result1;
    }


    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(this, cameraPermission, CAMERA_REQUEST_CODE);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {

            case CAMERA_REQUEST_CODE: {

                if (grantResults.length > 0) {

                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean storageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if (cameraAccepted && storageAccepted) {

                        pickFromCamera();
                    } else {

                        Toast.makeText(this, "camera permission required!", Toast.LENGTH_SHORT).show();
                    }

                }
            }
            break;
            case STORAGE_REQUEST_CODE: {

                if (grantResults.length > 0) {


                    boolean storageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                    if (storageAccepted) {

                        pickFromStorage();
                    } else {

                        Toast.makeText(this, "storage permission required!", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (resultCode == RESULT_OK) {

            if (requestCode == IMAGE_PIC_GALLERY_CODE) {

                CropImage.activity(data.getData())
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1, 1)
                        .start(this);

            } else if (requestCode == IMAGE_PIC_CAMERA_CODE) {
                CropImage.activity(imageUri)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1, 1)
                        .start(this);

            } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {

                CropImage.ActivityResult result = CropImage.getActivityResult(data);

                if (resultCode == RESULT_OK) {

                    Uri resultUri = result.getUri();
                    imageUri = resultUri;
                    imageView.setImageURI(resultUri);

                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                    Exception error = result.getError();
                    Toast.makeText(this, "" + error, Toast.LENGTH_SHORT).show();

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
//end of create gig part