package com.myapplicationdev.android.taskmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class AddTaskActivity extends AppCompatActivity {

    int requestCode = 12345;
    int notificationID = 888;
    EditText name, description, delay;
    Button btnAdd, btnCancel;
    ArrayList<Task> al;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        name = findViewById(R.id.name_edittext);
        description = findViewById(R.id.description_edittext);
        delay = findViewById(R.id.remind_delay_edittext);
        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);

        al = new ArrayList<Task>();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationManager notificationManager = (NotificationManager)
                        getSystemService(NOTIFICATION_SERVICE);

                if (!name.getText().toString().isEmpty() && !description.getText().toString().isEmpty()) {
                    String nameString = name.getText().toString();
                    String descriptionString = description.getText().toString();

                    DBHelper db = new DBHelper(AddTaskActivity.this);
                    db.insertTask(descriptionString, nameString);
                    Toast.makeText(getApplicationContext(), "Inserted", Toast.LENGTH_SHORT).show();
                    Log.i("info", "Task: " + nameString + " Desc: " + descriptionString);
                    name.setText("");
                    description.setText("");
                    db.close();
                    Calendar cal = Calendar.getInstance();
                    cal.add(Calendar.SECOND, 5);

                    Intent intent = new Intent(AddTaskActivity.this,
                            ScheduledNotificationReceiver.class);

                    PendingIntent pendingIntent = PendingIntent.getBroadcast(
                            AddTaskActivity.this, requestCode,
                            intent, PendingIntent.FLAG_CANCEL_CURRENT);

                    AlarmManager am = (AlarmManager)
                            getSystemService(Activity.ALARM_SERVICE);
                    am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                            pendingIntent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Do not leave any fields blank",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}