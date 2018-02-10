package com.romain.mathieu.moodtracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int mood;

    ImageButton btnHistory;
    ImageButton btnAddMessage;
    //test variable
    Button btnMidNight;
    TextView tvMessage;
    TextView tvMood;


    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferencesUtils.saveMood(this, mood);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




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
            tvMessage = findViewById(R.id.tv_test_message);
            tvMood = findViewById(R.id.tv_test_mood);
            tvMessage.setText(SharedPreferencesUtils.getMessage(this));
            tvMood.setText(String.valueOf(SharedPreferencesUtils.getMood(this)));


        }
    }

}


