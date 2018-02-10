package com.romain.mathieu.moodtracker;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;

import java.util.ArrayList;
import java.util.Hashtable;

public class HistoryActivity extends AppCompatActivity {

    private ArrayList<MoodData> moodData;
    Hashtable widthMood;
    Hashtable colorMood;
    RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);



        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);

        initializeData();

        MyAdapter adapter = new MyAdapter(moodData);
        recyclerView.setAdapter(adapter);
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


        moodData = new ArrayList<>();
        moodData.add(new MoodData("Hier", SharedPreferencesUtils.getMessage(this), (int) colorMood.get(SharedPreferencesUtils.getMood(this)), (Float) widthMood.get(SharedPreferencesUtils.getMood(this))));
    }
}
