package com.romain.mathieu.moodtracker.Model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.romain.mathieu.moodtracker.R;

import static com.romain.mathieu.moodtracker.Controller.MainActivity.moodData;
import static com.romain.mathieu.moodtracker.Controller.MainActivity.tableBackgroundColor;

/**
 * Created by romain on 20/02/2018
 */

public class MyBroadcastReceiver extends BroadcastReceiver {

    /* This method is executed at midnight every day

     * tableWidthMood is a table with the screen width which I multiply by a ratio

     * It will create an arrayList with the data wanted by the user
     * Param 1 = the date where mood is recorded.
     * Param 2 = retrieves the message saved in sharedpreferences
     * Param 3 = retrieves the color in table colorMood (go to initializeData() in Mainactivity)
               * with the mood value saved in my sharedPreferences
     * Param 4 = same than param 3 but for the table widthMood

     * if containsMood = false (app is not launched today) than create a black cardView with message "empty mood"

      * save the arrayList
      * reset data */
    @Override
    public void onReceive(Context context, Intent intent) {

        float tableWidthMood[] = {SharedPreferencesUtils.getWidth(context) * 0.25f, SharedPreferencesUtils.getWidth(context) * 0.37f, SharedPreferencesUtils.getWidth(context) * 0.50f, SharedPreferencesUtils.getWidth(context) * 0.75f, SharedPreferencesUtils.getWidth(context)};

        if (SharedPreferencesUtils.containsMood(context)) {
            moodData.add(new MoodData(" ", SharedPreferencesUtils.getMessage(context), tableBackgroundColor[SharedPreferencesUtils.getMood(context)], tableWidthMood[SharedPreferencesUtils.getMood(context)]));
        } else {
            //Todo peut etre afficher la couleur du texte en blanc ?
            moodData.add(new MoodData(" ", "Aucune humeur enregistré", R.color.color_empty, SharedPreferencesUtils.getWidth(context)));
        }

        SharedPreferencesUtils.saveArrayList(context);
        resetMood(context);
    }

    // resets mood and message in sharedPreferences
    public void resetMood(Context context) {
        SharedPreferencesUtils.removeMood(context, SharedPreferencesUtils.MY_FILE, SharedPreferencesUtils.KEY_MOOD);
        SharedPreferencesUtils.removeMood(context, SharedPreferencesUtils.MY_FILE, SharedPreferencesUtils.KEY_MESSAGE);
    }

}
