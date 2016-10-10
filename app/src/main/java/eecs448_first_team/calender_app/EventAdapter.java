package eecs448_first_team.calender_app;

import android.content.Context;
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
 */

public class EventAdapter extends ArrayAdapter<CalendarEvent> {
    public EventAdapter(Context context, List<CalendarEvent> events) {
        super(context, 0, events);
    }

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

        DateFormat dateFormat = new SimpleDateFormat(formatString);

        TextView startTime = (TextView) convertView.findViewById(R.id.startTime);
        startTime.setText(dateFormat.format(event.getStartDate()));

        TextView endTime = (TextView) convertView.findViewById(R.id.endTime);
        endTime.setText(dateFormat.format(event.getEndDate()));

        // Return the completed view to render on screen
        return convertView;
    }
}