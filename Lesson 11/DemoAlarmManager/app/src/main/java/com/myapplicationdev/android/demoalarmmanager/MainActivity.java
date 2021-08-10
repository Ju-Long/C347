package com.myapplicationdev.android.demoalarmmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button btnSetAlarm;
    AlarmManager am;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSetAlarm = findViewById(R.id.btnAlarm);
        btnSetAlarm.setOnClickListener(view -> {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.SECOND, 5);
            Intent intent = new Intent(MainActivity.this, AlarmReceiverActivity.class);
            int reqCode = 12345;
            PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, reqCode, intent, PendingIntent.FLAG_CANCEL_CURRENT);
            am = (AlarmManager) getSystemService(Activity.ALARM_SERVICE);
            am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
        });
    }
}