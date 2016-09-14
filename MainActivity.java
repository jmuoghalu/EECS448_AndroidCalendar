package com.example.cara.calendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public final static String SEPTEMBER = "com.example.cara.calendar";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.januaryButton).setOnClickListener(this);
        findViewById(R.id.februaryButton).setOnClickListener(this);
        findViewById(R.id.marchButton).setOnClickListener(this);
        findViewById(R.id.aprilButton).setOnClickListener(this);
        findViewById(R.id.mayButton).setOnClickListener(this);
        findViewById(R.id.augustButton).setOnClickListener(this);
        findViewById(R.id.septemberButton).setOnClickListener(this);
        findViewById(R.id.octoberButton).setOnClickListener(this);
        Intent y = getIntent();
    }
    //public void DoStuff()
    //{
        //does stuff
    //}

    @Override
    public void onClick(View view) {
        //all
        switch(view.getId())
        {
            case(R.id.septemberButton):
            {
                Intent s = new Intent(this, Monthview.class);
                s.putExtra(SEPTEMBER, 9);
                startActivity(s);
                break;
            }
        }
    }
}
