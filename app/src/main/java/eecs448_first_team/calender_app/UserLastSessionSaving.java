package eecs448_first_team.calender_app;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Hans on 9/18/2016.
 */
public class UserLastSessionSaving {
    SharedPreferences savePrefs;

    private static final String KEY_DAY = "day";
    private static final String KEY_MONTH = "month";
    private static final String KEY_YEAR = "year";
    private static final String KEY_FIRST_DAY = "firstmonthday";
    private static final String KEY_SUNDAY = "sunday_s_day";
    private static final String KEY_MONTH_SIZE = "sizeofmonth";
    private static final String KEY_PREV_SIZE = "sizeofprevmonth";
    private static final String KEY_WEEK = "week_num";
    private static final String KEY_LAST_VIEW = "lastViewUsed";
    private static final String SHARED_PREFS_NAME = "eecs448_first_team.calendar_app.userLastSessionData";

    public static final int VIEW_DAY = 0;
    public static final int VIEW_MONTH = 2;
    public static final int VIEW_WEEK = 1;
    public static final int VIEW_YEAR = 3;

    public UserLastSessionSaving(Context context)
    {
        savePrefs = context.getSharedPreferences(SHARED_PREFS_NAME,Context.MODE_PRIVATE);
    }

    public void SaveSessionDetails(int day, int month, int year, int firstMonthDay, int sunday_day, int month_day_size, int prev_month_size, int week_num, int currentView)
    {
        //store all of these in UserPrefs
        if(savePrefs != null)
        {
            SharedPreferences.Editor editor = savePrefs.edit();
            editor.putInt(KEY_DAY,day);
            editor.putInt(KEY_MONTH,month);
            editor.putInt(KEY_YEAR,year);
            editor.putInt(KEY_FIRST_DAY,firstMonthDay);
            editor.putInt(KEY_SUNDAY,sunday_day);
            editor.putInt(KEY_MONTH_SIZE,month_day_size);
            editor.putInt(KEY_PREV_SIZE,prev_month_size);
            editor.putInt(KEY_WEEK,week_num);
            editor.putInt(KEY_LAST_VIEW,currentView);
            editor.commit();
        }
    }
    public int[] GetSessionDetails()
    {
        int[] returnArray = new int[8];
        returnArray[0] = savePrefs.getInt(KEY_DAY,1); //1st day
        returnArray[1] = savePrefs.getInt(KEY_MONTH,8); //1st day of August
        returnArray[2] = savePrefs.getInt(KEY_YEAR,2016); //1st day of August 2016
        returnArray[3] = savePrefs.getInt(KEY_FIRST_DAY,2); //August 2016 starts on Monday
        returnArray[4] = savePrefs.getInt(KEY_SUNDAY,7); //first sunday is on the 7th
        returnArray[5] = savePrefs.getInt(KEY_MONTH_SIZE,31); //August has 31 days
        returnArray[6] = savePrefs.getInt(KEY_PREV_SIZE,0); //unknown value for # days in prev month
        returnArray[7] = savePrefs.getInt(KEY_WEEK,1); //start on first week

        return returnArray;
    }
    public int GetLastSessionView()
    {
        return savePrefs.getInt(KEY_LAST_VIEW,VIEW_YEAR);
    }
}
