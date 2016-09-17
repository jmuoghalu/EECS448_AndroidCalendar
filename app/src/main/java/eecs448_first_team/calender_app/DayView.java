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
        array = getToDay.getIntArrayExtra(AddDetails.DATA);
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
                goToDetails.putExtra(DATA, array);
                startActivity(goToDetails);
                break;
            }
        }
    }
    /**
     * precondition: array exists and is filled with correct values
     * postcondition: text of ID:date in activity_day_view.xml is set to date user wanted
     *
     * array: [day,month,year,day of the first of the month,date of Sunday of the week sent to WeekView,number of days in the month,number of days in the previous month,week number]
     */
    public void fillDate()
    {
        TextView t = (TextView) findViewById(R.id.date);
        if(array[7] == 1 && array[0] > 7) // if Week 1 and day is from the previous month
        {
            t.setText(getMonth(array[1]-1) + array[0] + ", " + array[2]);
        }
        else if(array[7] > 4 && array[0] < 23) // if Week 5 or 6 and day is from the next month
        {
            t.setText(getMonth(array[1]+1) + array[0] + ", " + array[2]);
        }
        else
        {
            t.setText(getMonth(array[1]) + array[0] + ", " + array[2]);
        }
    }

    /**
     * precondition: m is an existing integer
     * postconditon: None
     * @param m
     * @return the name of the month of number m
     */
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
