package com.romain.mathieu.moodtracker.Model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import static com.romain.mathieu.moodtracker.Controller.MainActivity.colorMood;
import static com.romain.mathieu.moodtracker.Controller.MainActivity.mood;
import static com.romain.mathieu.moodtracker.Controller.MainActivity.moodData;
import static com.romain.mathieu.moodtracker.Controller.MainActivity.widthMood;
import static com.romain.mathieu.moodtracker.Model.SharedPreferencesUtils.KEY_MOOD;
import static com.romain.mathieu.moodtracker.Model.SharedPreferencesUtils.MY_FILE;

/**
 * Created by romain on 20/02/2018
 */

public class MyBroadcastReceiver extends BroadcastReceiver {

    /* This method is executed at midnight every day

     * It will create an arrayList with the data wanted by the user
     * Param 1 = the date where mood is recorded.
     * Param 2 = retrieves the message saved in sharedpreferences
     * Param 3 = retrieves the color in Hashtable colorMood (go to initializeData() in Mainactivity)
               * with the mood value saved in my sharedPreferences
     * Param 4 = same than param 3 but for the hashtable widthMood

      * Save the arrayList */
    @Override
    public void onReceive(Context context, Intent intent) {
        moodData.add(new MoodData(" ", SharedPreferencesUtils.getMessage(context), colorMood.get(SharedPreferencesUtils.getMood(context)), widthMood.get(SharedPreferencesUtils.getMood(context))));
        resetMood(context);
        SharedPreferencesUtils.saveArrayList(context);

    }

    // resets mood to 3 to have the default  smiley in the next day
    public void resetMood(Context context) {
        SharedPreferencesUtils.removeMood(context, MY_FILE, KEY_MOOD);
        mood = 3;
    }

}
