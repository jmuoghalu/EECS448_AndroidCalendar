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
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class AddDetails extends AppCompatActivity implements View.OnClickListener {
    public static String[] time = {"12:00 AM","1:00 AM","2:00 AM","3:00 AM","4:00 AM","5:00 AM","6:00 AM","7:00 AM","8:00 AM","9:00 AM","10:00 AM","11:00 AM","12:00 PM","1:00 PM","2:00 PM","3:00 PM","4:00 PM","5:00 PM","6:00 PM","7:00 PM","8:00 PM","9:00 PM","10:00 PM","11:00 PM","12:00 PM"};
    public static String[] months = {"August","September","October","November","December","January","February","March","April","May"};
    public static String[] days1 = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28"};//february
    public static String[] days2 = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"};//september,november,april
    public static String[] days3 = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};//august,october,december,january,march,may
    public static String[] days;
//arrays holding info ^^
    public static String eDate;
    public static String eMonth;
    public static String sTime;
    public static String eTime;
// strings holding value of array^^
//    TextView startTimeText1 = (TextView) findViewById(R.id.startTimeText);
//    TextView endTimeText1 = (TextView) findViewById(R.id.endTimeText);
//    TextView startDateMonthText1 = (TextView) findViewById(R.id.startDateMonthText);
//    TextView endDateMonthText1 = (TextView) findViewById(R.id.endDateMonthText1);
//    TextView StartDateDayText1 = (TextView) findViewById(R.id.StartDateDayText);
//    TextView endDateDayText1 = (TextView) findViewById(R.id.endDateDayText);
//displays value of strings ^^
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
        int cycleBeginningTime = 0;
        int cycleEndingTime = 0;
        int cycleEndingDate=0;
        int cycleEndingMonth=0;

        goToDay.putExtra("day", day);
        goToDay.putExtra("month", month);
        goToDay.putExtra("year", year);

        switch(view.getId())
        {
            //time beginning upButtonBegin ButtonBegin, time ending upButtonEnd upButtonBegin,
            case (R.id.upButtonBegin):
            {
                        if(cycleBeginningTime == 23)
                        {
                            cycleBeginningTime=0;
                        }
                        else
                            cycleBeginningTime++;
                        sTime=time[cycleBeginningTime];
//                        startTimeText1.setText(sTime);
                        break;
            }
            case (R.id.upButtonEnd):
            {
                        if(cycleBeginningTime==0)
                        {
                            cycleBeginningTime=23;
                        }
                        else
                            cycleBeginningTime--;
                       sTime=time[cycleBeginningTime];
//                        startTimeText1.setText(sTime);
                break;
            }
            case (R.id.downButtonBegin): //end time
            {
                if(cycleEndingTime == 23)
                {
                    cycleEndingTime=0;
                }
                else
                    cycleEndingTime++;
                eTime=time[cycleEndingTime];
//                endTimeText1.setText(eTime);
                break;
            }
            case (R.id.downButtonEnd):
            {
                if(cycleEndingTime==0)
                {
                    cycleEndingTime=23;
                }
                else
                    cycleEndingTime--;
               eTime=time[cycleEndingTime];
//                endTimeText1.setText(eTime);
                break;
            }
                //endDateDayup, endDateDaydown, endDateMonthup,EndDateMonthDown
            case (R.id.endDateDayUp):
                    {
                        if(cycleEndingMonth==6)
                        {
                            days = days1;
                        }
                        else if(cycleEndingMonth==1 ||cycleEndingMonth== 3 || cycleEndingMonth==8)
                        {
                            days = days2;
                        }
                        else if(cycleEndingMonth==2 ||cycleEndingMonth== 4 || cycleEndingMonth==5 ||cycleEndingMonth== 7 ||cycleEndingMonth==9)
                        {
                            days = days3;
                        }
                        if(cycleEndingDate == days.length -1)
                        {
                            cycleEndingDate=0;
                        }
                        else
                            cycleEndingDate++;
                        eDate=days[cycleEndingDate];
//                        endDateDayText1.setText(eDate);

                        break;
                    }
            case (R.id.endDateDayDown):
            {
                {
                    if(cycleEndingMonth==6)
                    {
                        days = days1;
                    }
                    if(cycleEndingMonth==1 ||cycleEndingMonth== 3 || cycleEndingMonth==8)
                    {
                        days = days2;
                    }
                    if(cycleEndingMonth==2 ||cycleEndingMonth== 4 || cycleEndingMonth==5 ||cycleEndingMonth== 7 ||cycleEndingMonth==9)
                    {
                        days = days3;
                    }
                    if(cycleEndingDate == 0)
                    {
                        cycleEndingDate= days.length -1;
                    }
                    else
                        cycleEndingDate--;
                    eDate=days[cycleEndingDate];
//                    endDateDayText1.setText(eDate);
                    break;
                }
            }
            case (R.id.endDateMonthUp):
            {
                if(cycleEndingMonth == 9)
                {
                    cycleEndingMonth = 0;
                }
                else
                cycleEndingMonth++;
                eMonth=months[cycleEndingMonth];
//                endDateMonthText1.setText(eMonth);
                break;
            }
            case (R.id.endDateMonthDown):
            {
                if(cycleEndingMonth == 0)
                {
                    cycleEndingMonth = 9;
                }
                else
                    cycleEndingMonth--;
                eMonth=months[cycleEndingMonth];
//                endDateMonthText1.setText(eMonth);
                break;
            }
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
