package com.mldt.puppypals.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Event;
import com.amplifyframework.datastore.generated.model.User;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.OnTokenCanceledListener;
import com.mldt.puppypals.R;

import java.util.concurrent.CompletableFuture;

public class AddEvent extends AppCompatActivity {

    public static final String Tag = "Location";
    public static final String ActTag = "AddEvent";

    public AuthUser currentAuthUser = null;
    CompletableFuture<User> userFuture = null;
    public User currentUser = null;
    SharedPreferences preferences;


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
        currentAuthUser = Amplify.Auth.getCurrentUser();
        userFuture = new CompletableFuture<>();

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        if (Amplify.Auth.getCurrentUser() != null) {
            Amplify.Auth.fetchAuthSession(
                    result -> Log.i("AmplifyQuickstart", result.toString()),
                    error -> Log.e("AmplifyQuickstart", error.toString())
            );
            currentAuthUser = Amplify.Auth.getCurrentUser();
            String currentAuthEmail = currentAuthUser.getUsername();

            Amplify.API.query(
                    ModelQuery.list(User.class),
                    successResponse -> {
                        System.out.println(successResponse.getData());
                        for (User dbUser : successResponse.getData()) {
                            if (dbUser.getUserEmail().equals(currentAuthEmail)) {
                                currentUser = dbUser;
                                System.out.println("Current user: " + currentUser);
                            }
                        }
                        userFuture.complete(currentUser);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(AddEvent.this, "Found user", Toast.LENGTH_SHORT).show();
                            }
                        });
                    },
                    failureResponse -> {
                        userFuture.complete(null);
                        Log.i(Tag, "Did not read Users successfully");
                    }
            );

//        setUpLocation();
            setUpSubmitButton();
        }
    }




//    private void setUpLocation(){
//        // Setup location Client & request permissions
//        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1); // hardcoded 1; can be anything between 1 - 65535
//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getApplicationContext());
//        fusedLocationClient.flushLocations();
//
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        fusedLocationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, new CancellationToken() {
//            @NonNull
//            @Override
//            public CancellationToken onCanceledRequested(@NonNull OnTokenCanceledListener onTokenCanceledListener) {
//                return null;
//            }
//
//            @Override
//            public boolean isCancellationRequested() {
//                return false;
//            }
//        }).addOnSuccessListener(location -> {
//            if(location == null){
//                Log.e(Tag, "Location callback was null");
//
//            }
//            Log.i(Tag, "Latitude: " + location.getLatitude());
//            Log.i(Tag, "Longitude: " + location.getLongitude());
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


        Event newEvent = Event.builder()
                .eventDescription(eventTitle)
                .lat(eventLat)
                .lon(eventLon)
                .eventDate(eventDate)
                .eventTime(eventTime)
                .host(currentUser)
                .build();

        Amplify.API.mutate(
                ModelMutation.create(newEvent),
                success -> Log.i(Tag,"Add Event made Successfully"),
                failure -> Log.i(Tag, "failed to add event")
        );
        Intent goToMainActivity = new Intent(AddEvent.this, MainActivity.class);
        startActivity(goToMainActivity);
        });
    }
}