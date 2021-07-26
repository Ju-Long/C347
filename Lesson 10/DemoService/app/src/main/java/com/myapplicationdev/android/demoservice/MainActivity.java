package com.myapplicationdev.android.demoservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnStart, btnStop, btnBind, btnUnbind;
    private MyService.DownloadBinder downloadBinder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (MyService.DownloadBinder) service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        btnBind = findViewById(R.id.btnBind);
        btnUnbind = findViewById(R.id.btnUnbind);

        btnStart.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MyService.class);
            startService(intent);
        });

        btnStop.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MyService.class);
            stopService(intent);
        });

        btnBind.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MyService.class);
            bindService(intent, connection, BIND_AUTO_CREATE);
        });

        btnUnbind.setOnClickListener(v -> {
            unbindService(connection);
        });
    }
}