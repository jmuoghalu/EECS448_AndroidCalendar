package com.example.cara.calendar2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class MonthView extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_view);

        findViewById(R.id.yearButton).setOnClickListener(this);
        findViewById(R.id.firstWeek).setOnClickListener(this);
        findViewById(R.id.secondWeek).setOnClickListener(this);
        findViewById(R.id.thirdWeek).setOnClickListener(this);
        findViewById(R.id.fourthWeek).setOnClickListener(this);
        findViewById(R.id.fifthWeek).setOnClickListener(this);

        Intent getToMonth = getIntent();
    }
    @Override
    public void onClick(View view)
    {
        switch(view.getId())
        {
            case (R.id.yearButton):
            {
                Intent goToYear = new Intent(this, YearView.class);
                startActivity(goToYear);
                break;
            }
            default:
            {
                Intent goToWeek = new Intent(this, WeekView.class);
                startActivity(goToWeek);
                break;
            }
        }
    }
}
