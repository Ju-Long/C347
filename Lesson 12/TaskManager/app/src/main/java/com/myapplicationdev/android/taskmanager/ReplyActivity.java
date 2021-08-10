package com.myapplicationdev.android.taskmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.RemoteInput;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class ReplyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);

        CharSequence reply = null;
        Intent intent = getIntent();
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if (remoteInput != null){
            reply = remoteInput.getCharSequence("status");
        }

        if(reply != null){
            DBHelper dbHelper = new DBHelper(ReplyActivity.this);
            int id = intent.getIntExtra("id", 0);
            if (dbHelper.deleteTask(id))
                Toast.makeText(ReplyActivity.this, "deleted", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(ReplyActivity.this, "failed to delete", Toast.LENGTH_SHORT).show();
            dbHelper.close();
            Intent lvIntent = new Intent(ReplyActivity.this, MainActivity.class);
            startActivity(lvIntent);
        }
    }
}