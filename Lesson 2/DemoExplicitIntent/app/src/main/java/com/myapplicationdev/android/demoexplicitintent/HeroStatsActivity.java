package com.myapplicationdev.android.demoexplicitintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class HeroStatsActivity extends AppCompatActivity {

    TextView textViewName, textViewStrength, textViewTechnicalProwess;
    Button btnLike, btnDislike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_stats);

        Intent i = getIntent();
        Hero hero = (Hero) i.getSerializableExtra("hero");

        textViewName = (TextView) findViewById(R.id.textViewName);
        textViewStrength = (TextView) findViewById(R.id.textViewStrength);
        textViewTechnicalProwess = (TextView) findViewById(R.id.textViewTechnicalProwess);
        btnLike = (Button) findViewById(R.id.buttonLike);
        btnDislike = (Button) findViewById(R.id.buttonDislike);

        textViewName.setText(hero.getName());
        textViewStrength.setText(String.valueOf(hero.getStrength()));
        textViewTechnicalProwess.setText(String.valueOf(hero.getTechnicalProwess()));

        btnLike.setOnClickListener(v -> {
            Intent n = new Intent();
            n.putExtra("like", "like");
            setResult(RESULT_OK, n);
            finish();
        });
        btnDislike.setOnClickListener(v -> {
            Intent n = new Intent();
            n.putExtra("like", "dislike");
            setResult(RESULT_OK, n);
            finish();
        });
    }
}