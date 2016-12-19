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

    private int cellMargin;
    private int cellWidth;
    private int cellHeight;

    private int cellResId;

    private int cellBackgroundResId;
    public static final int STATE_RED_ON_BLACK = 0;
    public static final int STATE_BLACK_ON_RED = 1;
    public static final int STATE_BLACK = 2;
    public static final int STATE_RED = 3;
    public static final int NONE = 4;
    public static final int STATE_BLACK_ON_BLACK = 5;
    public static final int STATE_RED_ON_RED = 6;

    private int cellState;
    public Cell(){

    }

    public void addCellBackground(int drawable){
        cellBackgroundResId = drawable;
    }
    public void parseTextView(TextView textView){
        cellResId = textView.getId();
        //cellBackground = textView.getBackground();

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

    public void addMargin(int margin){
        cellMargin = margin;
    }

    public void addState(int state){
        cellState = state;
        switch(cellState){
            case STATE_BLACK:
                addCellBackground(R.drawable.black);
                break;
            case STATE_RED:
                addCellBackground(R.drawable.red);
                break;
            case STATE_BLACK_ON_BLACK:
                    addCellBackground(R.drawable.blackonblack);
                break;
            case STATE_RED_ON_RED:
                addCellBackground(R.drawable.redonred);
                break;
            case STATE_BLACK_ON_RED:
                addCellBackground(R.drawable.blackonred);
                break;
            case STATE_RED_ON_BLACK:
                addCellBackground(R.drawable.redonblack);
                break;
        }
    }

    public int getState(){
        return cellState;
    }

}
