package com.mathieu.romain.moodtracker;

import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Romain on 26-Jan-18.
 */

public class SwipeDirectionListener implements View.OnTouchListener {

    float deltaX;
    float deltaY;
    float maxValX;
    float maxValY;
    float firstTouchX;
    float firstTouchY;
    float currentX;
    float currentY;
    float SWIPE_THRESHOLD = 10.0f;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        boolean result;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //Register the first touch on TouchDown and this should not change unless finger goes up.
                firstTouchX = event.getX();
                firstTouchY = event.getY();
                maxValX = 0.0f;
                maxValY = 0.0f;
                //As the event is consumed, return true
                result = true;
                break;

            case MotionEvent.ACTION_MOVE:
                //CurrentX/Y are the continues changing values of one single touch session. Change
                //when finger slides on view
                currentX = event.getX();
                currentY = event.getY();
                //setting the maximum value of X or Y so far. Any deviation in this means a  change of direction so reset the firstTouch value to last known max value i.e MaxVal X/Y.
                if (maxValX < currentX) {
                    maxValX = currentX;
                } else {
                    firstTouchX = maxValX;
                    maxValX = 0.0f;
                }

                if (maxValY < currentY) {
                    maxValY = currentY;
                } else {
                    firstTouchY = maxValY;
                    maxValY = 0.0f;
                }
                //DeltaX/Y are the difference between current touch and the value when finger first touched screen.
                //If its negative that means current value is on left side of first touchdown value i.e Going left and
                //vice versa.
                deltaX = currentX - firstTouchX;
                deltaY = currentY - firstTouchY;


                //vertical swipe
                if (Math.abs(deltaY) > SWIPE_THRESHOLD) {
                    if (deltaY > 0) {
                        // swipe down
                        onDownSwipe(currentY);
                    } else {
                        //swipe up
                        onUpSwipe(currentY);
                    }
                }

                result = true;
                break;

            case MotionEvent.ACTION_UP:
                //Clean UP
                firstTouchX = 0.0f;
                firstTouchY = 0.0f;

                result = true;
                break;

            default:
                result = false;
                break;
        }

        return result;
    }


    //constructeur

    public void onUpSwipe(float value) {

    }

    public void onDownSwipe(float value) {

    }
}
