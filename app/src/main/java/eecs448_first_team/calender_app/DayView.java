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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class DayView extends AppCompatActivity implements View.OnClickListener {
    private CalendarEventDb database;

    private int day;
    private int month;
    private int year;
    private Calendar cal;
    private ListView eventsListView;

    @Override
    /**
     * Load the day view and setup text correctly.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_view);

        // listen for clicks to each of these views
        findViewById(R.id.weekButton).setOnClickListener(this);
        findViewById(R.id.monthButton).setOnClickListener(this);
        findViewById(R.id.yearButton).setOnClickListener(this);
        findViewById(R.id.addDetailsButton1).setOnClickListener(this);

        // Get information passed to this view from othe rviews
        Intent getToDay = getIntent();
        day = getToDay.getIntExtra("day", 1);
        month = getToDay.getIntExtra("month", 7);
        year = getToDay.getIntExtra("year", 2016);

        // create a new calendar object to determine info on date
        cal = new GregorianCalendar(year, month, day);

        fillDate();
    }

    @Override
    /**
     * Called when app resumes after view has been created above.
     * Used to load information from database.
     */
    public void onResume() {
        super.onResume();

        // init new database
        database = new CalendarEventDb(this);

        // default start time based on current day
        Long start = cal.getTimeInMillis();

        // determine tommorrow based as today + 1
        Calendar tomorrowCal = (Calendar) cal.clone();
        tomorrowCal.add(Calendar.DAY_OF_YEAR, 1);
        Long end = tomorrowCal.getTimeInMillis();

        // get all events between these two times
        List<CalendarEvent> events = database.getCalendarEvents(start, end);

        // init list of events
        EventAdapter adapter = new EventAdapter(this, events);

        eventsListView = (ListView) findViewById(R.id.events_list);
        eventsListView.setAdapter(adapter);

        // from http://stackoverflow.com/questions/2468100/android-listview-click-howto
        // handle when list has been clicked
        eventsListView.setClickable(true);
        eventsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                CalendarEvent event = (CalendarEvent) eventsListView.getItemAtPosition(position);
                Intent goToAdd = new Intent(DayView.this, AddDetails.class);
                goToAdd.putExtra("id", event.getID()); // pass current event to AddDetails view
                startActivity(goToAdd);
            }
        });
    }

    @Override
    /**
     * Handle when view is closed. Used to close database
     */
    public void onStop() {
        super.onStop();
        database.close();
    }

    @Override
    /**
     * Handle when view is clicked.
     * @param view The child view that was clicked
     */
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.weekButton: // week button was clicked
                Intent goToWeek = new Intent(this, WeekView.class);
                goToWeek.putExtra("year", year);
                goToWeek.putExtra("week", cal.get(Calendar.WEEK_OF_YEAR));
                startActivity(goToWeek);
                break;
            case R.id.monthButton: // month button was cilcked
                Intent goToMonth = new Intent(this, MonthView.class);
                goToMonth.putExtra("year", cal.get(Calendar.YEAR));
                goToMonth.putExtra("month", cal.get(Calendar.MONTH));
                startActivity(goToMonth);
                break;
            case R.id.yearButton: // year button was clicked
                Intent goToYear = new Intent(this, YearDisplay.class);
                goToYear.putExtra("year", cal.get(Calendar.YEAR));
                goToYear.putExtra("month", cal.get(Calendar.MONTH));
                goToYear.putExtra("day", cal.get(Calendar.DAY_OF_MONTH));
                startActivity(goToYear);
                break;
            case R.id.addDetailsButton1: // add details button was clicked
                Intent goToAdd = new Intent(this, AddDetails.class);
                goToAdd.putExtra("year", cal.get(Calendar.YEAR));
                goToAdd.putExtra("month", cal.get(Calendar.MONTH));
                goToAdd.putExtra("day", cal.get(Calendar.DAY_OF_MONTH));
                startActivity(goToAdd);
                break;
        }
    }
    /**
     * Fill the date text view with correct text.
     * precondition: array exists and is filled with correct values.
     * postcondition: text of ID:date in activity_day_view.xml is set to date user wanted.
     * Sets the proper text to display based on selected day.
     */
    public void fillDate() {
        DateFormat dateFormat = new SimpleDateFormat("MMMM d, YYYY");

        TextView t = (TextView) findViewById(R.id.date);
        t.setText(dateFormat.format(cal.getTime()));
    }

    /**
     * Used to save state so it can be recovered succesfully.
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
