package com.myapplicationdev.android.demoexplicitintent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int requestCodeForSupermanStats = 1;
    int requestCodesForBatmanStats = 2;
    TextView textViewSuperman, textViewBatman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewBatman = findViewById(R.id.textViewBatman);
        textViewSuperman = findViewById(R.id.textViewSuperman);

        textViewSuperman.setOnClickListener(v -> {
            Hero superman = new Hero("Superman", 100, 60);
            Intent i = new Intent(MainActivity.this, HeroStatsActivity.class);
            i.putExtra("hero", superman);
            startActivityForResult(i, requestCodeForSupermanStats);
        });

        textViewBatman.setOnClickListener(v -> {
            Hero batman = new Hero("Batman", 60, 90);
            Intent i = new Intent(MainActivity.this, HeroStatsActivity.class);
            i.putExtra("hero", batman);
            startActivityForResult(i, requestCodesForBatmanStats);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (data != null) {
                String like = data.getStringExtra("like");
                String statement = "";
                if (requestCode == requestCodeForSupermanStats)
                    statement = "You " + like + " Superman";
                if (requestCode == requestCodesForBatmanStats)
                    statement = "You " + like + " Batman";

                Toast.makeText(MainActivity.this, statement, Toast.LENGTH_LONG).show();
            }
        }
    }
}