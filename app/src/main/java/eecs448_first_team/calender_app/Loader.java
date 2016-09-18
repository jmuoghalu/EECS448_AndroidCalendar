package eecs448_first_team.calender_app;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

/**
 * Created by Paul on 9/17/2016.
 * Trying to adapt
 * https://developer.android.com/guide/topics/data/data-storage.html#pref
 * and
 * https://classroom.udacity.com/courses/ud853/lessons/1474559101/concepts/164
 * http://stackoverflow.com/questions/3624280/how-to-use-sharedpreferences-in-android-to-store-fetch-and-edit-values
 * https://developer.android.com/training/basics/data-storage/shared-preferences.html#ReadSharedPreference
 */
public class Loader extends Activity {

    public int restoreDate(String inDateString){
        SharedPreferences dateStored = getPreferences(MODE_PRIVATE);
        int missingMonth = 0;
        return dateStored.getInt((inDateString), missingMonth);
    }

    public void writeDate(String inDateString, int inDateInt){

        SharedPreferences date = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editDate = date.edit();
        editDate.putInt(inDateString, inDateInt);
        editDate.apply();
    }
}
