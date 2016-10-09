package eecs448_first_team.calender_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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

        // Return the completed view to render on screen
        return convertView;
    }
}