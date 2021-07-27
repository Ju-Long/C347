package com.myapplicationdev.android.p09gettingmylocation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button check_record, get_location_update, remove_location_update;
    GoogleMap map;
    TextView latitude, longitude;
    FusedLocationProviderClient locationProviderClient;
    String folderLocation;
    LocationRequest locationRequest;
    LocationCallback locationCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        check_record = findViewById(R.id.check_record_btn);
        get_location_update = findViewById(R.id.get_location_update_btn);
        remove_location_update = findViewById(R.id.remove_location_update_btn);
        latitude = findViewById(R.id.latitude_tv);
        longitude = findViewById(R.id.longitude_tv);
        FragmentManager manager = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) manager.findFragmentById(R.id.map);
        locationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                map = googleMap;

                UiSettings ui = map.getUiSettings();
                ui.setCompassEnabled(true);
                ui.setZoomControlsEnabled(true);

                checkPermission();
            }
        });

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

        get_location_update.setOnClickListener(v -> {
            if (checkPermission()) {
                locationRequest = LocationRequest.create();
                locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                locationRequest.setInterval(30000);
                locationRequest.setSmallestDisplacement(500);

                locationCallback = new LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                        Location location = locationResult.getLastLocation();
                        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                        latitude.setText("Latitude: " + location.getLatitude());
                        longitude.setText("Longitude: " + location.getLongitude());

                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                        map.addMarker(new MarkerOptions()
                                .position(latLng)
                                .title("Last Known Location")
                                .snippet("Latitude: " + location.getLatitude() + ", Longitude: " + location.getLongitude())
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));



                        try {
                            File fileLocation = new File(folderLocation, "data.txt");
                            if (fileLocation.createNewFile())
                                Log.i("Create file", "create a file");
                            else
                                Log.i("Create file", "failed to create a file");

                            FileWriter writer = new FileWriter(fileLocation, true);
                            writer.write("Latitude: " + location.getLatitude() + ", Longitude: " + location.getLongitude() + "\n");
                            writer.close();
                        } catch (IOException e) { e.printStackTrace(); }
                    }
                };
            } else
                Log.i("Check Permission", "permission denial");
        });

        remove_location_update.setOnClickListener(v -> {
            locationProviderClient.removeLocationUpdates(locationCallback);
        });

        check_record.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, List_of_Location_Activity.class);
            startActivity(intent);
        });
    }

    private boolean checkPermission() {
        int permissionCheck_Coarse = ContextCompat.checkSelfPermission(
                MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION);
        int permissionCheck_Fine = ContextCompat.checkSelfPermission(
                MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionCheck_Coarse == PermissionChecker.PERMISSION_GRANTED || permissionCheck_Fine == PermissionChecker.PERMISSION_GRANTED)
            return true;
        else
            return false;
    }
}