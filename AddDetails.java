package com.example.cara.calendar2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class AddDetails extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details);

        findViewById(R.id.doneButton).setOnClickListener(this);
        findViewById(R.id.cancelButton).setOnClickListener(this);

        Intent getToDetails = getIntent();
    }
    @Override
    public void onClick(View view)
    {
        Intent goToDay = new Intent(this, DayView.class);
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
