package com.example.cara.calendar2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class YearView extends AppCompatActivity implements View.OnClickListener {

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
        Intent goToMonth = new Intent(this, MonthView.class);
        switch(view.getId())
        {
            case(R.id.augustButton):
            {
                goToMonth.putExtra(MONTH, 8);
                startActivity(goToMonth);
                break;
            }
            case(R.id.septemberButton):
            {
                goToMonth.putExtra(MONTH, 9);
                startActivity(goToMonth);
                break;
            }
            case(R.id.octoberButton):
            {
                goToMonth.putExtra(MONTH, 10);
                startActivity(goToMonth);
                break;
            }
            case(R.id.novemberButton):
            {
                goToMonth.putExtra(MONTH, 11);
                startActivity(goToMonth);
                break;
            }
            case(R.id.decemberButton):
            {
                goToMonth.putExtra(MONTH, 12);
                startActivity(goToMonth);
                break;
            }
            case(R.id.januaryButton):
            {
                goToMonth.putExtra(MONTH, 1);
                startActivity(goToMonth);
                break;
            }
            case(R.id.februaryButton):
            {
                goToMonth.putExtra(MONTH, 2);
                startActivity(goToMonth);
                break;
            }
            case(R.id.marchButton):
            {
                goToMonth.putExtra(MONTH, 3);
                startActivity(goToMonth);
                break;
            }
            case(R.id.aprilButton):
            {
                goToMonth.putExtra(MONTH, 4);
                startActivity(goToMonth);
                break;
            }
            case(R.id.mayButton):
            {
                goToMonth.putExtra(MONTH, 5);
                startActivity(goToMonth);
                break;
            }
        }
    }
}
