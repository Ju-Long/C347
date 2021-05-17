package com.myapplicationdev.android.demodatabasecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnAdd, btnEdit, btnRetrieve;
    TextView tvDBContent;
    EditText etContent;
    ArrayList<Note> al;
    ListView lv;
    ArrayAdapter<Note> aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnRetrieve = (Button) findViewById(R.id.btnRetrieve);
        tvDBContent = (TextView) findViewById(R.id.tvDBContnet);
        etContent = (EditText) findViewById(R.id.etContent);
        lv = (ListView) findViewById(R.id.lv);
        al = new ArrayList<Note>();
        aa = new ArrayAdapter<Note>(this, android.R.layout.simple_expandable_list_item_1, al);
        lv.setAdapter(aa);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Note target = al.get(position);
                Intent i = new Intent(MainActivity.this, EditActivity.class);
                i.putExtra("data", target);
                startActivity(i);
            }
        });

        btnAdd.setOnClickListener(v -> {
            String data = etContent.getText().toString();
            DBHelper dbh = new DBHelper(MainActivity.this);
            long inserted_id = dbh.insertNote(data);
            dbh.close();

            if (inserted_id != -1)
                Toast.makeText(MainActivity.this, "Insert Successful", Toast.LENGTH_SHORT).show();
        });

        btnRetrieve.setOnClickListener(v -> {
            retrieve();
        });

        btnEdit.setOnClickListener(v -> {
            Note target = al.get(0);
            Intent i = new Intent(MainActivity.this, EditActivity.class);
            i.putExtra("data", target);
            startActivity(i);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        retrieve();
    }

    public void retrieve() {
        tvDBContent = findViewById(R.id.tvDBContnet);
        DBHelper dbh = new DBHelper(MainActivity.this);
        al = new ArrayList<>();
        al.addAll(dbh.getAllNotes());
        dbh.close();

        String txt = "";
        for (Note i : al) {
            txt += "ID: " + i.getId() + ", " + i.getNoteContent() + "\n";
        }
        tvDBContent.setText(txt);
    }
}