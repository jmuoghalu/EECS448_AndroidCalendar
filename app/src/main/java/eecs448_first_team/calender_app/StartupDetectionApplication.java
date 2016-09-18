package eecs448_first_team.calender_app;

import android.app.Application;

/**
 * Created by Hans on 9/18/2016.
 */
public class StartupDetectionApplication extends Application {
    private boolean firstStartedUp = true;

    /** Used to ensure when app run after closing, it takes user to activity they were last viewing with last viewed date
     * Evaluates if application has already been started, then notifies that application has already started
     * @return true if application has not been started yet, false if otherwise
     */
    public boolean Check_and_CommunicateStartedUp()
    {
        if(firstStartedUp)
        {
            firstStartedUp = false;
            return true;
        }
        return false;
    }
}
