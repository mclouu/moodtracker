package com.mathieu.romain.moodtracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView imgSmiley;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgSmiley = findViewById(R.id.img_smiley);

        ImageView imgSmiley = findViewById(R.id.img_smiley);

        imgSmiley.setOnTouchListener(new SwipeDirectionListener() {

            TextView tv = findViewById(R.id.tv);


            public void onUpSwipe(float value) {
                tv.setText("UpSwipe");
            }

            public void onDownSwipe(float value) {
                tv.setText("DownSwipe");
            }
        });


    }


}

