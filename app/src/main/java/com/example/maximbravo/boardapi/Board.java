package com.example.maximbravo.boardapi;

import android.app.Activity;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Maxim Bravo on 12/17/2016.
 */

public class Board {
    //Activity that called the class
    private Activity parentActivity;

    //parent view group
    private ViewGroup parentViewGroup;

    //parent view
    private int parentViewWidth;
    public Board (Activity activity, ViewGroup viewGroup, int screenWidth){

        parentActivity = activity;

        parentViewGroup = viewGroup;

        parentViewWidth = screenWidth;

        makeTestView();
    }

    public void makeTestView (){


        TextView newTV = new TextView(parentActivity.getApplicationContext());
        newTV.setText("Over here.");
        newTV.setTextColor(Color.MAGENTA);
        //sixth of screen
        newTV.setHeight(parentViewWidth/6);
        //sixth of screen
        newTV.setWidth(parentViewWidth/6);
        newTV.setBackgroundColor(Color.BLUE);
        parentViewGroup.addView(newTV);

    }

    public int dpToPixel(int dp){
        //Found code on stackOverflow http://stackoverflow.com/questions/8309354/formula-px-to-dp-dp-to-px-android
        DisplayMetrics displayMetrics = parentActivity.getApplicationContext().getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }
}
