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
        int monthFromYear = getToMonth.getIntExtra(YearView.MONTH,0);

        if(monthFromYear != 0)
        {
            TextView textview = (TextView) findViewById(R.id.monthName);
            textview.setText(getMonth(monthFromYear));
        }
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
    
    public String getMonth(int m)
    {
        if(m == 8)
        {
            return("August");
        }
        else if(m == 9)
        {
            return("September");
        }
        else if(m == 10)
        {
            return("October");
        }
        else if(m == 11)
        {
            return("November");
        }
        else if(m == 12)
        {
            return("December");
        }
        else if(m == 1)
        {
            return("January");
        }
        else if(m == 2)
        {
            return("February");
        }
        else if(m == 3)
        {
            return("March");
        }
        else if(m == 4)
        {
            return("April");
        }
        else if(m == 5)
        {
            return("May");
        }
        else
        {
            return("");
        }
    }
}
