package com.myapplicationdev.android.p02_holiday;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class EventDisplayAdapter extends ArrayAdapter<Events> {
    private ArrayList<Events> events;
    private Context context;
    private ImageView iv;
    private TextView tvEventName, tvEventDate;
    private String event;

    public EventDisplayAdapter(Context context, int resources, ArrayList<Events> objects, String event) {
        super(context, resources, objects);
        events = objects;
        this.context = context;
        this.event = event;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.secondaryrow, parent, false);

        iv = (ImageView) rowView.findViewById(R.id.ivEvent);
        tvEventName = (TextView) rowView.findViewById(R.id.eventName);
        tvEventDate = (TextView) rowView.findViewById(R.id.eventDate);

        Events curr = events.get(position);
        switch (curr.getImageName()) {
            case "newyear":
                iv.setImageResource(R.drawable.newyear);
                break;
            case "labour":
                iv.setImageResource(R.drawable.labour);
                break;
            case "cny":
                iv.setImageResource(R.drawable.cny);
                break;
            case "goodfriday":
                iv.setImageResource(R.drawable.goodfriday);
                break;
        }
        tvEventName.setText(curr.getEventName());
        tvEventDate.setText(curr.getEventDate());

        return rowView;
    }
}
