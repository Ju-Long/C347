package com.myapplicationdev.android.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnGetTasks;
    TextView tvResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnGetTasks = (Button) findViewById(R.id.btnGetTasks);
        tvResults = (TextView) findViewById(R.id.tvResults);

        btnInsert.setOnClickListener(v -> {
            DBHelper db = new DBHelper(MainActivity.this);
            db.insertTask("Submit RJ", "25 Apr 2016");
            db.close();
        });

        btnGetTasks.setOnClickListener(v -> {
            DBHelper db = new DBHelper(MainActivity.this);
            ArrayList<String> data = db.getTaskContent();
            db.close();

            String txt = "";
            for (int i = 0; i < data.size(); i++) {
                Log.d("Database Content", i + ". " + data.get(i));
                txt += i + ". " + data.get(i) + "\n";
            }
            tvResults.setText(txt);
        });
    }
}