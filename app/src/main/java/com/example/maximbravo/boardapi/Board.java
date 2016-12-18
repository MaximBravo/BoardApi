package com.example.maximbravo.boardapi;

import android.app.Activity;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Random;

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

    private int cellWidth;

    private int statusBarHeight;
    private int cellSideLength;
    private Cell[][] board;
    public Board (Activity activity, ViewGroup viewGroup, int resid, int width, int height, int screenWidth, int screenHeight, int csl, int statusbarheight){

        parentActivity = activity;

        parentViewGroup = (ViewGroup) parentActivity.findViewById(resid);

        parentViewWidth = screenWidth;

        parentViewHeight = screenHeight;

        boardWidth = width;

        boardHeight = height;

        statusBarHeight = statusbarheight;
        board = new Cell[boardWidth][boardHeight];

        cellSideLength = csl;

        makeBoard();
    }

    private float extraTop;
    public Board(Activity activity, int width, int height, int cWidth){

        boardWidth = width;
        boardHeight = height;
        board = new Cell[boardWidth][boardHeight];

        cellWidth = cWidth;

        parentActivity = activity;

        parentViewGroup = (ViewGroup) parentActivity.findViewById(R.id.content_main);
        extraTop = parentViewGroup.getY();
        makeBoard();
    }

    private static final int fraction = 20;
    public void makeBoard(){

        final float scale = parentActivity.getApplicationContext().getResources().getDisplayMetrics().density;
        int pixels = (int) (cellSideLength * scale + 0.5f);
        //int margin = pixels/fraction;
        //margin for checkers
        int margin = 0;
        //int pixels = widthOfCell;
        LinearLayout boardLinear = new LinearLayout(parentActivity.getApplicationContext());
        boardLinear.setOrientation(LinearLayout.VERTICAL);
        int count = 1;
        boolean red = true;
        for(int rows = 0; rows < boardWidth; rows++) {
            LinearLayout l = new LinearLayout(parentActivity.getApplicationContext());
            l.setOrientation(LinearLayout.HORIZONTAL);
            for(int columns = 0; columns < boardHeight; columns++) {
                int entireWidth = pixels + (2*margin);
                Cell currentCell = new Cell();
                currentCell.addWidthAndHeight(entireWidth, entireWidth);
                currentCell.addMargin(margin);
                TextView right = new TextView(parentActivity.getApplicationContext());
                right.setHeight(pixels);
                right.setWidth(pixels);
                LinearLayout.LayoutParams templ = new LinearLayout.LayoutParams(pixels, pixels);
                templ.setMargins(margin, margin, margin, margin);
                right.setLayoutParams(templ);
                right.setId(count);
                Random rand = new Random();

                // nextInt is normally exclusive of the top value,
                // so add 1 to make it inclusive
                int randomNum = rand.nextInt((15 - 1) + 1) + 1;
                if(red) {
                    right.setBackgroundColor(Color.RED);
                    if(columns != boardWidth-1) {
                        red = false;
                    }
                } else {
                    right.setBackgroundColor(Color.BLACK);
                    if(columns != boardWidth-1) {
                        red = true;
                    }
                }
                l.addView(right);
                currentCell.parseTextView(right);
                count++;
                board[columns][rows] = currentCell;
            }

            //l.setLayoutParams(params);
            boardLinear.addView(l);

        }

        double paddingspacewidth = margin * (boardWidth * 2);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int)((pixels*boardWidth) + paddingspacewidth),(int) ((pixels*boardWidth) + paddingspacewidth));
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        boardLinear.setLayoutParams(params);
        boardLinear.setId(count);
        boardLinear.setBackgroundColor(Color.WHITE);
        parentViewGroup.addView(boardLinear);

        int boardWidthInPixels = (int)((pixels*boardWidth) + paddingspacewidth);


        int boardX = (parentViewWidth/2) - (boardWidthInPixels/2);
        int boardY = (parentViewHeight/2) - (boardWidthInPixels/2);
        count = 0;
        for(int index = 0; index < boardLinear.getChildCount(); ++index) {
            LinearLayout row = (LinearLayout) boardLinear.getChildAt(index);
            int rowHeight = boardWidthInPixels/boardWidth;
            for(int i = 0; i < row.getChildCount(); ++i) {
                View nextChild = row.getChildAt(i);
                Integer id = nextChild.getId();
                int childX = boardX+(i * (rowHeight ));
                int childY = boardY+(index * (rowHeight));

                String xypos = convertIdToPostion(id);
                String[] pos = xypos.split("-");
                int xpos = Integer.parseInt(pos[0]);
                int ypos = Integer.parseInt(pos[1]);
                Cell touchingCell = board[xpos][ypos];
                touchingCell.addXAndYPos(childX, childY + rowHeight);
            }
        }

    }


    private String tempSummary = "";

    public void addAllXYPositions(int boardId){



    }

    public int isOnView(int x, int y){
        for(int i = 0; i < boardWidth; i++){
            for(int j = 0; j < boardHeight; j++){
                Cell currentCell = board[j][i];
                int currentX = currentCell.getCellXPos();
                int currentY = currentCell.getCellYPos();
                int currentWidth = currentCell.getCellWidth();
                int currentHeight = currentCell.getCellHeight();
                if (x <= currentX + currentWidth &&
                        x >= currentX &&
                        y <= currentY + currentHeight + extraTop &&
                        y >= currentY + extraTop) {
                    return currentCell.getCellResId();
                }
            }
        }
        return -1;
    }



//    private int boardId;
//    public void makeBoardnew(){
//        //minumum dimension
//        int minDimension = Math.min(parentViewHeight, parentViewWidth);
//
////        double csl = ((minDimension/(boardWidth+1)) *17) /20  ;
////
////        int cellSideLength = (int) csl;
//        int margin = cellSideLength/20;
//
//        int actualCellSideLength = (int) (cellSideLength + (2*margin));
//        //main linear layout
//        LinearLayout boardLinear = new LinearLayout(parentActivity.getApplicationContext());
//        boardLinear.setOrientation(LinearLayout.VERTICAL);
//
//        //id starting from one for ids for textviews
//        int count = 1;
//
//        int rowLength = 0;
//        //loops through rows
//        for(int rows = 0; rows < boardWidth; rows++) {
//            //linear layout for row
//            LinearLayout rowLinear = new LinearLayout(parentActivity.getApplicationContext());
//            rowLinear.setOrientation(LinearLayout.HORIZONTAL);
//            //loops through columns
//            for(int columns = 0; columns < boardHeight; columns++){
//                //current cell
//                Cell currentCell = new Cell();
//                //the new textView to add
//                TextView cell = new TextView(parentActivity.getApplicationContext());
//                cell.setHeight(cellSideLength);
//                cell.setWidth(cellSideLength);
//                cell.setBackgroundColor(Color.RED);
//                //params for textView margin
//                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(actualCellSideLength, actualCellSideLength);
//                params.setMargins(margin, margin, margin, margin);
//
//                cell.setLayoutParams(params);
//                cell.setId(count);
//
//                currentCell.parseTextView(cell);
//                currentCell.addWidthAndHeight(actualCellSideLength, actualCellSideLength);
//
//                String arrayPosString = convertIdToPostion(count);
//                String[] arrayPos = arrayPosString.split("-");
//                int arrayX = Integer.parseInt(arrayPos[0]);
//                int arrayY = Integer.parseInt(arrayPos[1]);
//                board[arrayX][arrayY] = currentCell;
//
//                rowLinear.addView(cell);
//                count++;
//            }
//            rowLength = rowLinear.getWidth();
//            boardLinear.addView(rowLinear);
//        }
//
//
//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        params.addRule(RelativeLayout.CENTER_IN_PARENT);
//        boardLinear.setLayoutParams(params);
//        boardLinear.setId(count);
//
//        boardLinear.setBackgroundColor(Color.BLACK);
//        parentViewGroup.addView(boardLinear);
//        boardId = count;
//
//    }
//
//    public void addXandYPosnew(int takeInWidth, int takeInHeight){
//        View boardLayout = (View) parentViewGroup.findViewById(boardId);
//        Cell referenceCell = board[0][0];
//        int boardX = (int) (takeInWidth/2) - (referenceCell.getCellWidth()*(boardWidth/2));
//        int boardY = (int) (takeInHeight/2) - (referenceCell.getCellHeight()*(boardHeight/2));
//        for(int i = 0; i < boardWidth; i++){
//            for(int j = 0; j < boardHeight; j++){
//                Cell currentcell = board[i][j];
//                currentcell.addXAndYPos(boardX + (i * currentcell.getCellWidth()), boardY + (j * currentcell.getCellHeight()) + currentcell.getCellHeight() + statusBarHeight);
//            }
//        }
//    }
//
//    public void addXandYPosold(){
//        LinearLayout b = (LinearLayout) parentViewGroup.getChildAt(1);
//        HashMap<Integer, Integer[]> views = new HashMap<>();
//        int boardX = (int) b.getX();
//        int boardY = (int) b.getY();
//        int count = 0;
//        for(int index = 0; index < b.getChildCount(); ++index) {
//            LinearLayout row = (LinearLayout) b.getChildAt(index);
//            //int rowX = boardX;
//            //int rowY = boardY + (index * (row.getHeight()));
//            for(int i = 0; i < row.getChildCount(); ++i) {
//                Cell currentcell = board[index][i];
//                View nextChild = row.getChildAt(i);
//                Integer id = nextChild.getId();
//                int childX = boardX+(i * (currentcell.getCellWidth() ));
//                int childY = boardY+(index * (currentcell.getCellHeight()));
//
//                currentcell.addXAndYPos(childX, childY);
//                Integer[] idxywh = {id, childX, childY, row.getHeight(), row.getHeight()};
//                views.put(count, idxywh);
//                count++;
//            }
//        }
//    }
//
//    public String getParentHeightAndWidth(){
//        String result = "\n";
//        result += "\nParent Height: " + parentViewHeight;
//        result += "\nParent Width: " + parentViewWidth;
//        return result;
//    }
//    public int getViewTouchingId(int x, int y){
//        for(int row = 0; row < boardHeight; row++){
//            for(int column = 0; column < boardWidth; column++){
//                Cell currentCell = board[row][column];
//                int cellX = currentCell.getCellXPos();
//                int cellY = currentCell.getCellYPos();
//                int cellWidth = currentCell.getCellWidth();
//                int cellHeight = currentCell.getCellHeight();
//                if(x <= cellX + cellWidth &&
//                        x >= cellX &&
//                        y <= cellY + cellHeight &&
//                        y >= cellY){
//                    return currentCell.getCellResId();
//                }
//            }
//        }
//        return -1;
//    }

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
