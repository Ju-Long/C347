package com.myapplicationdev.android.demoschedulednotification;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button btnSchedule;
    int reqCode = 12345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSchedule = findViewById(R.id.btnSchedule);
        btnSchedule.setOnClickListener(view -> {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.SECOND, 5);

            Intent intent = new Intent(MainActivity.this, ScheduledNotificationReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, reqCode, intent, PendingIntent.FLAG_CANCEL_CURRENT);
            AlarmManager alarmManager = (AlarmManager) getSystemService(Activity.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
        });
    }
}