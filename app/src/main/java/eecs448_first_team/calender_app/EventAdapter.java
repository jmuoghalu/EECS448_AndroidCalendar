package eecs448_first_team.calender_app;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by mbauer on 10/9/16.
 * based off of guide available here https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView
 * EventAdapter creates a view for each calendar event that is provided.\
 * This is used by day view to display events.
 */

public class EventAdapter extends ArrayAdapter<CalendarEvent> {
    public EventAdapter(Context context, List<CalendarEvent> events) {
        super(context, 0, events);
    }

    /**
     * Does the event conflict with other events in the adapter.
     * THat is, does the start time and end time of this event overlap with the start time or
     * end time of one of the events in the adapter.
     * @param event1 The event to use
     * @return True if the event conflicts with another event in the list of event sprovided
     */
    public boolean doesConflict(CalendarEvent event1) {
        for (int i = 0; i < getCount(); i++) {
            CalendarEvent event2 = getItem(i);
            if (event2.getID() == event1.getID()) {
                continue;
            }

            // check for conflicting events
            if (event2.getStartDate() < event1.getEndDate() && event2.getEndDate() > event1.getStartDate()) {
                return true;
            }
        }

        return false;
    }

    /**
     * Get the view for each event.
     * @param position Position for each element in the view
     * @param convertView The view provided for each laout
     * @param parent The parent element of the view
     * @return An initialized view object
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        CalendarEvent event = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event, parent, false);
        }

        // Lookup view for data population

        TextView details = (TextView) convertView.findViewById(R.id.details);
        details.setText(event.getDetails());

        String formatString;

        // convert start and end dates to calendars
        Calendar startCal = Calendar.getInstance();
        startCal.setTimeInMillis(event.getStartDate());
        Calendar endCal = Calendar.getInstance();
        endCal.setTimeInMillis(event.getEndDate());

        // show shorter format if on the same day
        if (startCal.get(Calendar.DAY_OF_MONTH) == endCal.get(Calendar.DAY_OF_MONTH) &&
                startCal.get(Calendar.MONTH) == endCal.get(Calendar.MONTH) &&
                startCal.get(Calendar.YEAR) == endCal.get(Calendar.YEAR)) {
            formatString = "HH:mm aaa";
        } else {
            formatString = "EEE, d MMM yyyy HH:mm aaa";
        }

        // display the start and end time for the event

        DateFormat dateFormat = new SimpleDateFormat(formatString);

        TextView startTime = (TextView) convertView.findViewById(R.id.startTime);
        startTime.setText(dateFormat.format(event.getStartDate()));

        TextView endTime = (TextView) convertView.findViewById(R.id.endTime);
        endTime.setText(dateFormat.format(event.getEndDate()));

        // if the events conflict, mark it with red text
        if (doesConflict(event)) { // mark events that conflict
            startTime.setTextColor(Color.RED);
            endTime.setTextColor(Color.RED);
        }

        // Return the completed view to render on screen
        return convertView;
    }
}