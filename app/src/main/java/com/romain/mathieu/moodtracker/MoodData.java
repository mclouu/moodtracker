package com.romain.mathieu.moodtracker;

/**
 * Created by Romain on 04/02/2018.
 */

public class MoodData {
    String time;
    String message;
    //int messageIcons;
    //int sizeCard;
    int colorCard;


    public MoodData(String time, String message, int colorCard) {
        this.time = time;
        this.message = message;
        this.colorCard = colorCard;

        // this.messageIcons = messageIcons;
        // this.sizeCard = sizeCard;

    }
}
