package com.romain.mathieu.moodtracker.Controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.romain.mathieu.moodtracker.Model.MoodData;
import com.romain.mathieu.moodtracker.View.MyAdapter;
import com.romain.mathieu.moodtracker.R;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static com.romain.mathieu.moodtracker.Model.SharedPreferencesUtils.MY_FILE;


public class HistoryActivity extends AppCompatActivity {


    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


        getArrayList();


        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setReverseLayout(true);
        recyclerView.setLayoutManager(llm);


        MyAdapter adapter = new MyAdapter(MainActivity.moodData);
        recyclerView.setAdapter(adapter);
    }

    public void getArrayList() {
        SharedPreferences sharedPreferences = getSharedPreferences(MY_FILE, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("moodDataList", null);
        Type type = new TypeToken<ArrayList<MoodData>>() {
        }.getType();
        MainActivity.moodData = gson.fromJson(json, type);
    }

}
