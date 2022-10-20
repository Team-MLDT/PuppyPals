package com.mldt.puppypals.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
<<<<<<< Updated upstream
=======
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
>>>>>>> Stashed changes
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.core.Amplify;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.OnTokenCanceledListener;
import com.mldt.puppypals.R;

public class LogIn extends AppCompatActivity {
    // Create the location client
    FusedLocationProviderClient fusedLocationClient;
    public static final String LOCATION_TAG = "Location";
    public static final String TAG = "LogInActivity";
    //set shared preferences for user email when user log in

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        setUpLogInForm();

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
                Log.e(LOCATION_TAG, "Location callback was null");

            }
            Log.i(LOCATION_TAG, "Latitude: " + location.getLatitude());
            Log.i(LOCATION_TAG, "Longitude: " + location.getLongitude());
        });
    }

    public void setUpLogInForm(){
        Intent callingIntent = getIntent();
        String userEmail = callingIntent.getStringExtra(Verify.VERIFY_ACCOUNT_EMAIL_TAG);
        EditText emailET = findViewById(R.id.loginActivityEmailInput);
        emailET.setText(userEmail);
        findViewById(R.id.loginActivityLoginButton).setOnClickListener(view -> {
            String userSelectedEmail = emailET.getText().toString();
            String userPassword = ((EditText) findViewById(R.id.loginActivityPasswordInput)).getText().toString();

            Amplify.Auth.signIn(
                    userSelectedEmail,
                    userPassword,
                    success -> {
                        Log.i(TAG, "Login succeeded " + success);
                        Intent goToMainActivity = new Intent(LogIn.this, MainActivity.class);
                        startActivity(goToMainActivity);
                    },
                    failure -> {
                        Log.i(TAG, "Login failed: " + failure);
                        runOnUiThread(() -> {
                            Toast.makeText(LogIn.this, "Login failed", Toast.LENGTH_SHORT).show();
                        });
                    }
            );
        });
    }
}