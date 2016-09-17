package eecs448_first_team.calender_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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
