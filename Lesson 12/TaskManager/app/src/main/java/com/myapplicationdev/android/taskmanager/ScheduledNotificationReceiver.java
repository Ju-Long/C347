package com.myapplicationdev.android.taskmanager;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import java.util.Calendar;

public class ScheduledNotificationReceiver extends BroadcastReceiver {

    int reqCode = 12345;
    int notificationID = 888;
    @Override
    public void onReceive(Context context, Intent intent)  {
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new
                    NotificationChannel("default", "Default Channel",
                    NotificationManager.IMPORTANCE_DEFAULT);

            channel.setDescription("This is for default notification");
            notificationManager.createNotificationChannel(channel);
        }

        Intent i = new Intent(context, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(context, reqCode,
                i, PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationCompat.Action action = new
                NotificationCompat.Action.Builder(
                R.mipmap.ic_launcher,
                "This is an Action",
                pIntent).build();

        NotificationCompat.WearableExtender extender = new NotificationCompat.WearableExtender();
        extender.addAction(action);

        String title = intent.getStringExtra("dataName");
        String text = intent.getStringExtra("dataDescription");

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "default");
        builder.setContentTitle(title);
        builder.setContentText(text);
        builder.setSmallIcon(android.R.drawable.ic_dialog_info);

        builder.extend(extender);
        Notification n = builder.build();
        notificationManager.notify(reqCode, n);
    }
}
