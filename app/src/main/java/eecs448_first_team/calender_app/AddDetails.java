package eecs448_first_team.calender_app;

/**
 * author: Cara Fisher
 * date: 9-18-16
 * purpose: a mobile app page where details can be added to be displayed in DayView.
 * <p>
 * Many thanks to Android (https://developer.android.com/index.html) for its help
 *
 * Updated by "Team One" for Project 2.
 */

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AddDetails extends AppCompatActivity implements View.OnClickListener {

    /*Added by Team One*/
    private TextView startDateHourText;
    private TextView endDateHourText;
    private TextView startDateMonthText;
    private TextView endDateMonthText;
    private TextView startDateDayText;
    private TextView endDateDayText;
    private EditText details; //the details text to be replaced
    boolean startSunday,startMonday,startTuesday,startWednesday,startThursday,startFriday,startSaturday;
    private CheckBox sunBox;
    private CheckBox monBox;
    private CheckBox tuesBox;
    private CheckBox wedBox;
    private CheckBox thursBox;
    private CheckBox friBox;
    private CheckBox satBox;
    private CheckBox[] dayBoxes = new CheckBox[7];
    /*End of new code*/


    private CalendarEventDb database;

    private Calendar startTime;
    private Calendar recurringTime;
    private Calendar recurringTime2;

    private Calendar recurringTimeTracer;
    private Calendar recurringTimeTracer2;

    private Calendar endTime;
    private boolean toRecursion;
    private int choice;


    private Long id = Long.valueOf(0);
    private CalendarEvent event = null;

    @Override
    /**
     * Called when an Add Details view is created.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details);

        // listen for buttons
        // added by Team One
        findViewById(R.id.doneButton).setOnClickListener(this);
        findViewById(R.id.cancelButton).setOnClickListener(this);
        findViewById(R.id.deleteButton).setOnClickListener(this);
        findViewById(R.id.startDateHourPlus).setOnClickListener(this);
        findViewById(R.id.startDateHourMinus).setOnClickListener(this);
        findViewById(R.id.startDateDayPlus).setOnClickListener(this);
        findViewById(R.id.startDateDayMinus).setOnClickListener(this);
        findViewById(R.id.startDateMonthPlus).setOnClickListener(this);
        findViewById(R.id.startDateMonthMinus).setOnClickListener(this);
        findViewById(R.id.endDateDayMinus).setOnClickListener(this);
        findViewById(R.id.endDateDayPlus).setOnClickListener(this);
        findViewById(R.id.endDateMonthMinus).setOnClickListener(this);
        findViewById(R.id.endDateMonthPlus).setOnClickListener(this);
        findViewById(R.id.endDateHourPlus).setOnClickListener(this);
        findViewById(R.id.endDateHourMinus).setOnClickListener(this);

        findViewById(R.id.none).setOnClickListener(this);
        findViewById(R.id.weekly).setOnClickListener(this);
        findViewById(R.id.biweekly).setOnClickListener(this);
        findViewById(R.id.monthly).setOnClickListener(this);

        findViewById(R.id.check1).setOnClickListener(this);
        findViewById(R.id.check2).setOnClickListener(this);
        findViewById(R.id.check3).setOnClickListener(this);
        findViewById(R.id.check4).setOnClickListener(this);
        findViewById(R.id.check5).setOnClickListener(this);
        findViewById(R.id.check6).setOnClickListener(this);
        findViewById(R.id.check7).setOnClickListener(this);

        // init references to widgets on the view

        details = (EditText) findViewById(R.id.edit);

        startDateHourText = (TextView) findViewById(R.id.startDateHourText);
        endDateHourText = (TextView) findViewById(R.id.endDateHourText);
        startDateMonthText = (TextView) findViewById(R.id.startDateMonthText);
        endDateMonthText = (TextView) findViewById(R.id.endDateMonthText);
        startDateDayText = (TextView) findViewById(R.id.startDateDayText);
        endDateDayText = (TextView) findViewById(R.id.endDateDayText);

        sunBox = (CheckBox) findViewById(R.id.check1);
        monBox = (CheckBox) findViewById(R.id.check2);
        tuesBox = (CheckBox) findViewById(R.id.check3);
        wedBox = (CheckBox) findViewById(R.id.check4);
        thursBox = (CheckBox) findViewById(R.id.check5);
        friBox = (CheckBox) findViewById(R.id.check6);
        satBox = (CheckBox) findViewById(R.id.check7);

        dayBoxes[0] = sunBox;
        dayBoxes[1] = monBox;
        dayBoxes[2] = tuesBox;
        dayBoxes[3] = wedBox;
        dayBoxes[4] = thursBox;
        dayBoxes[5] = friBox;
        dayBoxes[6] = satBox;

        // load details passed to the view

        Intent getToDetails = getIntent();
        id = getToDetails.getLongExtra("id", 0);

        int day = getToDetails.getIntExtra("day", 0);
        int month = getToDetails.getIntExtra("month", 0);
        int year = getToDetails.getIntExtra("year", 0);

        if (year != 0) { // if a valid year was passed
            // init an end and start time
            startTime = new GregorianCalendar(year, month, day);

            // use next day for end time
            endTime = (Calendar) startTime.clone();
            endTime.add(Calendar.DAY_OF_YEAR, 1);
            endTime.add(Calendar.MINUTE, -1);

            // init recurringTime
            recurringTime = (Calendar) startTime.clone();
            recurringTime2 = (Calendar) startTime.clone();
            recurringTimeTracer = (Calendar) startTime.clone();
            recurringTimeTracer2 = (Calendar) startTime.clone();
        } else {
            // init time based on current time
            startTime = GregorianCalendar.getInstance();

            // zero out time for the current day
            startTime.set(Calendar.HOUR_OF_DAY, 0);
            startTime.set(Calendar.MINUTE, 0);
            startTime.set(Calendar.SECOND, 0);

            // use next day for end time
            endTime = (Calendar) startTime.clone();
            endTime.add(Calendar.DAY_OF_YEAR, 1);
            endTime.add(Calendar.MINUTE, -1);

            // init recurringTime
            recurringTime = (Calendar) startTime.clone();
            recurringTime2 = (Calendar) startTime.clone();
            recurringTimeTracer = (Calendar) startTime.clone();
            recurringTimeTracer2 = (Calendar) startTime.clone();
        }

        fillDate();
    }

    @Override
    /**
     * Load database info after the view has been loaded.
     */
    public void onResume() {
        super.onResume();

        if (event == null) { // make sure event has not been initialized
                             // event should not be overwritten
            database = new CalendarEventDb(this);

            if (id != 0) {
                event = database.getCalendarEvent(id); // use id passed to view
            }
        }

        // ref to delete button
        Button deleteButton = (Button) findViewById(R.id.deleteButton);

        if (event != null) {
            // load widget details based on this event
            details.setText(event.getDetails());
            startTime.setTimeInMillis(event.getStartDate());
            endTime.setTimeInMillis(event.getEndDate());

            recurringTime.setTimeInMillis(event.getStartDate());
            recurringTime2 = (Calendar) startTime.clone();
            recurringTimeTracer.setTimeInMillis(event.getStartDate());
            recurringTimeTracer2.setTimeInMillis(event.getStartDate());

            // show delete button when event has already been created
            deleteButton.setVisibility(View.VISIBLE);
        } else {
            // create new event with no details
            event = new CalendarEvent();
            event.setDetails("");
            event.setStartDate(startTime.getTimeInMillis());
            event.setEndDate(endTime.getTimeInMillis());

            // hide delete button when event has already been created
            deleteButton.setVisibility(View.INVISIBLE);
        }

        updateTime();
    }

    /**
     * Update the start and end time.
     */
    public void updateTime() {
        // date formatters used to show date text
        DateFormat monthFormat = new SimpleDateFormat("MMMM");
        DateFormat dayFormat = new SimpleDateFormat("dd");
        DateFormat timeFormat = new SimpleDateFormat("h:mm aaa");

        // set the text fields with formatted string

        Date startDate = startTime.getTime();
        startDateMonthText.setText(monthFormat.format(startDate));
        startDateDayText.setText(dayFormat.format(startDate));
        startDateHourText.setText(timeFormat.format(startDate));

        Date endDate = endTime.getTime();
        endDateMonthText.setText(monthFormat.format(endDate));
        endDateDayText.setText(dayFormat.format(endDate));
        endDateHourText.setText(timeFormat.format(endDate));
    }

    @Override
    /**
     * Handle click events to add details.
     * @param view The clicked view.
     */
    public void onClick(View view)
    {
        // open the day to click
        Intent goToDay = new Intent(this, DayView.class);
        goToDay.putExtra("day", startTime.get(Calendar.DAY_OF_MONTH));
        goToDay.putExtra("month", startTime.get(Calendar.MONTH));
        goToDay.putExtra("year", startTime.get(Calendar.YEAR));

        // make sure event and widgets are kept in sync.
        event.setDetails(details.getText().toString());
        event.setStartDate(startTime.getTimeInMillis());
        event.setEndDate(endTime.getTimeInMillis());

            /*Added by Team One*/
        switch (view.getId()) {

            case (R.id.startDateHourPlus):
                startTime.add(Calendar.MINUTE, 15);
                break;
            case (R.id.startDateHourMinus):
                startTime.add(Calendar.MINUTE, -15);
                break;
            case (R.id.startDateDayPlus):
                startTime.add(Calendar.DAY_OF_MONTH, 1);
                break;
            case (R.id.startDateDayMinus):
                startTime.add(Calendar.DAY_OF_MONTH, -1);
                break;
            case (R.id.startDateMonthPlus):
                startTime.add(Calendar.MONTH, 1);
                break;
            case (R.id.startDateMonthMinus):
                startTime.add(Calendar.MONTH, -1);
                break;
            case (R.id.endDateDayPlus):
                endTime.add(Calendar.DAY_OF_MONTH, 1);
                break;
            case (R.id.endDateDayMinus):
                endTime.add(Calendar.DAY_OF_MONTH, -1);
                break;
            case (R.id.endDateMonthPlus):
                endTime.add(Calendar.MONTH, 1);
                break;
            case (R.id.endDateMonthMinus):
                endTime.add(Calendar.MONTH, -1);
                break;
            case (R.id.endDateHourPlus):
                recurringTimeTracer.add(Calendar.MINUTE,15);
                recurringTimeTracer2.add(Calendar.MINUTE,15);

                endTime.add(Calendar.MINUTE, 15);
                break;
            case (R.id.endDateHourMinus):
                recurringTimeTracer.add(Calendar.MINUTE,-15);
                recurringTimeTracer2.add(Calendar.MINUTE,-15);

                endTime.add(Calendar.MINUTE, -15);
                break;


            case (R.id.none):
                dayBoxes[startTime.get(Calendar.DAY_OF_WEEK) - 1].setChecked(false);
                dayBoxes[startTime.get(Calendar.DAY_OF_WEEK) - 1].setEnabled(false);

                sunBox.setVisibility(view.GONE);
                monBox.setVisibility(view.GONE);
                tuesBox.setVisibility(view.GONE);
                wedBox.setVisibility(view.GONE);
                thursBox.setVisibility(view.GONE);
                friBox.setVisibility(view.GONE);
                satBox.setVisibility(view.GONE);

                choice = 0;
                toRecursion = false;
                break;
            case (R.id.weekly):
                dayBoxes[startTime.get(Calendar.DAY_OF_WEEK) - 1].setChecked(true);
                dayBoxes[startTime.get(Calendar.DAY_OF_WEEK) - 1].setEnabled(false);

                sunBox.setVisibility(view.VISIBLE);
                monBox.setVisibility(view.VISIBLE);
                tuesBox.setVisibility(view.VISIBLE);
                wedBox.setVisibility(view.VISIBLE);
                thursBox.setVisibility(view.VISIBLE);
                friBox.setVisibility(view.VISIBLE);
                satBox.setVisibility(view.VISIBLE);

                choice = 1;
                toRecursion = true;
                break;
            case (R.id.biweekly):
                dayBoxes[startTime.get(Calendar.DAY_OF_WEEK) - 1].setChecked(true);
                dayBoxes[startTime.get(Calendar.DAY_OF_WEEK) - 1].setEnabled(false);

                sunBox.setVisibility(view.VISIBLE);
                monBox.setVisibility(view.VISIBLE);
                tuesBox.setVisibility(view.VISIBLE);
                wedBox.setVisibility(view.VISIBLE);
                thursBox.setVisibility(view.VISIBLE);
                friBox.setVisibility(view.VISIBLE);
                satBox.setVisibility(view.VISIBLE);

                choice = 2;
                toRecursion = true;
                break;
            case (R.id.monthly):
                dayBoxes[startTime.get(Calendar.DAY_OF_WEEK) - 1].setChecked(false);
                dayBoxes[startTime.get(Calendar.DAY_OF_WEEK) - 1].setEnabled(false);

                sunBox.setVisibility(view.GONE);
                monBox.setVisibility(view.GONE);
                tuesBox.setVisibility(view.GONE);
                wedBox.setVisibility(view.GONE);
                thursBox.setVisibility(view.GONE);
                friBox.setVisibility(view.GONE);
                satBox.setVisibility(view.GONE);

                choice = 3;
                toRecursion = true;
                break;


            case (R.id.doneButton):

                if (endTime.before(startTime)) {
                    new AlertDialog.Builder(this)
                            .setTitle("Bad times")
                            .setMessage("This event ends before it starts.")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                    return;
                }

                // will handle the rucursing aspect of the event
                if (toRecursion)
                {
                    setRecurringDates(view, choice);
                }

                    // will handle the event for the current day
                if (event != null && event.getID() != null)
                {
                    database.updateEvent(event);
                } else {
                    database.addEvent(event);
                }

                this.finish();
                break;

            case (R.id.cancelButton):
                startActivity(goToDay);
                break;

            case (R.id.deleteButton):
                if (event != null && event.getID() != null) {
                    database.deleteEvent(event);
                }
                startActivity(goToDay);
                break;

        }

        updateTime();
        fillDate();

        /*End of code section*/

    }

    @Override
    /**
     * Close database when view is stopped.
     */
    public void onStop() {
        super.onStop();
        database.close();
    }


    // The following methods were added by Team One
    /**
    * Adds the recurring events to the appropriate dates that occur after the current day
    * @param view The clicked view
    * @param recurringChoice The timeframe for the recurring events (integer representing weekly, biweekly, or monthly)
    */
        public void setRecurringDates(View view, int recurringChoice)
        {

        switch ( recurringChoice )
        {

                        // event that recurs weekly
                    case (1):
                    {
                                // new event will be added using a copy of the original calendars
                        Calendar newStartTime = (Calendar) startTime.clone();
                        Calendar newEndTime = (Calendar) endTime.clone();
                        Calendar endOfYear = new GregorianCalendar(2017, 5, 1);

                                // the method will keep track of the original day and week that the user was editing
                        int beginningWeek = newStartTime.get(Calendar.WEEK_OF_YEAR);
                        int curDay = newStartTime.get(Calendar.DAY_OF_WEEK);


                                // the events will be added on the appropriate dates from the start date to the end of the academic calendar
                        while( newStartTime.before(endOfYear) )
                        {

                                // handles the week with the original date (from the current date to the Saturday of that week)
                            if(newStartTime.get(Calendar.WEEK_OF_YEAR) == beginningWeek)
                            {
                                    // Calendar.DAY_OF_WEEK has Sunday at 1, versus the dayBoxes array, which has Sunday at 0
                                for(int A = curDay ; A < 7 ; A++)
                                {
                                        // increments the day that the loop is currently looking at
                                    newStartTime.set( Calendar.DAY_OF_WEEK, (A+1) );
                                    newEndTime.set( Calendar.DAY_OF_WEEK, (A+1) );

                                        // adds the event to the new day if the day is one that the recurring event is to fall on
                                    if(dayBoxes[A].isChecked())
                                    {
                                            // creates a new event and adds it to the database
                                        CalendarEvent newEvent = new CalendarEvent();

                                        newEvent.setDetails(details.getText().toString());
                                        newEvent.setStartDate(newStartTime.getTimeInMillis());
                                        newEvent.setEndDate(newEndTime.getTimeInMillis());

                                        database.addEvent(newEvent);

                                    }

                                }

                            }

                                // handles Sunday to Saturday for the subsequent weeks
                            else
                            {

                                for(int A = 0 ; A < 7 ; A++)
                                {

                                    newStartTime.set( Calendar.DAY_OF_WEEK, (A+1) );
                                    newEndTime.set( Calendar.DAY_OF_WEEK, (A+1) );

                                    if(dayBoxes[A].isChecked())
                                    {
                                            // creates a new event and adds it to the database
                                        CalendarEvent newEvent = new CalendarEvent();

                                        newEvent.setDetails(details.getText().toString());
                                        newEvent.setStartDate(newStartTime.getTimeInMillis());
                                        newEvent.setEndDate(newEndTime.getTimeInMillis());

                                        database.addEvent(newEvent);

                                    }

                                }

                            }

                            newStartTime.add(Calendar.WEEK_OF_YEAR, 1);
                            newEndTime.add(Calendar.WEEK_OF_YEAR, 1);

                        }


                        break;
                    }




                        // same code as the weekly case, except the weeks are incremented by 2
                    case (2):
                    {
                        Calendar newStartTime = (Calendar) startTime.clone();
                        Calendar newEndTime = (Calendar) endTime.clone();
                        Calendar endOfYear = new GregorianCalendar(2017, 5, 1);


                        int beginningWeek = newStartTime.get(Calendar.WEEK_OF_YEAR);
                        int curDay = newStartTime.get(Calendar.DAY_OF_WEEK);


                        while( newStartTime.before(endOfYear) )
                        {

                            // handles the week with the original year
                            if(newStartTime.get(Calendar.WEEK_OF_YEAR) == beginningWeek)
                            {
                                // Calendar.DAY_OF_WEEK has Sunday at 1, versus the dayBoxes array, which has Sunday at 0
                                for(int A = curDay ; A < 7 ; A++)
                                {

                                    newStartTime.set( Calendar.DAY_OF_WEEK, (A+1) );
                                    newEndTime.set( Calendar.DAY_OF_WEEK, (A+1) );

                                    if(dayBoxes[A].isChecked())
                                    {
                                            // creates a new event and adds it to the database
                                        CalendarEvent newEvent = new CalendarEvent();

                                        newEvent.setDetails(details.getText().toString());
                                        newEvent.setStartDate(newStartTime.getTimeInMillis());
                                        newEvent.setEndDate(newEndTime.getTimeInMillis());

                                        database.addEvent(newEvent);

                                    }

                                }

                            }


                            else
                            {

                                for(int A = 0 ; A < 7 ; A++)
                                {

                                    newStartTime.set( Calendar.DAY_OF_WEEK, (A+1) );
                                    newEndTime.set( Calendar.DAY_OF_WEEK, (A+1) );

                                    if(dayBoxes[A].isChecked())
                                    {

                                            // creates a new event and adds it to the database
                                        CalendarEvent newEvent = new CalendarEvent();

                                        newEvent.setDetails(details.getText().toString());
                                        newEvent.setStartDate(newStartTime.getTimeInMillis());
                                        newEvent.setEndDate(newEndTime.getTimeInMillis());

                                        database.addEvent(newEvent);

                                    }

                                }

                            }

                            newStartTime.add(Calendar.WEEK_OF_YEAR, 2);
                            newEndTime.add(Calendar.WEEK_OF_YEAR, 2);

                        }


                        break;
                    }






                            // events that recur monthly
                    case (3):
                    {
                            // keeps track of the current date (the date that the user was originally editing)
                        int beginningDate = startTime.get(Calendar.DAY_OF_MONTH);

                                // Java Calendar library indexes the months of the year starting with January at 0
                        int[] months = {7, 8, 9, 10, 11, 0, 1, 2, 3, 4}; // August to May
                        int curMonth = startTime.get(Calendar.MONTH);


                        boolean keepSkipping = true;


                        Calendar newStartTime = (Calendar) startTime.clone();
                        Calendar newEndTime = (Calendar) endTime.clone();


                        for (int A = 0; A < months.length; A++)
                        {

                            if(months[A] == curMonth)   // events will not be added to months that have already passed
                            {
                                keepSkipping = false;
                            }


                                // starts with the month immediately following the current date
                            if(!keepSkipping && months[A] != curMonth)
                            {

                                    // creates a new event and adds it to the database
                                newStartTime.add(Calendar.MONTH, 1);
                                newEndTime.add(Calendar.MONTH, 1);


                                CalendarEvent newEvent = new CalendarEvent();
                                newEvent.setDetails(details.getText().toString());
                                newEvent.setStartDate(newStartTime.getTimeInMillis());
                                newEvent.setEndDate(newEndTime.getTimeInMillis());

                                database.addEvent(newEvent);

                            }

                        }

                        break;
                    }




                }
        }






    public void addEvent(Calendar start, Calendar end)
    {
        CalendarEvent event = new CalendarEvent();
        event.setDetails(this.event.getDetails());
        event.setEndDate(end.getTimeInMillis());
        event.setStartDate(start.getTimeInMillis());
        database.addEvent(event);
    }

    /*End of code section*/



    // from DayView.java

    /**
     * precondition: array exists and is filled with correct values.
     * postcondition: text of ID:date in activity_day_view.xml is set to date user wanted.
     * Sets the proper text to display based on selected day.
     */
    public void fillDate() {
        DateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM d, YYYY");
        TextView t = (TextView) findViewById(R.id.add);
        t.setText(dateFormat.format(startTime.getTime()));
    }
}
