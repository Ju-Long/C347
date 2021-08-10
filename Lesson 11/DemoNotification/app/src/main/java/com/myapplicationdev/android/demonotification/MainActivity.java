package com.myapplicationdev.android.demonotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    int requestCode = 123;
    int notificationID = 888;
    Button btnNotify1, btnNotify2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNotify1 = findViewById(R.id.btnNotify1);
        btnNotify2 = findViewById(R.id.btnNotify2);

        btnNotify1.setOnClickListener(view -> {
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel("default", "Default Channel",
                        NotificationManager.IMPORTANCE_DEFAULT);
                channel.setDescription("This is for default notification");
                notificationManager.createNotificationChannel(channel);
            }

            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            PendingIntent pIntent = PendingIntent.getActivity(MainActivity.this, requestCode, intent, PendingIntent.FLAG_CANCEL_CURRENT);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "default");
            builder.setContentTitle("Amazing Offer!");
            builder.setContentText("Subject");
            builder.setSmallIcon(android.R.drawable.btn_star_big_off);
            builder.setContentIntent(pIntent);
            builder.setAutoCancel(true);

            Notification notification = builder.build();
            notificationManager.notify(notificationID, notification);
            finish();
        });

        btnNotify2.setOnClickListener(view -> {
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel("default", "Default Channel",
                        NotificationManager.IMPORTANCE_DEFAULT);
                channel.setDescription("This is for default notification");
                notificationManager.createNotificationChannel(channel);
            }

            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            PendingIntent pIntent = PendingIntent.getActivity(MainActivity.this, requestCode, intent, PendingIntent.FLAG_CANCEL_CURRENT);

            NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
            bigText.setBigContentTitle("Big Text - Long Content");
            bigText.bigText("This is one big text - A quick brown fox jumps over a lazy brown dog \n Lorem ipsum dolor sit amet, sea eu quod des");
            bigText.setSummaryText("Reflection Journal");

            NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "default");
            builder.setContentTitle("Amazing Offer!");
            builder.setContentText("Subject");
            builder.setSmallIcon(android.R.drawable.btn_star_big_off);
            builder.setContentIntent(pIntent);
            builder.setStyle(bigText);
            builder.setAutoCancel(true);

            Notification notification = builder.build();
            notificationManager.notify(notificationID, notification);
            finish();
        });
    }
}