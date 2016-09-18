package com.example.cara.calendar2;

/**
 * author: Cara Fisher
 * date: 9-18-16
 * purpose: a mobile app page that displays the months of the academic year for the user
 * 
 * Many thanks to Android (https://developer.android.com/index.html) for its help
 */
 
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
        //array: [day, month, year, day of 1st, Sunday's day for WeekView, # days in month, # days in previous month, week #]
        // Some of these are not known yet in YearView
        Intent goToMonth = new Intent(this, MonthView.class);
        switch(view.getId())
        {
            case(R.id.augustButton):
            {
                array[0]=0;
                array[1]=8;
                array[2]=2016;
                array[3]=2;
                array[4]=0;
                array[5]=31;
                array[6]=0;
                array[7]=0;
                goToMonth.putExtra(DATA, array);
                startActivity(goToMonth);
                break;
            }
            case(R.id.septemberButton):
            {
                array[0]=0;
                array[1]=9;
                array[2]=2016;
                array[3]=5;
                array[4]=0;
                array[5]=30;
                array[6]=31;
                array[7]=0;
                goToMonth.putExtra(DATA, array);
                startActivity(goToMonth);
                break;
            }
            case(R.id.octoberButton):
            {
                array[0]=0;
                array[1]=10;
                array[2]=2016;
                array[3]=7;
                array[4]=0;
                array[5]=31;
                array[6]=30;
                array[7]=0;
                goToMonth.putExtra(DATA, array);
                startActivity(goToMonth);
                break;
            }
            case(R.id.novemberButton):
            {
                array[0]=0;
                array[1]=11;
                array[2]=2016;
                array[3]=3;
                array[4]=0;
                array[5]=30;
                array[6]=31;
                array[7]=0;
                goToMonth.putExtra(DATA, array);
                startActivity(goToMonth);
                break;
            }
            case(R.id.decemberButton):
            {
                array[0]=0;
                array[1]=12;
                array[2]=2016;
                array[3]=5;
                array[4]=0;
                array[5]=31;
                array[6]=30;
                array[7]=0;
                goToMonth.putExtra(DATA, array);
                startActivity(goToMonth);
                break;
            }
            case(R.id.januaryButton):
            {
                array[0]=0;
                array[1]=1;
                array[2]=2017;
                array[3]=1;
                array[4]=0;
                array[5]=31;
                array[6]=31;
                array[7]=0;
                goToMonth.putExtra(DATA, array);
                startActivity(goToMonth);
                break;
            }
            case(R.id.februaryButton):
            {
                array[0]=0;
                array[1]=2;
                array[2]=2017;
                array[3]=4;
                array[4]=0;
                array[5]=28;
                array[6]=31;
                array[7]=0;
                goToMonth.putExtra(DATA, array);
                startActivity(goToMonth);
                break;
            }
            case(R.id.marchButton):
            {
                array[0]=0;
                array[1]=3;
                array[2]=2017;
                array[3]=4;
                array[4]=0;
                array[5]=31;
                array[6]=28;
                array[7]=0;
                goToMonth.putExtra(DATA, array);
                startActivity(goToMonth);
                break;
            }
            case(R.id.aprilButton):
            {
                array[0]=0;
                array[1]=4;
                array[2]=2017;
                array[3]=7;
                array[4]=0;
                array[5]=30;
                array[6]=31;
                array[7]=0;
                goToMonth.putExtra(DATA, array);
                startActivity(goToMonth);
                break;
            }
            case(R.id.mayButton):
            {
                array[0]=0;
                array[1]=5;
                array[2]=2017;
                array[3]=2;
                array[4]=0;
                array[5]=31;
                array[6]=30;
                array[7]=0;
                goToMonth.putExtra(DATA, array);
                startActivity(goToMonth);
                break;
            }
        }
    }
}
