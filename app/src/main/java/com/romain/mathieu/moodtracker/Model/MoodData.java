package com.romain.mathieu.moodtracker.Model;

import java.util.Hashtable;

/**
 * Created by Romain on 04/02/2018.
 */

public class MoodData {
    public String time;
    public String message;
    public float sizeCard;
    public int colorCard;
    public Hashtable dayAgo;


    public MoodData(String time, String message, int colorCard, float sizeCard) {
        this.time = time;
        this.message = message;
        this.colorCard = colorCard;
        this.sizeCard = sizeCard;


    }


    public String getDate(int time) {
        dayAgo = new Hashtable();
        dayAgo.put(7, "Il y a une semaine");
        dayAgo.put(6, "Il y a 6 jours");
        dayAgo.put(5, "Il y a 5 jours");
        dayAgo.put(4, "Il y a 4 jours");
        dayAgo.put(3, "Il y a 3 jours");
        dayAgo.put(2, "Avant-hier");
        dayAgo.put(1, "Hier");

        return (String) dayAgo.get(time);
    }
}