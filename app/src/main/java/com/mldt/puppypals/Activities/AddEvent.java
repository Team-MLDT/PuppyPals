package com.mldt.puppypals.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
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
    public static final String Tag = "Location";
    // Create the location client
    FusedLocationProviderClient fusedLocationClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        // Setup location Client & request permissions
        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1); // hardcoded 1; can be anything between 1 - 65535
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getApplicationContext());
        fusedLocationClient.flushLocations();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, new CancellationToken() {
            @NonNull
            @Override
            public CancellationToken onCanceledRequested(@NonNull OnTokenCanceledListener onTokenCanceledListener) {
                return null;
            }

            @Override
            public boolean isCancellationRequested() {
                return false;
            }
        }).addOnSuccessListener(location -> {
            if(location == null){
                Log.e(Tag, "Location callback was null");

            }
            Log.i(Tag, "Latitude: " + location.getLatitude());
            Log.i(Tag, "Longitude: " + location.getLongitude());

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
                    success -> Log.i(Tag,"Add Event made Successfully"),
                    failure -> Log.i(Tag, "failed to add event")
            );
                Intent goToMainActivity = new Intent(AddEvent.this, MainActivity.class);

        });
    }
}