package com.myapplicationdev.android.p02_holiday;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class EventAdapter extends ArrayAdapter<String> {
    private ArrayList<String> events;
    private Context context;
    private TextView tvEvent;

    public EventAdapter(Context context, int resources, ArrayList<String> objects) {
        super(context, resources, objects);
        events = objects;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.mainrow, parent, false);

        tvEvent = (TextView) rowView.findViewById(R.id.typeofevent);

        String currEvent = events.get(position) != null ? events.get(position) : "none";
        tvEvent.setText(currEvent);

        return rowView;
    }
}
