package eecs448_first_team.calender_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class YearView extends AppCompatActivity implements View.OnClickListener {
    public final static String DATA = "";
    public int[] array = new int[8];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year_view);

        findViewById(R.id.januaryButton).setOnClickListener(this);
        findViewById(R.id.februaryButton).setOnClickListener(this);
        findViewById(R.id.marchButton).setOnClickListener(this);
        findViewById(R.id.aprilButton).setOnClickListener(this);
        findViewById(R.id.mayButton).setOnClickListener(this);
        findViewById(R.id.augustButton).setOnClickListener(this);
        findViewById(R.id.septemberButton).setOnClickListener(this);
        findViewById(R.id.octoberButton).setOnClickListener(this);
        findViewById(R.id.novemberButton).setOnClickListener(this);
        findViewById(R.id.decemberButton).setOnClickListener(this);

        Intent getToYear = getIntent();
    }
    @Override
    public void onClick(View view)
    {
        /**
         * Int array pass around [  0 = day
         *                          1 = month
         *                          2 = year
         *                          3 = 1st of month
         *                          4 = Sunday's day for WeekView
         *                          5 = # days in month
         *                          6 = # days in previous month
         *                          7 = week #
         *                       ]
         */
        final int dayIndex = 0;
        final int monthIndex = 1;
        final int yearIndex = 2;
        final int firstDayOfMonth = 3;
        final int sundayDayForWeekView = 4;
        final int numOfDaysInMonth = 5;
        final int numOfDaysInPrevMonth = 6;
        final int weekNum = 7;

        Intent goToMonth = new Intent(this, MonthView.class);
        switch(view.getId())
        {
            case(R.id.augustButton):
            {
                array[dayIndex] = SafeDate.day00.getDate();
                array[monthIndex] = SafeDate.August.getDate();
                array[yearIndex] = SafeDate.fallYear.getDate();
                array[firstDayOfMonth] = SafeDate.day02.getDate();
                array[sundayDayForWeekView] = SafeDate.day00.getDate();
                array[numOfDaysInMonth] = SafeDate.day30.getDate();
                array[numOfDaysInPrevMonth] = SafeDate.day00.getDate();
                array[weekNum] = SafeDate.day00.getDate();
                goToMonth.putExtra(DATA, array);
                startActivity(goToMonth);
                break;
            }
            case(R.id.septemberButton):
            {
                array[dayIndex] = SafeDate.day00.getDate();
                array[monthIndex] =  SafeDate.September.getDate();
                array[yearIndex] = SafeDate.fallYear.getDate();
                array[firstDayOfMonth] =  SafeDate.day05.getDate();
                array[sundayDayForWeekView] = SafeDate.day00.getDate();
                array[numOfDaysInMonth] = SafeDate.day30.getDate();
                array[numOfDaysInPrevMonth] = SafeDate.day31.getDate();
                array[weekNum] = SafeDate.day00.getDate();
                goToMonth.putExtra(DATA, array);
                startActivity(goToMonth);
                break;
            }
            case(R.id.octoberButton):
            {
                array[dayIndex] = SafeDate.day00.getDate();
                array[monthIndex] =  SafeDate.October.getDate();
                array[yearIndex] = SafeDate.fallYear.getDate();
                array[firstDayOfMonth] =  SafeDate.day07.getDate();
                array[sundayDayForWeekView] = SafeDate.day00.getDate();
                array[numOfDaysInMonth] = SafeDate.day31.getDate();
                array[numOfDaysInPrevMonth] = SafeDate.day30.getDate();
                array[weekNum] = SafeDate.day00.getDate();
                goToMonth.putExtra(DATA, array);
                startActivity(goToMonth);
                break;
            }
            case(R.id.novemberButton):
            {
                array[dayIndex] = SafeDate.day00.getDate();
                array[monthIndex] =  SafeDate.November.getDate();
                array[yearIndex] = SafeDate.fallYear.getDate();
                array[firstDayOfMonth] =  SafeDate.day03.getDate();
                array[sundayDayForWeekView] = SafeDate.day00.getDate();
                array[numOfDaysInMonth] = SafeDate.day30.getDate();
                array[numOfDaysInPrevMonth] = SafeDate.day31.getDate();
                array[weekNum] = SafeDate.day00.getDate();
                goToMonth.putExtra(DATA, array);
                startActivity(goToMonth);
                break;
            }
            case(R.id.decemberButton):
            {
                array[dayIndex] = SafeDate.day00.getDate();
                array[monthIndex] = SafeDate.December.getDate();
                array[yearIndex] = SafeDate.fallYear.getDate();
                array[firstDayOfMonth] = SafeDate.day05.getDate();
                array[sundayDayForWeekView] = SafeDate.day00.getDate();
                array[numOfDaysInMonth] = SafeDate.day31.getDate();
                array[numOfDaysInPrevMonth] = SafeDate.day30.getDate();
                array[weekNum] = SafeDate.day00.getDate();
                goToMonth.putExtra(DATA, array);
                startActivity(goToMonth);
                break;
            }
            case(R.id.januaryButton):
            {
                array[dayIndex] = SafeDate.day00.getDate();
                array[monthIndex] = SafeDate.January.getDate();
                array[yearIndex] = SafeDate.springYear.getDate();
                array[firstDayOfMonth] = SafeDate.day01.getDate();
                array[sundayDayForWeekView] = SafeDate.day00.getDate();
                array[numOfDaysInMonth] = SafeDate.day31.getDate();
                array[numOfDaysInPrevMonth] = SafeDate.day31.getDate();
                array[weekNum] = SafeDate.day00.getDate();
                goToMonth.putExtra(DATA, array);
                startActivity(goToMonth);
                break;
            }
            case(R.id.februaryButton):
            {
                array[dayIndex] = SafeDate.day00.getDate();
                array[monthIndex] = SafeDate.Febuary.getDate();
                array[yearIndex] = SafeDate.springYear.getDate();
                array[firstDayOfMonth] = SafeDate.day04.getDate();
                array[sundayDayForWeekView] = SafeDate.day00.getDate();
                array[numOfDaysInMonth] = SafeDate.day28.getDate();
                array[numOfDaysInPrevMonth] = SafeDate.day31.getDate();
                array[weekNum] = SafeDate.day00.getDate();
                goToMonth.putExtra(DATA, array);
                startActivity(goToMonth);
                break;
            }
            case(R.id.marchButton):
            {
                array[dayIndex] = SafeDate.day00.getDate();
                array[monthIndex] = SafeDate.March.getDate();
                array[yearIndex] = SafeDate.springYear.getDate();
                array[firstDayOfMonth] = SafeDate.day04.getDate();
                array[sundayDayForWeekView] = SafeDate.day00.getDate();
                array[numOfDaysInMonth] = SafeDate.day31.getDate();
                array[numOfDaysInPrevMonth] = SafeDate.day28.getDate();
                array[weekNum] = SafeDate.day00.getDate();
                goToMonth.putExtra(DATA, array);
                startActivity(goToMonth);
                break;
            }
            case(R.id.aprilButton):
            {
                array[dayIndex] = SafeDate.day00.getDate();
                array[monthIndex] = SafeDate.April.getDate();
                array[yearIndex] = SafeDate.springYear.getDate();
                array[firstDayOfMonth] = SafeDate.day07.getDate();
                array[sundayDayForWeekView] = SafeDate.day00.getDate();
                array[numOfDaysInMonth] = SafeDate.day30.getDate();
                array[numOfDaysInPrevMonth] = SafeDate.day31.getDate();
                array[weekNum] = SafeDate.day00.getDate();
                goToMonth.putExtra(DATA, array);
                startActivity(goToMonth);
                break;
            }
            case(R.id.mayButton):
            {
                array[dayIndex] = SafeDate.day00.getDate();
                array[monthIndex] = SafeDate.May.getDate();
                array[yearIndex] = SafeDate.springYear.getDate();
                array[firstDayOfMonth] = SafeDate.day02.getDate();
                array[sundayDayForWeekView] = SafeDate.day00.getDate();
                array[numOfDaysInMonth] = SafeDate.day31.getDate();
                array[numOfDaysInPrevMonth] = SafeDate.day30.getDate();
                array[weekNum] = SafeDate.day00.getDate();
                goToMonth.putExtra(DATA, array);
                startActivity(goToMonth);
                break;
            }
        }
    }
}
