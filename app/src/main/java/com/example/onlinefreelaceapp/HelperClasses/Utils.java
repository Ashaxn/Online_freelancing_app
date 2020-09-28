package com.example.onlinefreelaceapp.HelperClasses;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;


import com.example.onlinefreelaceapp.HelperClasses.HomeAdapter.AlertType;

import java.text.DecimalFormat;

import de.mateware.snacky.Snacky;

public class Utils {

    private static DecimalFormat decimalFormat = new DecimalFormat("0.00");

    public static boolean isNotEmptyString(String value) {
        if (value != null && !value.equalsIgnoreCase("")) {
            return true;
        }
        return false;
    }


    public static void showMessage(Activity activity, AlertType alertType, String message) {
       // Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
       switch (alertType){

           case SUCCESS:
               Snacky.builder()
                       .setActivity(activity)
                       .setText(message)
                       .setDuration(3000)
                       .success()
                       .show();
               break;
           case ERROR:
               Snacky.builder()
                       .setActivity(activity)
                       .setText(message)
                       .setDuration(3000)
                       .error()
                       .show();
               break;
           case TOAST:
               Toast.makeText(activity,message,Toast.LENGTH_LONG).show();
       }
    }

    public static boolean validateField(EditText editText, String msg) {
        if (editText.getText().toString().length() == 0) {
            editText.setError(msg);
            editText.requestFocus();

            return false;
        }
        return true;
    }


    public static String getRealPathFromUri(Uri contentUri, Activity activity) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = activity.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }


    public static String getDecimal(int value) {
        return decimalFormat.format(value);
    }

    public static String getDecimal(double value) {
        return decimalFormat.format(value);
    }

    public static String getDecimal(String value) {
        return decimalFormat.format(value);
    }
}
