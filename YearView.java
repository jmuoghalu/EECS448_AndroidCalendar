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
            default:
            {
                startActivity(goToMonth);
                break;
            }
        }
    }
}
