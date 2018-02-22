package com.romain.mathieu.moodtracker.Controller;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.romain.mathieu.moodtracker.Model.MoodData;
import com.romain.mathieu.moodtracker.Model.MyBroadcastReceiver;
import com.romain.mathieu.moodtracker.Model.SharedPreferencesUtils;
import com.romain.mathieu.moodtracker.Model.SwipeDirectionListener;
import com.romain.mathieu.moodtracker.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    public static int mood;

    // Variable View
    ImageButton btnHistory;
    ImageButton btnAddMessage;
    RelativeLayout layout;

    // Variable ArrayList and Hashtable
    public static ArrayList<MoodData> moodData;
    public static Hashtable<Integer, Float> widthMood;
    public static Hashtable<Integer, Integer> colorMood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

// findViewById ------------------------------------------------------------
        btnAddMessage = findViewById(R.id.btn_add_message);
        btnHistory = findViewById(R.id.btn_history);
// findViewById ------------------------------------------------------------

        btnAddMessage.setOnClickListener(this);
        btnHistory.setOnClickListener(this);

        swipe();
        initializeData();
        AlarmMidnight(this);
    }


    // When we swipe, this method (up = mood++ or down = mood--) and change imgSmiley and BackgroundColor depending on mood value.
    public void swipe() {
        layout = findViewById(R.id.main_activity_layout);

        layout.setOnTouchListener(new SwipeDirectionListener(MainActivity.this) {
            ImageView imgSmiley = findViewById(R.id.img_smiley);
            RelativeLayout layout = findViewById(R.id.main_activity_layout);

            int tableauImg[] = {R.drawable.smileysad, R.drawable.smileydisappointed, R.drawable.smileynormal, R.drawable.smileyhappy, R.drawable.smileysuperhappy};
            int tableauBackground[] = {R.color.color_sad, R.color.color_disappointed, R.color.color_normal, R.color.color_happy, R.color.color_superhappy};


            public void onUpSwipe() {
                if (mood != 4) {
                    mood++;
                }
                imgSmiley.setImageResource(tableauImg[mood]);
                layout.setBackgroundResource(tableauBackground[mood]);

            }

            public void onDownSwipe() {
                if (mood != 0) {
                    mood--;
                }
                imgSmiley.setImageResource(tableauImg[mood]);
                layout.setBackgroundResource(tableauBackground[mood]);

            }

        });


    }

    // This method opens a popup to write a note
    public void showDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        @SuppressLint("InflateParams")
        // https://possiblemobile.com/2013/05/layout-inflation-as-intended/
        final View dialogView = inflater.inflate(R.layout.popup_add_message, null);
        dialogBuilder.setView(dialogView);

        final EditText editTextMessage = dialogView.findViewById(R.id.edittext_message);

        dialogBuilder.setTitle("Ajouter une note");
        dialogBuilder.setPositiveButton("valider", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                SharedPreferencesUtils.saveMessage(MainActivity.this, editTextMessage.getText().toString());
            }
        });
        dialogBuilder.setNegativeButton("annuler", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

    // This method listens if you click on one of the two buttons
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_message:
                showDialog();
                break;
            case R.id.btn_history:
                Intent myIntent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(myIntent);
                break;
        }
    }

    /*
    This method initializes the mood value if a backup exists otherwise mood = 3
    also initializes arrayList moodData and Hashtable widthMood and colorMood
    */
    private void initializeData() {

        if (SharedPreferencesUtils.containsMood(this)) {
            mood = SharedPreferencesUtils.getMood(this);
        } else {
            mood = 3;
        }

        moodData = new ArrayList<>();
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        widthMood = new Hashtable<>();
        widthMood.put(4, (float) metrics.widthPixels); // is supperhappy
        widthMood.put(3, (float) metrics.widthPixels * 0.75f); // is happy
        widthMood.put(2, (float) metrics.widthPixels * 0.50f); // is normal
        widthMood.put(1, (float) metrics.widthPixels * 0.37f); // is disappointed
        widthMood.put(0, (float) metrics.widthPixels * 0.25f); //is sad

        colorMood = new Hashtable<>();
        colorMood.put(4, R.color.color_superhappy);
        colorMood.put(3, R.color.color_happy);
        colorMood.put(2, R.color.color_normal);
        colorMood.put(1, R.color.color_disappointed);
        colorMood.put(0, R.color.color_sad);


    }

    //This method executes the code in MyBroadcastReceiver at midnight
    private void AlarmMidnight(Context context) {

        AlarmManager alarmManager;
        PendingIntent pendingIntent;

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DATE, 1);

        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, MyBroadcastReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);


        if (alarmManager != null) {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }
    }
}


