package com.mathieu.romain.moodtracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView imgSmiley;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RelativeLayout layout = findViewById(R.id.main_activity_layout);


            layout.setOnTouchListener(new SwipeDirectionListener() {

                TextView tv = findViewById(R.id.tv);
                int mood = 3;




                public void onUpSwipe(float value) {
                    if (mood < 4) {
                        mood++;
                    }
                    tv.setText("UpSwipe " + mood);
                }

                public void onDownSwipe(float value) {
                    tv.setText("DownSwipe " + mood);
                    if (mood > 0) {
                        mood--;
                    }
                }
            });
        }


    }

