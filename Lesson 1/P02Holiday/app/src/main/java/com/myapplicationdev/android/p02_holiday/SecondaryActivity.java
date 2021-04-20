package com.myapplicationdev.android.p02_holiday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class SecondaryActivity extends AppCompatActivity {

    TextView tvHeader;
    ListView lv;
    ArrayList<Events> al = new ArrayList<Events>();
    EventDisplayAdapter aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        tvHeader = (TextView) findViewById(R.id.header);
        lv = (ListView) findViewById(R.id.SecondaryList);

        Intent intent = getIntent();
        String message = intent.getStringExtra("event");

        tvHeader.setText(message);
        if (message.equals("Secular")) {
            al.add(new Events("Secular", "New Year Day", "newyear", "2021-01-01"));
            al.add(new Events("Secular", "Labour Day", "labour", "2021-01-01"));
        } else if (message.equals("Ethnic & Religion")) {
            al.add(new Events("Ethnic & Religion", "Chinese New Year", "cny", "2021-01-01"));
            al.add(new Events("Ethnic & Religion", "Good Friday", "goodfriday", "2021-01-01"));
        }

        aa = new EventDisplayAdapter(this, R.layout.secondaryrow, al, message);
        lv.setAdapter(aa);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(SecondaryActivity.this, al.get(position).getEventName() + " Date: " + al.get(position).getEventDate(), Toast.LENGTH_LONG).show();
            }
        });
    }
}