package eecs448_first_team.calender_app;

/**
 * author: Cara Fisher
 * date: 9-18-16
 * purpose: a mobile app page where details can be added to be displayed in DayView.
 *
 * Many thanks to Android (https://developer.android.com/index.html) for its help
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AddDetails extends AppCompatActivity implements View.OnClickListener {
    private TextView startDateHourText;
    private TextView endDateHourText;
    private TextView startDateMonthText;
    private TextView endDateMonthText;
    private TextView startDateDayText;
    private TextView endDateDayText;
    private EditText details; //the details text to be replaced

    private CalendarEventDb database;

    private Calendar startTime;
    private Calendar endTime;

    private Long id = Long.valueOf(0);
    private CalendarEvent event = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details);

        findViewById(R.id.doneButton).setOnClickListener(this);
        findViewById(R.id.cancelButton).setOnClickListener(this);
        findViewById(R.id.startDateDayMinus).setOnClickListener(this);
        findViewById(R.id.startDateDayPlus).setOnClickListener(this);
        findViewById(R.id.startDateMonthMinus).setOnClickListener(this);
        findViewById(R.id.startDateMonthPlus).setOnClickListener(this);
        findViewById(R.id.startDateHourPlus).setOnClickListener(this);
        findViewById(R.id.startDateHourMinus).setOnClickListener(this);
        findViewById(R.id.endDateDayMinus).setOnClickListener(this);
        findViewById(R.id.endDateDayPlus).setOnClickListener(this);
        findViewById(R.id.endDateMonthMinus).setOnClickListener(this);
        findViewById(R.id.endDateMonthPlus).setOnClickListener(this);
        findViewById(R.id.endDateHourPlus).setOnClickListener(this);
        findViewById(R.id.endDateHourMinus).setOnClickListener(this);

        details = (EditText) findViewById(R.id.edit);

        startDateHourText = (TextView) findViewById(R.id.startDateHourText);
        endDateHourText = (TextView) findViewById(R.id.endDateHourText);
        startDateMonthText = (TextView) findViewById(R.id.startDateMonthText);
        endDateMonthText = (TextView) findViewById(R.id.endDateMonthText);
        startDateDayText = (TextView) findViewById(R.id.startDateDayText);
        endDateDayText = (TextView) findViewById(R.id.endDateDayText);

        Intent getToDetails = getIntent();
        id = getToDetails.getLongExtra("id", 0);

        int day = getToDetails.getIntExtra("day", 0);
        int month = getToDetails.getIntExtra("month", 0);
        int year = getToDetails.getIntExtra("year", 0);

        if (year != 0) {
            startTime = new GregorianCalendar(year, month, day);
            endTime = (Calendar) startTime.clone();
            endTime.add(Calendar.DAY_OF_YEAR, 1);
        } else {
            startTime = (Calendar) GregorianCalendar.getInstance();
            endTime = (Calendar) GregorianCalendar.getInstance();
            endTime.add(Calendar.DAY_OF_YEAR, 1);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if (event == null) {
            database = new CalendarEventDb(this);

            if (id != 0) {
                event = database.getCalendarEvent(id);
            }
        }

        if (event != null) {
            details.setText(event.getDetails());
            startTime.setTimeInMillis(event.getStartDate());
            endTime.setTimeInMillis(event.getEndDate());
        } else {
            event = new CalendarEvent();
            event.setDetails("");
            event.setStartDate(startTime.getTimeInMillis());
            event.setEndDate(endTime.getTimeInMillis());
        }

        updateTime();
    }

    public void updateTime() {
        SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM");
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm aaa");

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
    public void onClick(View view) {
        Intent goToDay = new Intent(this, DayView.class);

        goToDay.putExtra("day", startTime.get(Calendar.DAY_OF_MONTH));
        goToDay.putExtra("month", startTime.get(Calendar.MONTH));
        goToDay.putExtra("year", startTime.get(Calendar.YEAR));

        event.setDetails(details.getText().toString());
        event.setStartDate(startTime.getTimeInMillis());
        event.setEndDate(endTime.getTimeInMillis());

        switch (view.getId()) {
            case (R.id.startDateHourPlus):
                startTime.add(Calendar.HOUR, 1);
                break;
            case (R.id.startDateHourMinus):
                startTime.add(Calendar.HOUR, -1);
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
                endTime.add(Calendar.HOUR, 1);
                break;
            case (R.id.endDateHourMinus):
                endTime.add(Calendar.HOUR, -1);
                break;

            case (R.id.doneButton):
                if (event != null && event.getID() != null) {
                    database.updateEvent(event);
                } else {
                    database.addEvent(event);
                }

                this.finish();
                break;

            case (R.id.cancelButton):
                startActivity(goToDay);
                break;
        }

        updateTime();
    }

    @Override
    public void onStop()
    {
        super.onStop();
        database.close();
    }
}
