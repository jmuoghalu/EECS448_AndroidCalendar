package eecs448_first_team.calender_app;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

/**
 * Created by Paul on 9/17/2016.
 * Trying to adapt
 * https://developer.android.com/guide/topics/data/data-storage.html#pref
 * and
 * https://classroom.udacity.com/courses/ud853/lessons/1474559101/concepts/164
 */
public class Loader extends Activity {
    public static final String month = "month";
    public static final String day = "day";

    protected void OnStart(Bundle old){
        super.onStart();

        SharedPreferences monthView = getSharedPreferences("month", MODE_PRIVATE);
        int month = monthView.getInt("month", R.id.monthName);

        SharedPreferences dayView = getSharedPreferences("month", MODE_PRIVATE);
        int day = dayView.getInt("day", R.id.date);

    }

    @Override
    protected void onStop(){
        super.onStop();

        SharedPreferences monthView = getSharedPreferences(month, MODE_PRIVATE);
        SharedPreferences.Editor monthEdit = monthView.edit();
        monthEdit.putInt(month, R.id.monthName);
        monthEdit.commit();

        SharedPreferences dayView = getSharedPreferences(month, MODE_PRIVATE);
        SharedPreferences.Editor dayEdit = dayView.edit();
        dayEdit.putInt(day, R.id.date);
        dayEdit.commit();
    }
}
