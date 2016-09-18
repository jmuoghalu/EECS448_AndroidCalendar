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
    public void onClick(View view) {
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

        /*
         * To reduce the number of function calls down, creating local variables
          */
        int nullDate = SafeDate.day00.getDate();
        int fallYear = SafeDate.fallYear.getDate();
        int springYear = SafeDate.springYear.getDate();
        int day30 = SafeDate.day30.getDate();
        int day31 = SafeDate.day31.getDate();

        Intent goToMonth = new Intent(this, MonthView.class);
        switch (view.getId()) {
            case (R.id.augustButton): {
                array[dayIndex] = nullDate;
                array[monthIndex] = SafeDate.August.getDate();
                array[yearIndex] = fallYear;
                array[firstDayOfMonth] = SafeDate.day02.getDate();
                array[sundayDayForWeekView] = nullDate;
                array[numOfDaysInMonth] = day30;
                array[numOfDaysInPrevMonth] = nullDate;
                array[weekNum] = nullDate;
                goToMonth.putExtra(DATA, array);
                startActivity(goToMonth);
                break;
            }
            case (R.id.septemberButton): {
                array[dayIndex] = nullDate;
                array[monthIndex] = SafeDate.September.getDate();
                array[yearIndex] = fallYear;
                array[firstDayOfMonth] = SafeDate.day05.getDate();
                array[sundayDayForWeekView] = nullDate;
                array[numOfDaysInMonth] = day30;
                array[numOfDaysInPrevMonth] = day31;
                array[weekNum] = nullDate;
                goToMonth.putExtra(DATA, array);
                startActivity(goToMonth);
                break;
            }
            case (R.id.octoberButton): {
                array[dayIndex] = nullDate;
                array[monthIndex] = SafeDate.October.getDate();
                array[yearIndex] = fallYear;
                array[firstDayOfMonth] = SafeDate.day07.getDate();
                array[sundayDayForWeekView] = nullDate;
                array[numOfDaysInMonth] = day31;
                array[numOfDaysInPrevMonth] = day30;
                array[weekNum] = nullDate;
                goToMonth.putExtra(DATA, array);
                startActivity(goToMonth);
                break;
            }
            case (R.id.novemberButton): {
                array[dayIndex] = nullDate;
                array[monthIndex] = SafeDate.November.getDate();
                array[yearIndex] = fallYear;
                array[firstDayOfMonth] = SafeDate.day03.getDate();
                array[sundayDayForWeekView] = nullDate;
                array[numOfDaysInMonth] = day30;
                array[numOfDaysInPrevMonth] = day31;
                array[weekNum] = nullDate;
                goToMonth.putExtra(DATA, array);
                startActivity(goToMonth);
                break;
            }
            case (R.id.decemberButton): {
                array[dayIndex] = nullDate;
                array[monthIndex] = SafeDate.December.getDate();
                array[yearIndex] = fallYear;
                array[firstDayOfMonth] = SafeDate.day05.getDate();
                array[sundayDayForWeekView] = nullDate;
                array[numOfDaysInMonth] = day31;
                array[numOfDaysInPrevMonth] = day30;
                array[weekNum] = nullDate;
                goToMonth.putExtra(DATA, array);
                startActivity(goToMonth);
                break;
            }
            case (R.id.januaryButton): {
                array[dayIndex] = nullDate;
                array[monthIndex] = SafeDate.January.getDate();
                array[yearIndex] = springYear;
                array[firstDayOfMonth] = SafeDate.day01.getDate();
                array[sundayDayForWeekView] = nullDate;
                array[numOfDaysInMonth] = day31;
                array[numOfDaysInPrevMonth] = day31;
                array[weekNum] = nullDate;
                goToMonth.putExtra(DATA, array);
                startActivity(goToMonth);
                break;
            }
            case (R.id.februaryButton): {
                array[dayIndex] = nullDate;
                array[monthIndex] = SafeDate.February.getDate();
                array[yearIndex] = springYear;
                array[firstDayOfMonth] = SafeDate.day04.getDate();
                array[sundayDayForWeekView] = nullDate;
                array[numOfDaysInMonth] = SafeDate.day28.getDate();
                array[numOfDaysInPrevMonth] = day31;
                array[weekNum] = nullDate;
                goToMonth.putExtra(DATA, array);
                startActivity(goToMonth);
                break;
            }
            case (R.id.marchButton): {
                array[dayIndex] = nullDate;
                array[monthIndex] = SafeDate.March.getDate();
                array[yearIndex] = springYear;
                array[firstDayOfMonth] = SafeDate.day04.getDate();
                array[sundayDayForWeekView] = nullDate;
                array[numOfDaysInMonth] = day31;
                array[numOfDaysInPrevMonth] = SafeDate.day28.getDate();
                array[weekNum] = nullDate;
                goToMonth.putExtra(DATA, array);
                startActivity(goToMonth);
                break;
            }
            case (R.id.aprilButton): {
                array[dayIndex] = nullDate;
                array[monthIndex] = SafeDate.April.getDate();
                array[yearIndex] = springYear;
                array[firstDayOfMonth] = SafeDate.day07.getDate();
                array[sundayDayForWeekView] = nullDate;
                array[numOfDaysInMonth] = day30;
                array[numOfDaysInPrevMonth] = day31;
                array[weekNum] = nullDate;
                goToMonth.putExtra(DATA, array);
                startActivity(goToMonth);
                break;
            }
            case (R.id.mayButton): {
                array[dayIndex] = nullDate;
                array[monthIndex] = SafeDate.May.getDate();
                array[yearIndex] = springYear;
                array[firstDayOfMonth] = SafeDate.day02.getDate();
                array[sundayDayForWeekView] = nullDate;
                array[numOfDaysInMonth] = day31;
                array[numOfDaysInPrevMonth] = day30;
                array[weekNum] = nullDate;
                goToMonth.putExtra(DATA, array);
                startActivity(goToMonth);
                break;
            }
        }
    }

    /**
     * @param savedInstanceState Bundles the values of of data and array and saves them once the activity is left
     * @author Paul
     * @cite https://developer.android.com/training/basics/activity-lifecycle/recreating.html
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putIntArray(DATA, array);
        super.onSaveInstanceState(savedInstanceState);
    }
}