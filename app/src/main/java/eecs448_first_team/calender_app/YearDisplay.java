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
import android.view.View;
import android.widget.CalendarView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class YearDisplay extends AppCompatActivity implements View.OnClickListener {

    @Override
    /**
     * Android method called as part of the Activity Lifecycle on activity creation
     * gets layout, prepares undefined class methods, alters display programmatically.
     * Configures listener for date changing to navigate user to corresponding month.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year_display);

        // get the calendar widget from the year display view
        CalendarView calendar;
        calendar = (CalendarView) findViewById(R.id.calendar);

        SimpleDateFormat sdf = new SimpleDateFormat("d-M-yyyy hh:mm");
        try {
            Date date = sdf.parse("1-8-2016 00:00");
            calendar.setMinDate(date.getTime()); //prevents date from going before Aug 1st
        } catch(ParseException e) {
            //googled off of the internet
            calendar.setMinDate(1470027600000l);
        }

        try {
            Date date = sdf.parse("31-5-2017 00:00");
            calendar.setMaxDate(date.getTime()); //prevents date from going before Aug 1st
        } catch(ParseException e) {
            //googled off of the internet
            calendar.setMaxDate(1496206800000l);
        }

        // listen for button clicks from add details
        findViewById(R.id.addDetailsButton3).setOnClickListener(this);

        // Listener to catch selected date and save it to be sent to anothe activity
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            @Override


            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth){
            Intent goToMonth = new Intent(YearDisplay.this, MonthView.class);
            goToMonth.putExtra("year", year);
            goToMonth.putExtra("month", month);
            goToMonth.putExtra("day", dayOfMonth);
            startActivity(goToMonth);
            }

        });

        // get last selected day from other views (defaults to August 1, 2016 if none given)
        Intent getToDay = getIntent();
        int day = getToDay.getIntExtra("day", 1);
        int month = getToDay.getIntExtra("month", 7);
        int year = getToDay.getIntExtra("year", 2016);

        // set the calendar date based on above year, month, day values
        Calendar cal = new GregorianCalendar(year, month, day);
        calendar.setDate(cal.getTimeInMillis());
    }

    @Override
    /**
     * Android method to handle clicks to this view.
     */
    public void onClick(View view) {
        if (view.getId() == R.id.addDetailsButton3) {
            // go to add details view
            Intent goToAdd = new Intent(this, AddDetails.class);
            startActivity(goToAdd);
        }
    }
}
