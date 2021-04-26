package com.myapplicationdev.android.lp1practical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<Task> al;
    TaskAdapter aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.listView);

        al = new ArrayList<Task>();
        al.add(new Task("Group discussion", "1 May 2021"));
        al.add(new Task("Assignment submission", "5 May 2021"));
        al.add(new Task("Online course", "19 Jun 2021"));

        aa = new TaskAdapter(this, R.layout.row_task, al);
        lv.setAdapter(aa);
    }
}