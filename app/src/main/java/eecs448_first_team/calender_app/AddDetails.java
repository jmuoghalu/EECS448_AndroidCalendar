package com.example.cara.calendar2;

/**
 * author: Cara Fisher
 * date: 9-18-16
 * purpose: a mobile app page where details can be added to be displayed in DayView
 * 
 * Many thanks to Android (https://developer.android.com/index.html) for its help
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class AddDetails extends AppCompatActivity implements View.OnClickListener {
    public final static String DATA = "";
    public int[] array;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details);

        findViewById(R.id.doneButton).setOnClickListener(this);
        findViewById(R.id.cancelButton).setOnClickListener(this);

        Intent getToDetails = getIntent();
        array = getToDetails.getIntArrayExtra(DayView.DATA);
    }
    @Override
    public void onClick(View view)
    {
        Intent goToDay = new Intent(this, DayView.class);
        goToDay.putExtra(DATA, array);
        switch(view.getId())
        {
            case (R.id.doneButton):
            {
                startActivity(goToDay);
                break;
            }
            case (R.id.cancelButton):
            {
                startActivity(goToDay);
                break;
            }
        }
    }
}
