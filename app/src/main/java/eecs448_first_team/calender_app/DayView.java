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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DayView extends AppCompatActivity implements View.OnClickListener {
    private CalendarEventDb theDatabase;

    private int day;
    private int month;
    private int year;
    private Calendar cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_view);

        findViewById(R.id.weekButton).setOnClickListener(this);
        findViewById(R.id.monthButton).setOnClickListener(this);
        findViewById(R.id.yearButton).setOnClickListener(this);
        findViewById(R.id.addDetailsButton).setOnClickListener(this);

        Intent getToDay = getIntent();
        day = getToDay.getIntExtra("day", 0);
        month = getToDay.getIntExtra("month", 7);
        year = getToDay.getIntExtra("year", 2016);

        cal = new GregorianCalendar(year, month, day);

        fillDate();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        theDatabase = new CalendarEventDb(this);

        ((TextView)findViewById(R.id.detailsText)).setText(theDatabase.getCalendarDetails(cal.getTimeInMillis()));
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
                goToWeek.putExtra("year", year);
                goToWeek.putExtra("week", cal.get(Calendar.WEEK_OF_YEAR));
                startActivity(goToWeek);
                break;
            }
            case(R.id.monthButton):
            {
                Intent goToMonth = new Intent(this, MonthView.class);
                goToMonth.putExtra("year", cal.get(Calendar.YEAR));
                goToMonth.putExtra("month", cal.get(Calendar.MONTH));
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
                goToDetails.putExtra("year", cal.get(Calendar.YEAR));
                goToDetails.putExtra("month", cal.get(Calendar.MONTH));
                goToDetails.putExtra("day", cal.get(Calendar.DAY_OF_MONTH));
                startActivity(goToDetails);
                break;
            }
        }
    }
    /**
     * precondition: array exists and is filled with correct values.
     * postcondition: text of ID:date in activity_day_view.xml is set to date user wanted.
     * Sets the proper text to display based on selected day.
     * array: [day,month,year,day of the first of the month,date of Sunday of the week sent to WeekView,number of days in the month,number of days in the previous month,week number]
     */
    public void fillDate()
    {
        DateFormat dateFormat = new SimpleDateFormat("MMMM d, YYYY");

        TextView t = (TextView) findViewById(R.id.date);
        t.setText(dateFormat.format(cal.getTime()));
    }

    /**
     * @param savedInstanceState Bundles the values of of data and array and saves them once the activity is left
     * @see   : https://developer.android.com/training/basics/activity-lifecycle/recreating.html
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("day", day);
        savedInstanceState.putInt("month", month);
        savedInstanceState.putInt("year", year);
        super.onSaveInstanceState(savedInstanceState);
    }
}
