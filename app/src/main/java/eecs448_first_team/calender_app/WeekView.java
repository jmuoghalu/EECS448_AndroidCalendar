
package eecs448_first_team.calender_app;

/**
 * author: Cara Fisher
 * date: 9-18-16
 * purpose: a mobile app page that displays the Sunday-Saturday surrounding a date
 *
 * Many thanks to Android (https://developer.android.com/index.html) for its help
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

        Intent getToWeek = getIntent();

        year = getToWeek.getIntExtra("year", 2016);
        week = getToWeek.getIntExtra("week", 26);

        cal = new GregorianCalendar();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, week);

        TextView textView = (TextView) findViewById(R.id.year);
        textView.setText("" + year);

        fillWeek();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.monthButton:
                Intent goToMonth = new Intent(this, MonthView.class);
                goToMonth.putExtra("year", cal.get(Calendar.YEAR));
                goToMonth.putExtra("month", cal.get(Calendar.MONTH));
                goToMonth.putExtra("day", cal.get(Calendar.DAY_OF_MONTH));
                startActivity(goToMonth);
                break;
            case R.id.yearButton:
                Intent goToYear = new Intent(this, YearDisplay.class);
                goToYear.putExtra("year", cal.get(Calendar.YEAR));
                goToYear.putExtra("month", cal.get(Calendar.MONTH));
                goToYear.putExtra("day", cal.get(Calendar.DAY_OF_MONTH));
                startActivity(goToYear);
                break;
            case R.id.addDetailsButton4:
                Intent goToAdd = new Intent(this, AddDetails.class);
                startActivity(goToAdd);
                break;
            default:
                GetWeekDay(view.getId());
                break;
        }
    }

    public void GetWeekDay(int viewId) {
        Calendar newcal = (Calendar) cal.clone();
        TextView t = (TextView) findViewById(R.id.sunday);
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
        }

        Intent goToDay = new Intent(this, DayView.class);
        goToDay.putExtra("year", newcal.get(Calendar.YEAR));
        goToDay.putExtra("month", newcal.get(Calendar.MONTH));
        goToDay.putExtra("day", newcal.get(Calendar.DAY_OF_MONTH));
        startActivity(goToDay);
    }

    /**
     * precondition: array exists and is filled with correct values.
     * postcondition: IDs sunday-saturday in activity_week_view.xml are filled with correct dates.
     */
    public void fillWeek() {
        DateFormat dateFormat = new SimpleDateFormat("EEEE, MMM. d");

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
     * @param savedInstanceState Bundles the values of of data and array and saves them once the activity is left
     * @see   : https://developer.android.com/training/basics/activity-lifecycle/recreating.html
     * @param : DATA (name of the array of the date we go too
     * @param : array (holds the destination and other relevant date integers)
     * @return none
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("year", year);
        savedInstanceState.putInt("week", week);
        super.onSaveInstanceState(savedInstanceState);
    }
}
