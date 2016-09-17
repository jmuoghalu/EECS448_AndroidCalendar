package com.example.cara.calendar2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;

public class DayView extends AppCompatActivity implements View.OnClickListener {
    public final static String DATA = "";
    public int[] array;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_view);

        findViewById(R.id.weekButton).setOnClickListener(this);
        findViewById(R.id.monthButton).setOnClickListener(this);
        findViewById(R.id.yearButton).setOnClickListener(this);
        findViewById(R.id.addDetailsButton).setOnClickListener(this);

        Intent getToDay = getIntent();
        array = getToDay.getIntArrayExtra(WeekView.DATA);
        fillDate();
    }
    @Override
    public void onClick(View view)
    {
        switch(view.getId())
        {
            case(R.id.weekButton):
            {
                Intent goToWeek = new Intent(this, WeekView.class);
                goToWeek.putExtra(DATA, array);
                startActivity(goToWeek);
                break;
            }
            case(R.id.monthButton):
            {
                Intent goToMonth = new Intent(this, MonthView.class);
                goToMonth.putExtra(DATA, array);
                startActivity(goToMonth);
                break;
            }
            case(R.id.yearButton):
            {
                Intent goToYear = new Intent(this, YearView.class);
                startActivity(goToYear);
                break;
            }
            case(R.id.addDetailsButton):
            {
                Intent goToDetails = new Intent(this, AddDetails.class);
                startActivity(goToDetails);
                break;
            }
        }
    }
    public void fillDate()  //Pay attention to first and last weeks
    {
        TextView t = (TextView) findViewById(R.id.date);
        if(array[7] == 1 && array[0] > 7)
        {
            t.setText(getMonth(array[1]-1) + array[0] + ", " + array[2]);
        }
        else if(array[7] > 4 && array[0] < 23)
        {
            t.setText(getMonth(array[1]+1) + array[0] + ", " + array[2]);
        }
        else
        {
            t.setText(getMonth(array[1]) + array[0] + ", " + array[2]);
        }
    }
    public String getMonth(int m)
    {
        if(m == 8)
        {
            return("August ");
        }
        else if(m == 9)
        {
            return("September ");
        }
        else if(m == 10)
        {
            return("October ");
        }
        else if(m == 11)
        {
            return("November ");
        }
        else if(m == 12)
        {
            return("December ");
        }
        else if(m == 1)
        {
            return("January ");
        }
        else if(m == 2)
        {
            return("February ");
        }
        else if(m == 3)
        {
            return("March ");
        }
        else if(m == 4)
        {
            return("April ");
        }
        else if(m == 5)
        {
            return("May ");
        }
        else
        {
            return("");
        }
    }
}
