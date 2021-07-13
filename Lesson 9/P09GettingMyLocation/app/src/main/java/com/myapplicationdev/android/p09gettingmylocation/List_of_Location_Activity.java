package com.myapplicationdev.android.p09gettingmylocation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class List_of_Location_Activity extends AppCompatActivity {

    TextView num_of_record;
    ListView list_of_record;
    Button refresh;
    String folderLocation;
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_location);

        arrayList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        num_of_record = findViewById(R.id.num_of_record_tv);
        list_of_record = findViewById(R.id.list_of_lat_long);
        refresh = findViewById(R.id.refresh_btn);
        list_of_record.setAdapter(adapter);
        folderLocation = getFilesDir().getAbsolutePath() + "/P09";
        File folder = new File(folderLocation);

        if (!folder.exists()) {
            boolean result = folder.mkdir();
            if (result)
                Log.d("File Read/Write", "Folder Created");
            else
                Log.i("Folder Create", "Folder creation failed");
        } else
            Log.i("Folder Create", "Folder already exist");

        try {
            File file = new File(folderLocation, "data.txt");
            if (file.createNewFile())
                Log.i("File create", "file created");
            else
                Log.i("File create", "file creation failed");

            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()) {
                arrayList.add(scanner.nextLine());
            }
            num_of_record.setText(arrayList.size());
            adapter.notifyDataSetChanged();
        } catch (IOException e) { e.printStackTrace(); }

        refresh.setOnClickListener(v -> {
            try {
                File file = new File(folderLocation, "data.txt");
                if (file.createNewFile())
                    Log.i("File create", "file created");
                else
                    Log.i("File create", "file creation failed");

                Scanner scanner = new Scanner(file);
                while(scanner.hasNextLine()) {
                    arrayList.add(scanner.nextLine());
                }
                num_of_record.setText(arrayList.size());
                adapter.notifyDataSetChanged();
            } catch (IOException e) { e.printStackTrace(); }
            Toast.makeText(this, "refreshed", Toast.LENGTH_SHORT).show();
        });
    }
}