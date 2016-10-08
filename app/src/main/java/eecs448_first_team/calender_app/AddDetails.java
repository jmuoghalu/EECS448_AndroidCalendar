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
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class AddDetails extends AppCompatActivity implements View.OnClickListener {
    private CalendarEventDb database;
    private EditText theDetails; //the details text to be replaced

    private Calendar cal;
    private int day;
    private int month;
    private int year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details);

        findViewById(R.id.doneButton).setOnClickListener(this);
        findViewById(R.id.cancelButton).setOnClickListener(this);

        Intent getToDetails = getIntent();
        day = getToDetails.getIntExtra("day", 0);
        month = getToDetails.getIntExtra("month", 7);
        year = getToDetails.getIntExtra("year", 2016);

        cal = new GregorianCalendar(year, month, day);
        theDetails = (EditText)findViewById(R.id.edit);
    }
    @Override
    public void onResume()
    {
        super.onResume();
        database = new CalendarEventDb(this);

        theDetails.setText(database.getCalendarDetails(cal.getTimeInMillis()));
    }
    @Override
    public void onClick(View view)
    {
        Intent goToDay = new Intent(this, DayView.class);

        goToDay.putExtra("day", day);
        goToDay.putExtra("month", month);
        goToDay.putExtra("year", year);

        switch(view.getId())
        {
            case (R.id.doneButton):
            {
                String newDetails = theDetails.getText().toString();
                database.setCalendarDetails(cal, newDetails);
                Toast.makeText(this,"Details added!",Toast.LENGTH_LONG).show();
                this.finish();
                break;
            }
            case (R.id.cancelButton):
            {
                startActivity(goToDay);
                break;
            }
        }
    }

    @Override
    public void onStop()
    {
        super.onStop();
        database.close();
    }
}
