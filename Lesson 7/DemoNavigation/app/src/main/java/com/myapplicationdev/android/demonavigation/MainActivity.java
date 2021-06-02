package com.myapplicationdev.android.demonavigation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    Button btnNewActivity;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNewActivity = findViewById(R.id.btnNewActivity);
        fab = findViewById(R.id.fab);

        btnNewActivity.setOnClickListener(v -> {
            Intent i  = new Intent(MainActivity.this, NewActivity.class);
            startActivity(i);
        });

        fab.setOnClickListener(v -> {
            
        });
    }
}