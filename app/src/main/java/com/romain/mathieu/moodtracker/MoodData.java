package com.romain.mathieu.moodtracker;

/**
 * Created by Romain on 04/02/2018.
 */

public class MoodData {
    String time;
    String message;
    float sizeCard;
    int colorCard;


    public MoodData(String time, String message, int colorCard, float sizeCard) {
        this.time = time;
        this.message = message;
        this.colorCard = colorCard;
        this.sizeCard = sizeCard;


    }
}
