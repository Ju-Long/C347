package com.myapplicationdev.android.lp1practical;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class TaskAdapter extends ArrayAdapter<Task> {

    private ArrayList<Task> tasks;
    private Context context;
    private TextView tvItem, tvDesc, tvDate;
    private Button btnShare;

    public TaskAdapter(Context context, int resources, ArrayList<Task> objects) {
        super(context, resources, objects);
        tasks = objects;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_task, parent, false);

        tvItem = (TextView) rowView.findViewById(R.id.tvItem);
        tvDesc = (TextView) rowView.findViewById(R.id.tvDesc);
        tvDate = (TextView) rowView.findViewById(R.id.tvDate);
        btnShare = (Button) rowView.findViewById(R.id.btnShare);

        tvItem.setText("Item: " + (position + 1));
        tvDesc.setText("Desc: " + tasks.get(position).getDescription());
        tvDate.setText("Date: " + tasks.get(position).getDate());


        btnShare.setOnClickListener(v -> {
            String message = "Task Desciption: " + tasks.get(position).getDescription() + "\nTask Date: " + tasks.get(position).getDate();
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"andy_tao@rp.edu.sg"});
            sendIntent.putExtra(Intent.EXTRA_SUBJECT, "A task is shared with you from 19013345");
            sendIntent.putExtra(Intent.EXTRA_TEXT, message);
            sendIntent.setType("text/plain");

            context.startActivity(Intent.createChooser(sendIntent, "Share!"));

        });
        return rowView;

    }
}
