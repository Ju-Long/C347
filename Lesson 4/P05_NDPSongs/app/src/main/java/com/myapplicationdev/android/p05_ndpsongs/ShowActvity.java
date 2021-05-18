package com.myapplicationdev.android.p05_ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class ShowActvity extends AppCompatActivity {

    Button btnFilter;
    ListView lvSongs;
    ArrayList<Song> al, filteral;
    ArrayList<Integer> spinneral;
    SongArrayAdapter aa;
    ArrayAdapter<Integer> spinneraa;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        btnFilter = (Button) findViewById(R.id.btnFilter);
        lvSongs = (ListView) findViewById(R.id.lvSongs);
        spinner = (Spinner) findViewById(R.id.spinner);

        DBHelper dbh = new DBHelper(ShowActvity.this);
        al = dbh.getAllSongs();
        filteral = al;
        aa = new SongArrayAdapter(this, R.layout.row, al);
        lvSongs.setAdapter(aa);

        spinneral = new ArrayList<>();
        for ( Song i: al) {
            spinneral.add(i.getYear());
        }
        spinneraa = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinneral);
        spinneraa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinneraa);

        btnFilter.setOnClickListener(v -> {
            al = dbh.getAllSongs();
            filteral.clear();
            for (Song i: al) {
                if (i.getStars() == 5)
                    filteral.add(i);
            }
            aa = new SongArrayAdapter(this, R.layout.row, filteral);
            lvSongs.setAdapter(aa);
        });

        lvSongs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ShowActvity.this, ModifyActivity.class);
                i.putExtra("song", filteral.get(position));
                startActivity(i);
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                al = dbh.getAllSongs();
                int selected = spinneral.get(position);
                filteral.clear();
                for (Song i: al) {
                    if (i.getYear() == selected)
                        filteral.add(i);
                }
                aa = new SongArrayAdapter(ShowActvity.this, R.layout.row, filteral);
                lvSongs.setAdapter(aa);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        lvSongs = (ListView) findViewById(R.id.lvSongs);
        spinner = (Spinner) findViewById(R.id.spinner);
        DBHelper dbh = new DBHelper(ShowActvity.this);
        al = dbh.getAllSongs();
        aa = new SongArrayAdapter(this, R.layout.row, al);
        lvSongs.setAdapter(aa);

        spinneral = new ArrayList<>();
        for ( Song i: al) {
            spinneral.add(i.getYear());
        }
        spinneraa = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinneral);
        spinneraa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinneraa);
    }
}