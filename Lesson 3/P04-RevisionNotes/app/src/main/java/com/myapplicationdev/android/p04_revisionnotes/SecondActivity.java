package com.myapplicationdev.android.p04_revisionnotes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

	ListView lv;
	ArrayList<Note> aa, filteredaa;
	RevisionNotesArrayAdapter al;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		//TODO implement the Custom ListView

		Intent intent = getIntent();
		boolean good = intent.getBooleanExtra("good", false);

		lv = (ListView) findViewById(R.id.lv);

		DBHelper db = new DBHelper(SecondActivity.this);
		aa = db.getAllNotes();
		filteredaa = new ArrayList<>();
		if (good) {
			for (Note i: aa) {
				if (i.getStars() > 3)
					filteredaa.add(i);
			}
			al = new RevisionNotesArrayAdapter(this, R.layout.row, filteredaa);
		} else
			al = new RevisionNotesArrayAdapter(this, R.layout.row, aa);
		lv.setAdapter(al);
	}
}
