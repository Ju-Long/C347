package com.myapplicationdev.android.p02_holiday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<String> al;
    EventAdapter aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.MainList);
        al = new ArrayList<String>();
        al.add("Secular");
        al.add("Ethnic & Religion");
        aa = new EventAdapter(this, R.layout.mainrow, al);
        lv.setAdapter(aa);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String event = al.get(position);

                Intent intent = new Intent(MainActivity.this, SecondaryActivity.class);
                intent.putExtra("event", event);
                startActivity(intent);
            }
        });
    }
}