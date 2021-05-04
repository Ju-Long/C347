package com.myapplicationdev.android.p04_revisionnotes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RadioButton rgStar;
    RadioGroup rgStars;
    EditText etNote;
    Button btnInsertNote, btnShowList, btnShowGood;
    ArrayList<String> aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rgStars = (RadioGroup) findViewById(R.id.radioGroupStars);
        etNote = (EditText) findViewById(R.id.editTextNote);
        btnInsertNote = (Button) findViewById(R.id.buttonInsertNote);
        btnShowList = (Button) findViewById(R.id.buttonShowList);
        btnShowGood = (Button) findViewById(R.id.buttonShowGood);


        final Intent intent = new Intent(MainActivity.this, SecondActivity.class);

        btnInsertNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rgStar = (RadioButton) findViewById(rgStars.getCheckedRadioButtonId());
                DBHelper db = new DBHelper(MainActivity.this);
                aa = db.getNoteContent();
                if (Integer.valueOf(rgStar.getText().toString()) > 0 && !etNote.getText().toString().isEmpty() && !aa.contains(etNote.getText().toString())) {

                    db.insertNote(etNote.getText().toString().trim(), Integer.valueOf(rgStar.getText().toString()));
                    Log.d("Insert Database", "added into database");
                } else
                    Log.d("Insert Database", "Failed to insert, no value entries");
            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("good", false);
                startActivity(intent);
            }
        });

        btnShowGood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("good", true);
                startActivity(intent);
            }
        });
    }
}
