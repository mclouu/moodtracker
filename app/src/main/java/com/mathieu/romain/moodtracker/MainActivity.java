package com.mathieu.romain.moodtracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int mood = 3;
    ImageView imgSmiley;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipe();

        imgSmiley = findViewById(R.id.img_smiley);
        switch (mood) {
            case 0:
                imgSmiley.setImageResource(R.drawable.smileysad);
                break;
            case 1:
                imgSmiley.setImageResource(R.drawable.smileydisappointed);
                break;
            case 2:
                imgSmiley.setImageResource(R.drawable.smileynormal);
                break;
            case 3:
                imgSmiley.setImageResource(R.drawable.smileyhappy);
                break;
            case 4:
                imgSmiley.setImageResource(R.drawable.smileysuperhappy);
                break;
            default:
                imgSmiley.setImageResource(R.drawable.smileyhappy);
                break;
        }
    }

    public void swipe() {
        // RelativeLayout layout = findViewById(R.id.main_activity_layout);
        ImageView imgSmiley = findViewById(R.id.img_smiley);

        imgSmiley.setOnTouchListener(new SwipeDirectionListener() {

            TextView tv = findViewById(R.id.tv);


            public void onUpSwipe(float value) {
                if (mood < 4) {
                    mood++;
                }
                tv.setText("UpSwipe " + mood + " - " + value);
            }

            public void onDownSwipe(float value) {
                tv.setText("DownSwipe " + mood + " - " + value);
                if (mood > 0) {
                    mood--;
                }
            }
        });
    }
}

