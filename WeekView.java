package com.example.cara.calendar2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class WeekView extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_view);

        findViewById(R.id.sunday).setOnClickListener(this);
        findViewById(R.id.monday).setOnClickListener(this);
        findViewById(R.id.tuesday).setOnClickListener(this);
        findViewById(R.id.wednesday).setOnClickListener(this);
        findViewById(R.id.thursday).setOnClickListener(this);
        findViewById(R.id.friday).setOnClickListener(this);
        findViewById(R.id.saturday).setOnClickListener(this);
        findViewById(R.id.monthButton).setOnClickListener(this);
        findViewById(R.id.yearButton).setOnClickListener(this);

        Intent getToWeek = getIntent();
    }
    @Override
    public void onClick(View view)
    {
        switch(view.getId())
        {
            case(R.id.monthButton):
            {
                Intent goToMonth = new Intent(this, MonthView.class);
                startActivity(goToMonth);
                break;
            }
            case(R.id.yearButton):
            {
                Intent goToYear = new Intent(this, YearView.class);
                startActivity(goToYear);
                break;
            }
            default:
            {
                Intent goToDay = new Intent(this, DayView.class);
                startActivity(goToDay);
                break;
            }
        }
    }
}
