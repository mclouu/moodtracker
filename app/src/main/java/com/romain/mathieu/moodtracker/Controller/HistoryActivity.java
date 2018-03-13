package com.romain.mathieu.moodtracker.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.romain.mathieu.moodtracker.Model.SharedPreferencesUtils;
import com.romain.mathieu.moodtracker.R;
import com.romain.mathieu.moodtracker.View.MyAdapter;


public class HistoryActivity extends AppCompatActivity {


    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


        if (SharedPreferencesUtils.containsArrayList(this)){
            SharedPreferencesUtils.getArrayList(this);
        }else{
            Toast.makeText(this, "Vous n'avez pas encore d'historique :(", Toast.LENGTH_LONG).show();
        }



        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setReverseLayout(true);
        recyclerView.setLayoutManager(llm);


        MyAdapter adapter = new MyAdapter(MainActivity.moodData);
        recyclerView.setAdapter(adapter);
    }


}
