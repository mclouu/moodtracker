package com.romain.mathieu.moodtracker.Model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by romain on 20/02/2018.
 */

public class SilenceBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, "il est minuit héhé", Toast.LENGTH_SHORT).show();

    }
}
