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
import android.widget.Button;
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
    
    public static final String TAG = "LogInActivity";
    public static final String USER_EMAIL_TAG= "";
    SharedPreferences preferences;

    //set shared preferences for user email when user log in

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        setUpHomeButton();
        setUpLogInForm();
    }

    public void setUpLogInForm(){
        Intent callingIntent = getIntent();
        String userEmail = callingIntent.getStringExtra(Verify.VERIFY_ACCOUNT_EMAIL_TAG);
        EditText emailET = findViewById(R.id.loginActivityEmailInput);
        emailET.setText(userEmail);
        SharedPreferences.Editor preferenceEditor =preferences.edit();
        preferenceEditor.putString(USER_EMAIL_TAG,userEmail);
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
    public void setUpHomeButton(){
        Button homeButton = findViewById(R.id.loginActivityHomeButton);
        homeButton.setOnClickListener(view -> {
            Intent goToMainActivity = new Intent(LogIn.this, MainActivity.class);
            startActivity(goToMainActivity);
        });
    }
}