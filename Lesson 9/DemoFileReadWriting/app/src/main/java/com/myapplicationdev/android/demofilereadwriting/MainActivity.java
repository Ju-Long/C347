package com.myapplicationdev.android.demofilereadwriting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    String folderLocation;
    Button btnWrite, btnRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnWrite = findViewById(R.id.btnWrite);
        btnRead = findViewById(R.id.btnRead);
        folderLocation = getFilesDir().getAbsolutePath() + "/MyFolder";

        File folder = new File(folderLocation);
        if (!folder.exists()) {
            boolean result = folder.mkdir();
            if (result)
                Log.d("File Read/Write", "Folder Created");
            else
                Log.i("Folder Create", "Folder creation failed");
        } else
            Log.i("Folder Create", "Folder already exist");

        btnWrite.setOnClickListener(v -> {
            try {
                File file = new File(folderLocation, "/data.txt");
                if (file.createNewFile())
                    Log.i("Create File", "File Created");
                else
                    Log.i("Create File", "File already exist");

                FileWriter writer = new FileWriter(file, true);
                writer.write("written");
                writer.close();
                Log.i("File Write", "write finish");
            } catch (IOException e) { e.printStackTrace(); }
        });

        btnRead.setOnClickListener(v -> {
            try {
                File file = new File(folderLocation, "/data.txt");
                Scanner scanner = new Scanner(file);
                String data = "";
                while (scanner.hasNextLine()) {
                    data += scanner.nextLine() + "\n";
                }
                Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
            } catch (IOException e) { e.printStackTrace(); }
        });
    }
}