package com.mathieu.romain.moodtracker;

import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Romain on 26-Jan-18.
 */

public class SwipeDirectionListener implements View.OnTouchListener {

    float deltaY;
    float maxValY;
    float firstTouchY;
    float currentY;
    float SWIPE_THRESHOLD = 80;


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        boolean result;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                //Register the first touch on TouchDown and this should not change unless finger goes up.
                firstTouchY = event.getY();
                maxValY = 0.0f;

                //As the event is consumed, return true
                result = true;
                break;

            case MotionEvent.ACTION_MOVE:

                //CurrentX/Y are the continues changing values of one single touch session. Change
                //when finger slides on view
                currentY = event.getY();

                //setting the maximum value of X or Y so far. Any deviation in this means a  change of direction so reset the firstTouch value to last known max value i.e MaxVal X/Y.
                if (maxValY < currentY) {
                    maxValY = currentY;
                } else {
                    firstTouchY = maxValY;
                    maxValY = 0.0f;
                }

                //DeltaX/Y are the difference between current touch and the value when finger first touched screen.
                //If its negative that means current value is on down side of first touchdown value i.e Going down
                deltaY = currentY - firstTouchY;

                //vertical swipe

                if (Math.abs(deltaY) > SWIPE_THRESHOLD) {
                    if (deltaY > 0) {
                        // swipe down
                        onDownSwipe(Math.abs(deltaY));
                    } else {
                        //swipe up
                        onUpSwipe(Math.abs(deltaY));
                    }
                }
                result = true;
                break;

            case MotionEvent.ACTION_UP:
                //Clean UP
                firstTouchY = 0.0f;
                result = true;
                break;

            default:
                result = false;
                break;
        }

        return result;
    }


    //CONSTRUCTOR

    public void onUpSwipe(float value) {

    }

    public void onDownSwipe(float value) {

    }
}
