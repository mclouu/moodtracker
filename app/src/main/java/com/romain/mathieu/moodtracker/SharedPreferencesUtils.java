package com.romain.mathieu.moodtracker;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Romain on 06/02/2018.
 */

public class SharedPreferencesUtils {

    public static final String MY_FILE = "MySharedPreference.xml";
    public static final String CLE_MESSAGE = "CLE_MESSAGE";
    public static final String CLE_MOOD = "CLE_MOOD";


    public static void saveMessage(Context context, String message) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(CLE_MESSAGE, message);
        editor.apply();
    }

    public static String getMessage(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getString(CLE_MESSAGE, "");
    }


    public static void saveMood(Context context, int mood) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(CLE_MOOD, mood);
        editor.apply();
    }

    public static int getMood(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(CLE_MOOD, 0);
    }


}
