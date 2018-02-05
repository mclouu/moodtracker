package com.romain.mathieu.moodtracker;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Hashtable;

public class HistoryActivity extends AppCompatActivity {

    private ArrayList<MoodData> moodData;
    Hashtable widthMood;
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
        widthMood = new Hashtable();
        widthMood.put("supperhappy", 480);
        widthMood.put("happy", 380);
        widthMood.put("normal", 260);
        widthMood.put("disappointed", 140);
        widthMood.put("sad", 88);


        moodData = new ArrayList<>();
        moodData.add(new MoodData("Hier", "Aujourd'hui j'ai bu un très bon vin !", R.color.color_superhappy, (int) widthMood.get("supperhappy")));
        moodData.add(new MoodData("Avant-hier", "", R.color.color_happy, (int) widthMood.get("happy")));
        moodData.add(new MoodData("Il y a trois jours", "Bonne journée, mais rien d'original", R.color.color_normal, (int) widthMood.get("normal")));
        moodData.add(new MoodData("Il y a quatre jours", "", R.color.color_disappointed, (int) widthMood.get("disappointed")));
        moodData.add(new MoodData("Il y a cinq jours", "Ce vin était vraiment pas bon !", R.color.color_sad, (int) widthMood.get("sad")));
        moodData.add(new MoodData("Il y a six jours", "trop bon la pizza", R.color.color_superhappy, (int) widthMood.get("supperhappy")));
        moodData.add(new MoodData("Il y a une semaine", "trop bon la pizza", R.color.color_superhappy, (int) widthMood.get("supperhappy")));
    }
}
