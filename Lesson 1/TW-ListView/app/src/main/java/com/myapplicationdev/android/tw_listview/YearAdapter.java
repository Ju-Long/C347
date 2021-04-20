package com.myapplicationdev.android.tw_listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class YearAdapter extends ArrayAdapter<String> {
    private ArrayList<String> year;
    private Context context;
    private TextView tvYear;

    public YearAdapter(Context context, int resources, ArrayList<String> objects) {
        super(context, resources, objects);
        year = objects;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.courserow, parent, false);

        tvYear = (TextView) rowView.findViewById(R.id.tvYear);

        String currYear = year.get(position) != null ? year.get(position) : "none";
        tvYear.setText(currYear);

        return rowView;
    }
}
