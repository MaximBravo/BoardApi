package com.example.maximbravo.boardapi;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //Textview representing output textview
    private TextView output;

    //Relative layout for whole screen
    private RelativeLayout mainContent;

    //Board creation
    private Board board;

    private final int BOARD_SIZE = 8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize output textview
        output = (TextView) findViewById(R.id.output_textview);

        //initialize output RelativeLayout
        mainContent = (RelativeLayout) findViewById(R.id.content_main);

        //fab stuff
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //Width of screen
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int width = displaymetrics.widthPixels;
        int height = displaymetrics.heightPixels;
        //Board Initialization
        board = new Board(MainActivity.this, mainContent, R.id.content_main, BOARD_SIZE, BOARD_SIZE, width, height, Math.min(height, width)/(BOARD_SIZE+2) - 4);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //if finger is on screen or not
        if(event.getAction() != MotionEvent.ACTION_UP){
            //x value of press
            int x = (int) event.getX();
            //y value of press
            int y = (int) event.getY();
            //y value of main content for offset later
            int mainContentY = (int) mainContent.getY();

            //the value of x and y starting from mainContent
            int realX = x;
            int realY = y - mainContentY;
            //set output text to x and y coordinates of touch
            output.setText("You are touching the screen at: " + realX + ", " + realY);
        } else {
            //set output text if not touching the screen
            output.setText("You are not touching the screen! :(");
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
