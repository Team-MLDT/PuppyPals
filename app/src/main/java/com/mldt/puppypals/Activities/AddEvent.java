package com.mldt.puppypals.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Event;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.OnTokenCanceledListener;
import com.mldt.puppypals.R;

public class AddEvent extends AppCompatActivity {
    public static final String TAG = "Location";
    FusedLocationProviderClient fusedLocationClient = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        setUpLocation();

            Event newEvent = Event.builder()
                    .eventDescription("Description")
                    .lat("Latitude")
                    .lon("Longitude")
                    .eventDate("Date")
                    .eventTime("Time")
                    .eventImageUrl("ImageUrl")
                    .build();

            Amplify.API.mutate(
                    ModelMutation.create(newEvent),
                    success -> Log.i(TAG,"Add Event made Successfully"),
                    failure -> Log.i(TAG, "failed to add event")
            );
    }

    private void setUpLocation(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            Log.e(TAG, "No permissions for fine or coarse location");
            return;
        }
        // Setup location Client & request permissions
        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1); // hardcoded 1; can be anything between 1 - 65535
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getApplicationContext());
        fusedLocationClient.flushLocations();
    }
}