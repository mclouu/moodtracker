package com.romain.mathieu.moodtracker;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Hashtable;

import static com.romain.mathieu.moodtracker.SharedPreferencesUtils.MY_FILE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int mood;

    ImageButton btnHistory;
    ImageButton btnAddMessage;
    //test variable
    Button btnMidNight;
    // array varible
    public static ArrayList<MoodData> moodData;
    Hashtable widthMood;
    Hashtable colorMood;
    Hashtable position;
    int time = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        moodData = new ArrayList<>();

        getArrayList();


        mood = SharedPreferencesUtils.getMood(this);

        ImageView imgSmiley = findViewById(R.id.img_smiley);
        RelativeLayout layout = findViewById(R.id.main_activity_layout);
        int tableauImg[] = {R.drawable.smileysad, R.drawable.smileydisappointed, R.drawable.smileynormal, R.drawable.smileyhappy, R.drawable.smileysuperhappy};
        int tableauBackground[] = {R.color.color_sad, R.color.color_disappointed, R.color.color_normal, R.color.color_happy, R.color.color_superhappy};
        imgSmiley.setImageResource(tableauImg[mood]);
        layout.setBackgroundResource(tableauBackground[mood]);


        btnAddMessage = findViewById(R.id.btn_add_message);
        btnHistory = findViewById(R.id.btn_history);
        btnMidNight = findViewById(R.id.btn_midnight);

        btnAddMessage.setOnClickListener(this);
        btnHistory.setOnClickListener(this);
        btnMidNight.setOnClickListener(this);

        swipe();


        initializeData();


    }


    public void swipe() {
        RelativeLayout layout = findViewById(R.id.main_activity_layout);

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

    public void showDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
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

    @Override
    public void onClick(View view) {
        if (btnAddMessage == view) {
            showDialog();
        } else if (btnHistory == view) {
            Intent myIntent = new Intent(MainActivity.this, HistoryActivity.class);
            startActivity(myIntent);
        } else if (btnMidNight == view) {
            Toast.makeText(getApplication(), "il est minuit héhé", Toast.LENGTH_SHORT).show();
            SharedPreferencesUtils.saveMood(this, mood);
            moodData.add(new MoodData((String) position.get(time), SharedPreferencesUtils.getMessage(this), (int) colorMood.get(SharedPreferencesUtils.getMood(this)), (Float) widthMood.get(SharedPreferencesUtils.getMood(this))));


        }
    }

    private void initializeData() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        widthMood = new Hashtable();
        widthMood.put(4, (float) metrics.widthPixels); // is supperhappy
        widthMood.put(3, (float) metrics.widthPixels * 0.75f); // is happy
        widthMood.put(2, (float) metrics.widthPixels * 0.50f); // is normal
        widthMood.put(1, (float) metrics.widthPixels * 0.37f); // is disappointed
        widthMood.put(0, (float) metrics.widthPixels * 0.25f); //is sad

        colorMood = new Hashtable();
        colorMood.put(4, R.color.color_superhappy);
        colorMood.put(3, R.color.color_happy);
        colorMood.put(2, R.color.color_normal);
        colorMood.put(1, R.color.color_disappointed);
        colorMood.put(0, R.color.color_sad);

        position = new Hashtable();
        position.put(6, "Il y a une semaine");
        position.put(5, "Il y a 6 jours");
        position.put(4, "Il y a 5 jours");
        position.put(3, "Il y a 4 jours");
        position.put(2, "Il y a 3 jours");
        position.put(1, "Avant-hier");
        position.put(0, "Hier");


    }

    public void saveArrayList() {
        SharedPreferences sharedPreferences = getSharedPreferences(MY_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(moodData);
        editor.putString("moodDataList", json);
        editor.apply();
    }

    public void getArrayList() {
        SharedPreferences sharedPreferences = getSharedPreferences(MY_FILE, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("moodDataList", null);
        Type type = new TypeToken<ArrayList<MoodData>>() {
        }.getType();
        moodData = gson.fromJson(json, type);
    }


    @Override
    protected void onPause() {
        super.onPause();
        saveArrayList();
    }
}


