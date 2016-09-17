package com.example.cara.calendar2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Textview;

public class WeekView extends AppCompatActivity implements View.OnClickListener {
    public final static String DATA = "";
    public int[] array;
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
        array = getToWeek.getIntArrayExtra(MonthView.DATA);

        TextView textView = (TextView) findViewById(R.id.year);
        if(array[2] == 2016) {
            textView.setText("2016");
        }
        else
        {
            textView.setText("2017");
        }
        fillWeek();
    }
    @Override
    public void onClick(View view)
    {
        switch(view.getId())
        {
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
            default:
            {
                Intent goToDay = new Intent(this, DayView.class);
                startActivity(goToDay);
                break;
            }
        }
    }
    public void fillWeek()
    {
        TextView t = (TextView) findViewById(R.id.sunday);
        if(array[1] == 8 && array[7] == 1)
        {
            t = (TextView) findViewById(R.id.monday);
            t.setText("Monday, " + getMonth(array[1]) + "1");
            t = (TextView) findViewById(R.id.tuesday);
            t.setText("Tuesday, " + getMonth(array[1]) + "2");
            t = (TextView) findViewById(R.id.wednesday);
            t.setText("Wednesday, " + getMonth(array[1]) + "3");
            t = (TextView) findViewById(R.id.thursday);
            t.setText("Thursday, " + getMonth(array[1]) + "4");
            t = (TextView) findViewById(R.id.friday);
            t.setText("Friday, " + getMonth(array[1]) + "5");
            t = (TextView) findViewById(R.id.saturday);
            t.setText("Saturday, " + getMonth(array[1]) + "6");
        }
        else if(array[1] == 5 && array[7] == 5)
        {
            t.setText("Sunday, " + getMonth(array[1]) + array[4]);
            t = (TextView) findViewById(R.id.monday);
            t.setText("Monday, " + getMonth(array[1]) + (array[4]+1));
            t = (TextView) findViewById(R.id.tuesday);
            t.setText("Tuesday, " + getMonth(array[1]) + (array[4]+2));
            t = (TextView) findViewById(R.id.wednesday);
            t.setText("Wednesday, " + getMonth(array[1]) + (array[4]+3));
        }
        else if(array[7] == 1 && array[4] > 1) //First week and starts with another month
        {
            t.setText("Sunday, " + getMonth(array[1]-1) + array[4]);
            t = (TextView) findViewById(R.id.monday);
            if(array[4]+1 > array[6])
            {
                t.setText("Monday, " + getMonth(array[1]) + "1");
                t = (TextView) findViewById(R.id.tuesday);
                t.setText("Tuesday, " + getMonth(array[1]) + "2");
                t = (TextView) findViewById(R.id.wednesday);
                t.setText("Wednesday, " + getMonth(array[1]) + "3");
                t = (TextView) findViewById(R.id.thursday);
                t.setText("Thursday, " + getMonth(array[1]) + "4");
                t = (TextView) findViewById(R.id.friday);
                t.setText("Friday, " + getMonth(array[1]) + "5");
                t = (TextView) findViewById(R.id.saturday);
                t.setText("Saturday, " + getMonth(array[1]) + "6");
                return;
            }
            else
            {
                t.setText("Monday, " + getMonth(array[1]-1) + (array[4]+1));
            }
            t = (TextView) findViewById(R.id.tuesday);
            if(array[4]+2 > array[6])
            {
                t.setText("Tuesday, " + getMonth(array[1]) + "1");
                t = (TextView) findViewById(R.id.wednesday);
                t.setText("Wednesday, " + getMonth(array[1]) + "2");
                t = (TextView) findViewById(R.id.thursday);
                t.setText("Thursday, " + getMonth(array[1]) + "3");
                t = (TextView) findViewById(R.id.friday);
                t.setText("Friday, " + getMonth(array[1]) + "4");
                t = (TextView) findViewById(R.id.saturday);
                t.setText("Saturday, " + getMonth(array[1]) + "5");
                return;
            }
            else
            {
                t.setText("Tuesday, " + getMonth(array[1]-1) + (array[4]+2));
            }
            t = (TextView) findViewById(R.id.wednesday);
            if(array[4]+3 > array[6])
            {
                t.setText("Wednesday, " + getMonth(array[1]) + "1");
                t = (TextView) findViewById(R.id.thursday);
                t.setText("Thursday, " + getMonth(array[1]) + "2");
                t = (TextView) findViewById(R.id.friday);
                t.setText("Friday, " + getMonth(array[1]) + "3");
                t = (TextView) findViewById(R.id.saturday);
                t.setText("Saturday, " + getMonth(array[1]) + "4");
                return;
            }
            else
            {
                t.setText("Wednesday, " + getMonth(array[1]-1) + (array[4]+3));
            }
            t = (TextView) findViewById(R.id.thursday);
            if(array[4]+4 > array[6])
            {
                t.setText("Thursday, " + getMonth(array[1]) + "1");
                t = (TextView) findViewById(R.id.friday);
                t.setText("Friday, " + getMonth(array[1]) + "2");
                t = (TextView) findViewById(R.id.saturday);
                t.setText("Saturday, " + getMonth(array[1]) + "3");
                return;
            }
            else
            {
                t.setText("Thursday, " + getMonth(array[1]-1) + (array[4]+4));
            }
            t = (TextView) findViewById(R.id.friday);
            if(array[4]+5 > array[6])
            {
                t.setText("Friday, " + getMonth(array[1]) + "1");
                t = (TextView) findViewById(R.id.saturday);
                t.setText("Saturday, " + getMonth(array[1]) + "2");
                return;
            }
            else
            {
                t.setText("Friday, " + getMonth(array[1]-1) + (array[4]+5));
            }
            t = (TextView) findViewById(R.id.saturday);
            t.setText("Saturday, " + getMonth(array[1]) + "1");
        }
        else if(array[7] == 5 && array[4]+6 > array[5]) //Week 5 goes into next month
        {
            t.setText("Sunday, " + getMonth(array[1]) + array[4]);
            t = (TextView) findViewById(R.id.monday);
            if(array[4]+1 > array[5])
            {
                t.setText("Monday, " + getMonth(array[1]+1) + "1");
                t = (TextView) findViewById(R.id.tuesday);
                t.setText("Tuesday, " + getMonth(array[1]+1) + "2");
                t = (TextView) findViewById(R.id.wednesday);
                t.setText("Wednesday, " + getMonth(array[1]+1) + "3");
                t = (TextView) findViewById(R.id.thursday);
                t.setText("Thursday, " + getMonth(array[1]+1) + "4");
                t = (TextView) findViewById(R.id.friday);
                t.setText("Friday, " + getMonth(array[1]+1) + "5");
                t = (TextView) findViewById(R.id.saturday);
                t.setText("Saturday, " + getMonth(array[1]+1) + "6");
                return;
            }
            else
            {
                t.setText("Monday, " + getMonth(array[1]) + (array[4]+1));
            }
            t = (TextView) findViewById(R.id.tuesday);
            if(array[4]+2 > array[5])
            {
                t.setText("Tuesday, " + getMonth(array[1]+1) + "1");
                t = (TextView) findViewById(R.id.wednesday);
                t.setText("Wednesday, " + getMonth(array[1]+1) + "2");
                t = (TextView) findViewById(R.id.thursday);
                t.setText("Thursday, " + getMonth(array[1]+1) + "3");
                t = (TextView) findViewById(R.id.friday);
                t.setText("Friday, " + getMonth(array[1]+1) + "4");
                t = (TextView) findViewById(R.id.saturday);
                t.setText("Saturday, " + getMonth(array[1]+1) + "5");
                return;
            }
            else
            {
                t.setText("Tuesday, " + getMonth(array[1]) + (array[4]+2));
            }
            t = (TextView) findViewById(R.id.wednesday);
            if(array[4]+3 > array[5])
            {
                t.setText("Wednesday, " + getMonth(array[1]+1) + "1");
                t = (TextView) findViewById(R.id.thursday);
                t.setText("Thursday, " + getMonth(array[1]+1) + "2");
                t = (TextView) findViewById(R.id.friday);
                t.setText("Friday, " + getMonth(array[1]+1) + "3");
                t = (TextView) findViewById(R.id.saturday);
                t.setText("Saturday, " + getMonth(array[1]+1) + "4");
                return;
            }
            else
            {
                t.setText("Wednesday, " + getMonth(array[1]) + (array[4]+3));
            }
            t = (TextView) findViewById(R.id.thursday);
            if(array[4]+4 > array[5])
            {
                t.setText("Thursday, " + getMonth(array[1]+1) + "1");
                t = (TextView) findViewById(R.id.friday);
                t.setText("Friday, " + getMonth(array[1]+1) + "2");
                t = (TextView) findViewById(R.id.saturday);
                t.setText("Saturday, " + getMonth(array[1]+1) + "3");
                return;
            }
            else
            {
                t.setText("Thursday, " + getMonth(array[1]) + (array[4]+4));
            }
            t = (TextView) findViewById(R.id.friday);
            if(array[4]+5 > array[5])
            {
                t.setText("Friday, " + getMonth(array[1]+1) + "1");
                t = (TextView) findViewById(R.id.saturday);
                t.setText("Saturday, " + getMonth(array[1]+1) + "2");
                return;
            }
            else
            {
                t.setText("Friday, " + getMonth(array[1]) + (array[4]+5));
            }
            t = (TextView) findViewById(R.id.saturday);
            t.setText("Saturday, " + getMonth(array[1]+1) + "1");
        }
        else if(array[7] == 6) //Week 6
        {
            t.setText("Sunday, " + getMonth(array[1]) + array[4]);
            t = (TextView) findViewById(R.id.monday);
            if(array[4]+1 <= array[5])
            {
                t.setText("Monday, " + getMonth(array[1]) + (array[4]+1));
                t = (TextView) findViewById(R.id.tuesday);
                t.setText("Tuesday, " + getMonth(array[1]+1) + "1");
                t = (TextView) findViewById(R.id.wednesday);
                t.setText("Wednesday, " + getMonth(array[1]+1) + "2");
                t = (TextView) findViewById(R.id.thursday);
                t.setText("Thursday, " + getMonth(array[1]+1) + "3");
                t = (TextView) findViewById(R.id.friday);
                t.setText("Friday, " + getMonth(array[1]+1) + "4");
                t = (TextView) findViewById(R.id.saturday);
                t.setText("Saturday, " + getMonth(array[1]+1) + "5");
            }
            else
            {
                t.setText("Monday, " + getMonth(array[1]+1) + "1");
                t = (TextView) findViewById(R.id.tuesday);
                t.setText("Tuesday, " + getMonth(array[1]+1) + "2");
                t = (TextView) findViewById(R.id.wednesday);
                t.setText("Wednesday, " + getMonth(array[1]+1) + "3");
                t = (TextView) findViewById(R.id.thursday);
                t.setText("Thursday, " + getMonth(array[1]+1) + "4");
                t = (TextView) findViewById(R.id.friday);
                t.setText("Friday, " + getMonth(array[1]+1) + "5");
                t = (TextView) findViewById(R.id.saturday);
                t.setText("Saturday, " + getMonth(array[1]+1) + "6");
            }
        }
        else
        {
            t.setText("Sunday, " + getMonth(array[1]) + array[4]);
            t = (TextView) findViewById(R.id.monday);
            t.setText("Monday, " + getMonth(array[1]) + (array[4]+1));
            t = (TextView) findViewById(R.id.tuesday);
            t.setText("Tuesday, " + getMonth(array[1]) + (array[4]+2));
            t = (TextView) findViewById(R.id.wednesday);
            t.setText("Wednesday, " + getMonth(array[1]) + (array[4]+3));
            t = (TextView) findViewById(R.id.thursday);
            t.setText("Thursday, " + getMonth(array[1]) + (array[4]+4));
            t = (TextView) findViewById(R.id.friday);
            t.setText("Friday, " + getMonth(array[1]) + (array[4]+5));
            t = (TextView) findViewById(R.id.saturday);
            t.setText("Saturday, " + getMonth(array[1]) + (array[4]+6));
        }
    }
    public String getMonth(int m)
    {
        if(m == 8)
        {
            return("Aug. ");
        }
        else if(m == 9)
        {
            return("Sept. ");
        }
        else if(m == 10)
        {
            return("Oct. ");
        }
        else if(m == 11)
        {
            return("Nov. ");
        }
        else if(m == 12)
        {
            return("Dec. ");
        }
        else if(m == 1)
        {
            return("Jan. ");
        }
        else if(m == 2)
        {
            return("Feb. ");
        }
        else if(m == 3)
        {
            return("Mar. ");
        }
        else if(m == 4)
        {
            return("Apr. ");
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
