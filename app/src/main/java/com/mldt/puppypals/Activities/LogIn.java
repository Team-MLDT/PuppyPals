package com.mldt.puppypals.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.core.Amplify;
import com.mldt.puppypals.R;

public class LogIn extends AppCompatActivity {
    public static final String TAG = "LogInActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        setUpLogInForm();
    }

    public void setUpLogInForm(){
        Intent callingIntent = getIntent();
        String userEmail = callingIntent.getStringExtra(Verify.VERIFY_ACCOUNT_EMAIL_TAG);
        findViewById(R.id.loginActivityLoginButton).setOnClickListener(view -> {
            String userPassword = ((EditText) findViewById(R.id.loginActivityPasswordInput)).getText().toString();

            Amplify.Auth.signIn(
                    userEmail,
                    userPassword,
                    success -> {
                        Log.i(TAG, "Login succeeded " + success);
                        Intent goToLoggedInHomeActivity = new Intent(LogIn.this, LoggedINHome.class);
                        startActivity(goToLoggedInHomeActivity);
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