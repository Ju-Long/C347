package com.myapplicationdev.android.demomusicplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button start, stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = findViewById(R.id.btnStart);
        stop = findViewById(R.id.btnStop);

        start.setOnClickListener(v -> {
            startService(new Intent(MainActivity.this, MyService.class));
        });

        stop.setOnClickListener(v -> {
            stopService(new Intent(MainActivity.this, MyService.class));
        });
    }

    public boolean checkPermission() {
        int permission = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return permission == PermissionChecker.PERMISSION_GRANTED;
    }
}