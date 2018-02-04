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

public class HistoryActivity extends AppCompatActivity {

    private ArrayList<MoodData> moodData;
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
        moodData = new ArrayList<>();
        moodData.add(new MoodData("TDB", "trop bon la pizza", R.color.color_superhappy));
        moodData.add(new MoodData("Avant-hier", "trop bon la pizza", R.color.color_happy));
        moodData.add(new MoodData("Il y a un jours", "trop bon la pizza", R.color.color_disappointed));
        moodData.add(new MoodData("Il y a deux jours", "trop bon la pizza", R.color.color_normal));
        moodData.add(new MoodData("Il y a trois jours", "trop bon la pizza", R.color.color_sad));
        moodData.add(new MoodData("Il y a quatre jours", "trop bon la pizza", R.color.color_superhappy));
        moodData.add(new MoodData("Il y a cinq jours", "trop bon la pizza", R.color.color_superhappy));
        moodData.add(new MoodData("Il y a six jours", "trop bon la pizza", R.color.color_superhappy));
        moodData.add(new MoodData("Il y a une semaine", "trop bon la pizza", R.color.color_superhappy));
    }
}
