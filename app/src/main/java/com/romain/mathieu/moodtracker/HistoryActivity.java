package com.romain.mathieu.moodtracker;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public class HistoryActivity extends AppCompatActivity {

    private ArrayList<MoodData> moodData;
            Hashtable widthMood;
            RecyclerView recyclerView;

            CardView cardView;
            String message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);





        recyclerView =  findViewById(R.id.recyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);

        initializeData();

        MyAdapter adapter = new MyAdapter(moodData);
        recyclerView.setAdapter(adapter);
    }

    private void initializeData() {
        widthMood = new Hashtable();
        widthMood.put("supperhappy", 480);
        widthMood.put("happy", 380);
        widthMood.put("normal", 260);
        widthMood.put("disappointed", 140);
        widthMood.put("sad", 88);


        moodData = new ArrayList<>();
        moodData.add(new MoodData("Hier", "trop bon le vin", R.color.color_superhappy, (int) widthMood.get("supperhappy"), true));
        moodData.add(new MoodData("Avant-hier", "", R.color.color_happy, (int) widthMood.get("happy"),false));
        moodData.add(new MoodData("Il y a trois jours", "TDB", R.color.color_normal, (int) widthMood.get("normal"),true));
        moodData.add(new MoodData("Il y a quatre jours", "", R.color.color_disappointed, (int) widthMood.get("disappointed"),false));
        moodData.add(new MoodData("Il y a cinq jours", "pas bon ce vin", R.color.color_sad, (int) widthMood.get("sad"),true));
        moodData.add(new MoodData("Il y a six jours", "trop bon la pizza", R.color.color_superhappy, (int) widthMood.get("supperhappy"),true));
        moodData.add(new MoodData("Il y a une semaine", "trop bon la pizza", R.color.color_superhappy, (int) widthMood.get("supperhappy"),true));
    }
}
