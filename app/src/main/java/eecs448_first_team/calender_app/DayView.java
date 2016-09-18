package eecs448_first_team.calender_app;

/** 
4  * author: Cara Fisher 
5  * date: 9-18-16 
6  * purpose: a mobile app page which displays the focus date and any events user has added to that date
7  *  
8  * Many thanks to Android (https://developer.android.com/index.html) for its help 
9  */ 

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.Calendar;

public class DayView extends AppCompatActivity implements View.OnClickListener {
    public final static String DATA = "";
    public int[] array;
    private CalendarEventDb theDatabase;
    private Calendar dateToMillisecondsInterpreter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_view);

        findViewById(R.id.weekButton).setOnClickListener(this);
        findViewById(R.id.monthButton).setOnClickListener(this);
        findViewById(R.id.yearButton).setOnClickListener(this);
        findViewById(R.id.addDetailsButton).setOnClickListener(this);

        Intent getToDay = getIntent();
        array = getToDay.getIntArrayExtra(AddDetails.DATA);
        fillDate();
    }
    @Override
    public void onResume()
    {
        super.onResume();
        theDatabase = new CalendarEventDb(this);
        dateToMillisecondsInterpreter = Calendar.getInstance(); //configures the calendar to get current time in MS of your corresponding day
        dateToMillisecondsInterpreter.set(Calendar.HOUR,0); //locks hours,minutes,seconds,MS in Calendar to 0 to make consistent, and so we don't have to zero them again later
        dateToMillisecondsInterpreter.set(Calendar.MINUTE,0); //thus, I can say with certainty that using the Calendar to convert 9-21-2016 to milliseconds will produce a constant value
        dateToMillisecondsInterpreter.set(Calendar.SECOND,0);
        dateToMillisecondsInterpreter.set(Calendar.MILLISECOND,0);
        dateToMillisecondsInterpreter.set(Calendar.DAY_OF_MONTH,array[0]);
        if(array[7] == 1 && array[0] > 7) // if Week 1 and day is from the previous month
        {
            dateToMillisecondsInterpreter.set(Calendar.MONTH,array[1] - 1);
        }
        else if(array[7] > 4 && array[0] < 23) // if Week 5 or 6 and day is from the next month
        {
            dateToMillisecondsInterpreter.set(Calendar.MONTH,array[1] + 1);
        }
        else
        {
            dateToMillisecondsInterpreter.set(Calendar.MONTH,array[1]);
        }
        dateToMillisecondsInterpreter.set(Calendar.YEAR,array[2]);

        ((TextView)findViewById(R.id.detailsText)).setText(theDatabase.getCalendarDetails(dateToMillisecondsInterpreter.getTimeInMillis()));
    }
    @Override
    public void onStop()
    {
        super.onStop();
        theDatabase.close();
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
                Intent goToYear = new Intent(this, YearDisplay.class);
                startActivity(goToYear);
                break;
            }
            case(R.id.addDetailsButton):
            {
                Intent goToDetails = new Intent(this, AddDetails.class);
                goToDetails.putExtra(DATA, array); //TODO: do we pass array into details?
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
     * @param m the integer equivalent of the month to return (8 is "August", 1 is "January")
     * @return the name of the month of number m, or empty string if provided month is unreachable (June, July)
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
