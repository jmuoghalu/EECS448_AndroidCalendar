package com.example.cara.calendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class Weekview extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekview);
        findViewById(R.id.sunday).setOnClickListener(this);
        findViewById(R.id.monday).setOnClickListener(this);
        findViewById(R.id.tuesday).setOnClickListener(this);
        findViewById(R.id.wednesday).setOnClickListener(this);
        findViewById(R.id.thursday).setOnClickListener(this);
        findViewById(R.id.friday).setOnClickListener(this);
        findViewById(R.id.saturday).setOnClickListener(this);
        findViewById(R.id.monthButton).setOnClickListener(this);
        findViewById(R.id.yearButton).setOnClickListener(this);
        Intent w = getIntent();
    }

    @Override
    public void onClick(View view) {
        //all
        switch(view.getId())
        {
            case(R.id.monthButton):
            {
                break;
            }
            case(R.id.yearButton):
            {
                break;
            }
            default:
            {
                Intent someday = new Intent(this, MainActivity.class);
                //send day to MainActivity to show correct day view
                startActivity(someday);
                break;
            }
        }
    }
}
