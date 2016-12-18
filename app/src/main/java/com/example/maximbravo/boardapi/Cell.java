package com.example.maximbravo.boardapi;

import android.graphics.drawable.Drawable;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Maxim Bravo on 12/17/2016.
 */

public class Cell {
    private Drawable cellBackground;



    private int cellXPos;


    private int cellYPos;

    private int cellWidth;
    private int cellHeight;

    private int cellResId;
    public Cell(){

    }

    public void parseTextView(TextView textView){
        cellResId = textView.getId();
        cellBackground = textView.getBackground();

    }

    public void addXAndYPos(int x, int y){
        cellXPos = x;
        cellYPos = y;
    }

    public void addWidthAndHeight(int width, int height){
        cellWidth = width;
        cellHeight = height;
    }
    public Drawable getCellBackground() {
        return cellBackground;
    }

    public int getCellXPos() {
        return cellXPos;
    }

    public int getCellWidth() {
        return cellWidth;
    }

    public int getCellHeight() {
        return cellHeight;
    }

    public int getCellResId() {
        return cellResId;
    }

    public int getCellYPos() {
        return cellYPos;
    }

}
