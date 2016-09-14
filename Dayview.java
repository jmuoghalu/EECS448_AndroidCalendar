package com.example.cara.calendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class Dayview extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.weekButton).setOnClickListener(this);
        findViewById(R.id.monthButton).setOnClickListener(this);
        findViewById(R.id.yearButton).setOnClickListener(this);
        Intent get = getIntent();
    }
    //public void DoStuff()
    //{
    //does stuff
    //}

    @Override
    public void onClick(View view) {
        //all
        switch(view.getId())
        {
            case(R.id.weekButton):
            {
                Intent w = new Intent(this, Weekview.class);
                //send day to display correct week
                startActivity(w);
                break;
            }
            case(R.id.monthButton):
            {
                //do stuff for monthButton
                break;
            }
            case(R.id.yearButton):
            {
                //do stuff for yearButton
                break;
            }
        }
    }
}