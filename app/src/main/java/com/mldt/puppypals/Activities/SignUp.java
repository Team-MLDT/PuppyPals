package com.mldt.puppypals.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.User;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.OnTokenCanceledListener;
import com.mldt.puppypals.R;

public class SignUp extends AppCompatActivity {
    // Create the location client
    FusedLocationProviderClient fusedLocationClient;
    public static final String LOCATION_TAG = "Location";
    public static final String TAG = "SignUpActivity";
    public static final String SIGNUP_EMAIL_TAG = "Signup_Email_Tag";
    public static final String SIGNUP_ID_TAG = "Signup_Id_Tag";
    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        setUpSignUpForm();

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

    private void setUpSignUpForm(){

        findViewById(R.id.signupActivitySignupButton).setOnClickListener(view -> {
            String userEmail = ((EditText) findViewById(R.id.signupActivityEmailInput)).getText().toString();
            String userPassword = ((EditText) findViewById(R.id.signupActivityPasswordInput)).getText().toString();

            Amplify.Auth.signUp(userEmail,
                    userPassword,
                    AuthSignUpOptions.builder()
                            .userAttribute(AuthUserAttributeKey.email(), userEmail)
                            .build(),
                    success -> {
                        Log.i(TAG, "Signup successful! " + success);
                        Intent goToVerifyActivity = new Intent(SignUp.this, Verify.class);
                        goToVerifyActivity.putExtra(SIGNUP_EMAIL_TAG, userEmail);
                        startActivity(goToVerifyActivity);
                    },
                    failure -> {
                        Log.i(TAG, "Signup failed with email " + userEmail + " with message: " + failure);
                        runOnUiThread(() -> Toast.makeText(SignUp.this, "Signup Failed", Toast.LENGTH_SHORT).show());
                    }
            );

            SharedPreferences.Editor preferenceEditor =preferences.edit();

            User newUser = User.builder()
                    .username(userEmail)
                    .userEmail(userEmail)
                    .build();

            Amplify.API.mutate(
                    ModelMutation.create(newUser),
                    successResponse -> preferenceEditor.putString(SIGNUP_ID_TAG, successResponse.getData().getId()),
                    failureResponse -> Log.i(TAG, "Failed to add user with this response: " + failureResponse)
            );
        });
    }
}