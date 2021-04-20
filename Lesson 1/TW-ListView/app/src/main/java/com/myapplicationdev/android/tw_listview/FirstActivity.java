package com.myapplicationdev.android.tw_listview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FirstActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<String> al;
    YearAdapter aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) this.findViewById(R.id.lvYear);

        al = new ArrayList<String>();
        al.add("Year 1");
        al.add("Year 2");
        al.add("Year 3");
        aa = new YearAdapter(this, R.layout.courserow, al);
        lv.setAdapter(aa);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String selectedYear = al.get(position);

                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                //based on item add info to intent
                intent.putExtra("year", selectedYear);
                startActivity(intent);
            }
        });
    }
}
