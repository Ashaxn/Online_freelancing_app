package com.example.onlinefreelaceapp.HelperClasses;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {
    static SharedPreferences preferences;
    static SharedPreferences.Editor editor;
    static Context _context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "NB";

    public PrefManager(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = preferences.edit();
        _context = context;
    }

    public void setPreference(String paramName, String paramValue) {
        editor.putString(paramName, paramValue);
        editor.commit();
    }



    public String getString(String paramName) {
        return preferences.getString(paramName, "");
    }


    public void removePreference(String paramName) {
        editor.remove(paramName);
        editor.commit();
    }
}
