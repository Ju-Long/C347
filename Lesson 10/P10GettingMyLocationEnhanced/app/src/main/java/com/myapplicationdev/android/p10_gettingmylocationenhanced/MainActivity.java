package com.myapplicationdev.android.p10_gettingmylocationenhanced;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;

public class MainActivity extends AppCompatActivity {

    GoogleMap map;
    Button music_toggle, start_detector, stop_detector, check_records;
    TextView latitude, longitude;
    FusedLocationProviderClient client;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences("P10-C347", Context.MODE_PRIVATE);
        if (preferences.getBoolean("play_music", true)) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("play_music", false);
            editor.apply();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) fragmentManager.findFragmentById(R.id.map);
        client = new FusedLocationProviderClient(this);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                map = googleMap;

                if (checkPermission())
                    map.setMyLocationEnabled(true);

                UiSettings uiSettings = map.getUiSettings();
                uiSettings.setZoomControlsEnabled(true);
                uiSettings.setMyLocationButtonEnabled(true);
            }
        });

        music_toggle = findViewById(R.id.music_toggle_btn);
        start_detector = findViewById(R.id.start_detector_btn);
        stop_detector = findViewById(R.id.stop_detector_btn);
        check_records = findViewById(R.id.check_record_btn);
        latitude = findViewById(R.id.latitude_textview);
        longitude = findViewById(R.id.longitude_textview);

        music_toggle.setOnClickListener(v -> {
            if (!preferences.getBoolean("play_music", false)) {
                startService(new Intent(MainActivity.this, MusicService.class));
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("play_music", true);
            } else {
                stopService(new Intent(MainActivity.this, MusicService.class));
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("play_music", false);
            }
        });

        start_detector.setOnClickListener(v -> {

        });

        stop_detector.setOnClickListener(v -> {

        });

        check_records.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RecordActivity.class);
            startActivity(intent);
        });
    }

    private boolean checkPermission() {
        int permissionCheck_Coarse = ContextCompat.checkSelfPermission(
                MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION);
        int permissionCheck_Fine = ContextCompat.checkSelfPermission(
                MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);
        return permissionCheck_Coarse == PermissionChecker.PERMISSION_GRANTED || permissionCheck_Fine == PermissionChecker.PERMISSION_GRANTED;
    }
}