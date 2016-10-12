
package eecs448_first_team.calender_app;

/**
 * author: Cara Fisher
 * date: 9-18-16
 * purpose: a mobile app page that displays the Sunday-Saturday surrounding a date
 *
 * Many thanks to Android (https://developer.android.com/index.html) for its help
 *
 * Updated by "Team One" for Project 2.
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class WeekView extends AppCompatActivity implements View.OnClickListener {
    private Calendar cal;
    private int year;
    private int week;

    @Override
    /**
     * Handle the creation of this view. Used by AppCompatActivity
     * @param savedInstanceState Info to recover from previously create view.
     */
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
        findViewById(R.id.addDetailsButton4).setOnClickListener(this);

        // load information from previous view
        Intent getToWeek = getIntent();
        year = getToWeek.getIntExtra("year", 2016);
        week = getToWeek.getIntExtra("week", 26);

        // create calendar based on above info
        cal = new GregorianCalendar();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, week);

        // set the text for the year
        DateFormat weekFormat = new SimpleDateFormat("MMMM d");
        TextView textView = (TextView) findViewById(R.id.year);
        textView.setText("Week of " + weekFormat.format(cal.getTime()));

        fillWeek();
    }

    @Override
    /**
     * Android handler for a clicked view.
     * @param view The view that was "clicked" on the android application
     */
    public void onClick(View view) {
        // check based on the view's identifier
        switch (view.getId()) {

            case R.id.monthButton: // go to the month view
                Intent goToMonth = new Intent(this, MonthView.class);
                goToMonth.putExtra("year", cal.get(Calendar.YEAR));
                goToMonth.putExtra("month", cal.get(Calendar.MONTH));
                goToMonth.putExtra("day", cal.get(Calendar.DAY_OF_MONTH));
                startActivity(goToMonth);
                break;

            case R.id.yearButton: // go to the year view
                Intent goToYear = new Intent(this, YearDisplay.class);
                goToYear.putExtra("year", cal.get(Calendar.YEAR));
                goToYear.putExtra("month", cal.get(Calendar.MONTH));
                startActivity(goToYear);
                break;

            case R.id.addDetailsButton4: // go to the add details view
                Intent goToAdd = new Intent(this, AddDetails.class);
                startActivity(goToAdd);
                break;

            default: // assume that week day was selected, because that's all we are listening for
                     // in this view
                getWeekDay(view.getId());
                break;
        }
    }

    /**
     * Open the week day clicked as a new view.
     * @param viewId The identifier that was clicked.
     *               It should be one of R.id.sunday -> R.id.saturday. If not one of those,
     *               no action is taken.
     */
    public void getWeekDay(int viewId) {
        // create a new calendar so we don't break above calculations
        Calendar newcal = (Calendar) cal.clone();

        // set the day of the week based on which element was clicked
        switch(viewId) {
            case R.id.sunday:
                newcal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                break;

            case R.id.monday:
                newcal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                break;

            case R.id.tuesday:
                newcal.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
                break;

            case R.id.wednesday:
                newcal.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
                break;

            case R.id.thursday:
                newcal.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
                break;

            case R.id.friday:
                newcal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
                break;

            case R.id.saturday:
                newcal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
                break;

            default:
                return;
        }

        // go to date based on selected calendar date
        Intent goToDay = new Intent(this, DayView.class);
        goToDay.putExtra("year", newcal.get(Calendar.YEAR));
        goToDay.putExtra("month", newcal.get(Calendar.MONTH));
        goToDay.putExtra("day", newcal.get(Calendar.DAY_OF_MONTH));
        startActivity(goToDay);
    }

    /**
     * Fill the days of the week based on date formats
     * precondition: array exists and is filled with correct values.
     * postcondition: IDs sunday-saturday in activity_week_view.xml are filled with correct dates.
     */
    public void fillWeek() {
        // Used to display date as text
        DateFormat dateFormat = new SimpleDateFormat("EEEE, MMM. d");

        // Sunday -> Monday use similar process
        // take the current text view element,
        // create a new calendar,
        // set the current week day to one of Sunday -> Monday
        // format the date and set the text of the text view to formatted date

        TextView sunday = (TextView) findViewById(R.id.sunday);
        Calendar sundayCal = (Calendar) cal.clone();
        sundayCal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        sunday.setText(dateFormat.format(sundayCal.getTime()));

        TextView monday = (TextView) findViewById(R.id.monday);
        Calendar mondayCal = (Calendar) cal.clone();
        mondayCal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        monday.setText(dateFormat.format(mondayCal.getTime()));

        TextView tuesday = (TextView) findViewById(R.id.tuesday);
        Calendar tuesdayCal = (Calendar) cal.clone();
        tuesdayCal.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
        tuesday.setText(dateFormat.format(tuesdayCal.getTime()));

        TextView wednesday = (TextView) findViewById(R.id.wednesday);
        Calendar wednesdayCal = (Calendar) cal.clone();
        wednesdayCal.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
        wednesday.setText(dateFormat.format(wednesdayCal.getTime()));

        TextView thursday = (TextView) findViewById(R.id.thursday);
        Calendar thursdayCal = (Calendar) cal.clone();
        thursdayCal.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
        thursday.setText(dateFormat.format(thursdayCal.getTime()));

        TextView friday = (TextView) findViewById(R.id.friday);
        Calendar fridayCal = (Calendar) cal.clone();
        fridayCal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        friday.setText(dateFormat.format(fridayCal.getTime()));

        TextView saturday = (TextView) findViewById(R.id.saturday);
        Calendar saturdayCal = (Calendar) cal.clone();
        saturdayCal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        saturday.setText(dateFormat.format(saturdayCal.getTime()));
    }

    /**
     * Used to load state from previous instance.
     * @param savedInstanceState Bundles the values of of data and array and saves them once the activity is left
     * @see   : https://developer.android.com/training/basics/activity-lifecycle/recreating.html
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("year", year);
        savedInstanceState.putInt("week", week);
        super.onSaveInstanceState(savedInstanceState);
    }
}
