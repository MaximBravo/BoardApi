package com.example.maximbravo.boardapi;

import android.app.Activity;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
    private int parentViewHeight;

    private int boardWidth;
    private int boardHeight;

    private int cellSideLength;
    private Cell[][] board;
    public Board (Activity activity, ViewGroup viewGroup, int resid, int width, int height, int screenWidth, int screenHeight, int csl){

        parentActivity = activity;

        parentViewGroup = (ViewGroup) parentActivity.findViewById(resid);

        parentViewWidth = screenWidth;

        parentViewHeight = (int) (screenHeight - parentViewGroup.getY());

        boardWidth = width;

        boardHeight = height;

        board = new Cell[boardWidth][boardHeight];

        cellSideLength = csl;

        makeBoard();
    }

    public void makeBoard(){
        //minumum dimension
        int minDimension = Math.min(parentViewHeight, parentViewWidth);

//        double csl = ((minDimension/(boardWidth+1)) *17) /20  ;
//
//        int cellSideLength = (int) csl;
        int margin = cellSideLength/20;

        int actualCellSideLength = (int) (cellSideLength + (2*margin));
        //main linear layout
        LinearLayout boardLinear = new LinearLayout(parentActivity.getApplicationContext());
        boardLinear.setOrientation(LinearLayout.VERTICAL);

        //id starting from one for ids for textviews
        int count = 1;

        int rowLength = 0;
        //loops through rows
        for(int rows = 0; rows < boardWidth; rows++) {
            //linear layout for row
            LinearLayout rowLinear = new LinearLayout(parentActivity.getApplicationContext());
            rowLinear.setOrientation(LinearLayout.HORIZONTAL);
            //loops through columns
            for(int columns = 0; columns < boardHeight; columns++){
                //current cell
                Cell currentCell = new Cell();
                //the new textView to add
                TextView cell = new TextView(parentActivity.getApplicationContext());
                cell.setHeight(cellSideLength);
                cell.setWidth(cellSideLength);
                cell.setBackgroundColor(Color.RED);
                //params for textView margin
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(actualCellSideLength, actualCellSideLength);
                params.setMargins(margin, margin, margin, margin);

                cell.setLayoutParams(params);
                cell.setId(count);

                currentCell.parseTextView(cell);
                currentCell.addWidthAndHeight(actualCellSideLength, actualCellSideLength);

                String arrayPosString = convertIdToPostion(count);
                String[] arrayPos = arrayPosString.split("-");
                int arrayX = Integer.parseInt(arrayPos[0]);
                int arrayY = Integer.parseInt(arrayPos[1]);
                board[arrayX][arrayY] = currentCell;

                rowLinear.addView(cell);
                count++;
            }
            rowLength = rowLinear.getWidth();
            boardLinear.addView(rowLinear);
        }


        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        boardLinear.setLayoutParams(params);
        boardLinear.setId(count);

        boardLinear.setBackgroundColor(Color.BLACK);
        parentViewGroup.addView(boardLinear);

    }
    public String convertIdToPostion(int id){
        int x = (id-1)%boardWidth;
        int y = (id-1)/boardWidth;
        String result = x + "-" + y;
        return result;
    }
    public void makeTestView (){


        TextView newTV = new TextView(parentActivity.getApplicationContext());
        newTV.setText("Over here.");
        newTV.setTextColor(Color.MAGENTA);

        int minDimension = Math.min(parentViewHeight, parentViewWidth);
        //sixth of screen
        newTV.setHeight(minDimension/6);
        //sixth of screen
        newTV.setWidth(minDimension/6);
        newTV.setBackgroundColor(Color.BLUE);
        parentViewGroup.addView(newTV);

    }

    public int dpToPixel(int dp){
        //Found code on stackOverflow http://stackoverflow.com/questions/8309354/formula-px-to-dp-dp-to-px-android
        DisplayMetrics displayMetrics = parentActivity.getApplicationContext().getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }
}
