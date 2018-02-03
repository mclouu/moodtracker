package com.romain.mathieu.moodtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int mood = 3;
    ImageView imgSmiley;
    ImageButton btnHistory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgSmiley = findViewById(R.id.img_smiley);
        swipe();


        btnHistory = findViewById(R.id.btn_history);
        btnHistory.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(myIntent);
            }
        });


    }

    public void swipe() {
        RelativeLayout layout = findViewById(R.id.main_activity_layout);


        layout.setOnTouchListener(new SwipeDirectionListener(MainActivity.this) {
            ImageView imgSmiley = findViewById(R.id.img_smiley);
            RelativeLayout layout = findViewById(R.id.main_activity_layout);
            TextView tv = findViewById(R.id.tv);

            int tableauImg[] = {R.drawable.smileysad, R.drawable.smileydisappointed, R.drawable.smileynormal, R.drawable.smileyhappy, R.drawable.smileysuperhappy};
            int tableauBackground[] = {R.color.Background_sad, R.color.Background_disappointed, R.color.Background_normal, R.color.Background_happy, R.color.Background_superhappy};

            public void onUpSwipe() {
                if (mood != 4) {
                    mood++;
                }
                imgSmiley.setImageResource(tableauImg[mood]);
                layout.setBackgroundResource(tableauBackground[mood]);
                tv.setText("UpSwipe " + mood);

            }

            public void onDownSwipe() {
                if (mood != 0) {
                    mood--;
                }
                imgSmiley.setImageResource(tableauImg[mood]);
                layout.setBackgroundResource(tableauBackground[mood]);
                tv.setText("DownSwipe " + mood);

            }

        });

    }
}

