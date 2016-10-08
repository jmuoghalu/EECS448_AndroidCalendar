package eecs448_first_team.calender_app;

/**
 * authors: Deema ALShoaibi and Hans Brown
 * date: 9-18-16
 * purpose: a mobile app page that displays the entire year to the user, allowing them to select
 * a month through its constituent days.
 *
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class YearDisplay extends AppCompatActivity {

    @Override
    /**
     * Android method called as part of the Activity Lifecycle on activity creation
     * gets layout, prepares undefined class methods, alters display programmatically.
     * Configures listener for date changing to navigate user to corresponding month.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_year_display);
        CalendarView calender;
        calender = (CalendarView) findViewById(R.id.calender);

        SimpleDateFormat sdf = new SimpleDateFormat("d-M-yyyy hh:mm");
        try
        {
            Date date = sdf.parse("1-8-2016 00:00");
            calender.setMinDate(date.getTime()); //prevents date from going before Aug 1st
        }
        catch(ParseException e) {
            //googled off of the internet
            calender.setMinDate(1470027600000l);
        }
        try
        {
            Date date = sdf.parse("31-5-2017 00:00");
            calender.setMaxDate(date.getTime()); //prevents date from going before Aug 1st
        }
        catch(ParseException e)
        {
            //googled off of the internet
            calender.setMaxDate(1496206800000l);
        }

        // Listener to catch selected date and save it to be sent to anothe activity
        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            @Override


            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth){
            Intent goToMonth = new Intent(YearDisplay.this, MonthView.class);
            goToMonth.putExtra("year", year);
            goToMonth.putExtra("month", month);
            goToMonth.putExtra("day", dayOfMonth);
            startActivity(goToMonth);
            }

        });
    }
}
