package eecs448_first_team.calender_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class DayView extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_view);

        findViewById(R.id.weekButton).setOnClickListener(this);
        findViewById(R.id.monthButton).setOnClickListener(this);
        findViewById(R.id.yearButton).setOnClickListener(this);
        findViewById(R.id.addDetailsButton).setOnClickListener(this);

        Intent getToDay = getIntent();
    }
    @Override
    public void onClick(View view)
    {
        switch(view.getId())
        {
            case(R.id.weekButton):
            {
                Intent goToWeek = new Intent(this, WeekView.class);
                startActivity(goToWeek);
                break;
            }
            case(R.id.monthButton):
            {
                Intent goToMonth = new Intent(this, MonthView.class);
                startActivity(goToMonth);
                break;
            }
            case(R.id.yearButton):
            {
                Intent goToYear = new Intent(this, YearView.class);
                startActivity(goToYear);
                break;
            }
            case(R.id.addDetailsButton):
            {
                Intent goToDetails = new Intent(this, AddDetails.class);
                startActivity(goToDetails);
                break;
            }
        }
    }
}
