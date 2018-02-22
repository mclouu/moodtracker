package com.romain.mathieu.moodtracker.Model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import static com.romain.mathieu.moodtracker.Controller.MainActivity.colorMood;
import static com.romain.mathieu.moodtracker.Controller.MainActivity.moodData;
import static com.romain.mathieu.moodtracker.Controller.MainActivity.widthMood;

/**
 * Created by romain on 20/02/2018.
 */

public class SilenceBroadcastReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        moodData.add(new MoodData(" ", SharedPreferencesUtils.getMessage(this), (int) colorMood.get(SharedPreferencesUtils.getMood(this)), (Float) widthMood.get(SharedPreferencesUtils.getMood(this))));
        Toast.makeText(context, "Humeur mémorisé", Toast.LENGTH_SHORT).show();
        System.out.println("Humeur mémorisé");

    }

}
