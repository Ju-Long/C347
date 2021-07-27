package com.myapplicationdev.android.p10_gettingmylocation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class RecordActivity extends AppCompatActivity {

    Button refresh, favourite;
    TextView num_of_record;
    ListView lv;
    ArrayList<String> records;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        refresh = findViewById(R.id.refresh_btn);
        favourite = findViewById(R.id.favourite_btn);
        num_of_record = findViewById(R.id.num_of_record_textview);
        lv = findViewById(R.id.list_of_records);
        records = new ArrayList<>();
        adapter = new ArrayAdapter<>(RecordActivity.this, android.R.layout.simple_list_item_1, records);
        lv.setAdapter(adapter);

        File folder = new File(getFilesDir().getAbsolutePath() + "/Folder");
        if (!folder.exists()) {
            if (folder.mkdir())
                Log.d("Create folder: ", "folder created successfully");
            else
                Log.d("Create folder: ", "folder created successfully");
        } else
            Log.d("Create folder: ", "folder already exist");

        try {
            File file = new File(folder, "location.txt");
            if (!file.exists()) {
                if (file.createNewFile())
                    Log.d("Create new file: ", "created new file");
                else
                    Log.d("Create new file: ", "failed to create new file");
            } else
                Log.d("Create new file: ", "file exist");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                records.add(scanner.nextLine());
            }
            scanner.close();
            adapter.notifyDataSetChanged();
            num_of_record.setText("Number of record: " + records.size());
        } catch (Exception e) { e.printStackTrace(); }

        refresh.setOnClickListener(v -> {
            records.clear();
            try {
                File file = new File(folder, "location.txt");
                if (!file.exists()) {
                    if (file.createNewFile())
                        Log.d("Create new file: ", "created new file");
                    else
                        Log.d("Create new file: ", "failed to create new file");
                } else
                    Log.d("Create new file: ", "file exist");
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    records.add(scanner.nextLine());
                }
                scanner.close();
                adapter.notifyDataSetChanged();
                num_of_record.setText("Number of record: " + records.size());
            } catch (Exception e) { e.printStackTrace(); }
        });

        favourite.setOnClickListener(v -> {
            records.clear();
            try {
                File file = new File(folder, "favourite.txt");
                if (!file.exists()) {
                    if (file.createNewFile())
                        Log.d("Create new file: ", "created new file");
                    else
                        Log.d("Create new file: ", "failed to create new file");
                } else
                    Log.d("Create new file: ", "file exist");
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    records.add(scanner.nextLine());
                }
                scanner.close();
                adapter.notifyDataSetChanged();
                num_of_record.setText("Number of record: " + records.size());
            } catch (Exception e) { e.printStackTrace(); }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                builder.setMessage("Add this location in your favorite list?");
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            File file = new File(folder, "favourite.txt");
                            if (!file.exists()) {
                                if (file.createNewFile())
                                    Log.d("Create new favourite: ", "created new file");
                                else
                                    Log.d("Create new favourite: ", "failed to create new file");
                            } else
                                Log.d("Create new favourite: ", "file exist");
                            FileWriter writer = new FileWriter(file, true);
                            writer.write(records.get(position) + " /n");
                            writer.flush();
                            writer.close();
                        } catch (Exception e) { e.printStackTrace(); }
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}