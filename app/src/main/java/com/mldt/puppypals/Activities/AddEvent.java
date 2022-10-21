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
import android.widget.Button;
import android.widget.EditText;

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
    // Create the location client
    FusedLocationProviderClient fusedLocationClient;
    String eventTitle = "";
    String eventDate = "";
    String eventTime = "";
    String eventLat = "";
    String eventLon = "";
    String eventCity = "";
    String eventState = "";
    String eventZip = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        setUpSubmitButton();
//        setUpLocation();

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

//    private void setUpLocation(){
//        // Setup location Client & request permissions
//        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1); // hardcoded 1; can be anything between 1 - 65535
//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getApplicationContext());
//        fusedLocationClient.flushLocations();
//
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
//
//            return;
//        }
//        fusedLocationClient.getCurrentLocation().addOnSuccessListener(location -> {
//            if(location == null){
//                Log.e(TAG, "Location callback was null");
//
//            }
//            Log.i(TAG, "Latitude: " + location.getLatitude());
//            Log.i(TAG, "Longitude: " + location.getLongitude());
//        });
//    }


    public void setUpSubmitButton() {

        Button addNewEventButton = findViewById(R.id.addEventActivitySaveButton);
        addNewEventButton.setOnClickListener(view -> {
            eventTitle = ((EditText) findViewById(R.id.addEventActivityTitleInput)).getText().toString();
            eventDate = ((EditText) findViewById(R.id.addEventActivityDateInput)).getText().toString();
            eventTime = ((EditText) findViewById(R.id.addEventActivityTimeInput)).getText().toString();
            eventLat = ((EditText) findViewById(R.id.addEventActivityLatitudeInput)).getText().toString();
            eventLon = ((EditText) findViewById(R.id.addEventActivityLongitudeInput)).getText().toString();
            eventCity = ((EditText) findViewById(R.id.addEventActivityCityInput)).getText().toString();
            eventState = ((EditText) findViewById(R.id.addEventActivityStateInput)).getText().toString();
            eventZip = ((EditText) findViewById(R.id.addEventActivityZipInput)).getText().toString();

            // class as API representation of location (only properties are lat & lon)

        });

        Event newEvent = Event.builder()
                .eventDescription(eventTitle)
                .lat(eventLat)
                .lon(eventLon)
                .eventDate(eventDate)
                .eventTime(eventTime)
                .build();

        Amplify.API.mutate(
                ModelMutation.create(newEvent),
                success -> Log.i(TAG,"Add Event made Successfully"),
                failure -> Log.i(TAG, "failed to add event")
        );
        Intent goToMainActivity = new Intent(AddEvent.this, MainActivity.class);


    }

}