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

public class MainActivity extends AppCompatActivity {

    RadioButton rgStar;
    RadioGroup rgStars;
    EditText etNote;
    Button btnInsertNote, btnShowList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rgStars = (RadioGroup) findViewById(R.id.radioGroupStars);
        etNote = (EditText) findViewById(R.id.editTextNote);
        btnInsertNote = (Button) findViewById(R.id.buttonInsertNote);
        btnShowList = (Button) findViewById(R.id.buttonShowList);

        btnInsertNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rgStar = (RadioButton) findViewById(rgStars.getCheckedRadioButtonId());
                DBHelper db = new DBHelper(MainActivity.this);
                if (Integer.valueOf(rgStar.getText().toString()) > 0 && !etNote.getText().toString().isEmpty()) {

                    db.insertNote(etNote.getText().toString(), Integer.valueOf(rgStar.getText().toString()));
                    Log.d("Insert Database", "added into database");
                } else
                    Log.d("Insert Database", "Failed to insert, no value entries");
            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}
