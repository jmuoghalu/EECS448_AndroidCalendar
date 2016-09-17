package eecs448_first_team.calender_app;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,CalendarView.OnDateChangeListener{

    CalendarView calendarView;
    Calendar interpreterCalendar;
    CalendarEventDb editDatabase;
    TextView feedbackText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
//        findViewById(R.id.testEventButton).setOnClickListener(this);
//        findViewById(R.id.addEventButton).setOnClickListener(this);
        calendarView = (CalendarView)findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(this);
        interpreterCalendar = Calendar.getInstance(); //WARNING: upon getting instance, Calendar sets its current EXACT time down to milliseconds to current time. You have to overwrite this!
//        interpreterCalendar.set(Calendar.YEAR,2000);
//        interpreterCalendar.set(Calendar.MONTH,6);
//        interpreterCalendar.set(Calendar.DAY_OF_MONTH,1);
        interpreterCalendar.set(Calendar.HOUR,0); //locks hours,minutes,seconds,MS in Calendar to 0 to make consistent, and so we don't have to zero them again later
        interpreterCalendar.set(Calendar.MINUTE,0); //thus, I can say with certainty that using the Calendar to convert 9-21-2016 to milliseconds will produce a constant value
        interpreterCalendar.set(Calendar.SECOND,0);
        interpreterCalendar.set(Calendar.MILLISECOND,0);
//        feedbackText = (TextView)findViewById(R.id.startText);
//        feedbackText.setText(((Long)calendarView.getDate()).toString());
    }

    @Override
    public void onResume()
    {
        super.onResume();
        editDatabase = new CalendarEventDb(this);
        feedbackText.setText(String.valueOf(interpreterCalendar.getTimeInMillis()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onPause()
    {
        super.onPause();
        editDatabase.close();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Called whenever a View that has this class as an OnClickListener is clicked
//    @Override
//    public void onClick(View v) {
//        switch(v.getId())
//        {
//            case(R.id.addEventButton): //test event adding
//            {
//                if(editDatabase.setCalendarDetails(interpreterCalendar.getTimeInMillis(),"Alternate Testing"))
//                {
//                    Toast.makeText(this,"Added successfully",Toast.LENGTH_SHORT).show();
//                }
//                else
//                {
//                    Toast.makeText(this,"Something failed",Toast.LENGTH_LONG).show();
//                }
//                break;
//            }
//            case(R.id.testEventButton): //test event existing
//            {
//                String outputVal = editDatabase.getCalendarDetails(interpreterCalendar.getTimeInMillis());
//                if(outputVal != null)
//                {
//                    Toast.makeText(this,"Value exists",Toast.LENGTH_SHORT).show();
//                    Toast.makeText(this,outputVal,Toast.LENGTH_SHORT).show();
//                }
//                else
//                {
//                    Toast.makeText(this,"IT failed",Toast.LENGTH_LONG).show();
//                }
//                break;
//            }
//        }
//
//    }

    @Override
    public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
        interpreterCalendar.set(year,month,dayOfMonth); //Calendar created by Calendar.getInstance();
        calendarView.setDate(interpreterCalendar.getTimeInMillis()); //the CalendarView
        Long outVal = view.getDate();
        Toast.makeText(this,"Date " + dayOfMonth + "-" + month + "-" + year,Toast.LENGTH_LONG).show();
        feedbackText.setText(outVal.toString());
    }
}
