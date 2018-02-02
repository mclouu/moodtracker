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
                int humeur = 3;


                public void onUpSwipe(float value) {
                    if (humeur < 4) {
                        humeur++;
                    }
                    tv.setText("UpSwipe " + humeur);
                }

                public void onDownSwipe(float value) {
                    tv.setText("DownSwipe " + humeur);
                    if (humeur > 0) {
                        humeur--;
                    }
                }
            });
        }


    }

