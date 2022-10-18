package com.mldt.puppypals.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.core.Amplify;
import com.mldt.puppypals.R;

public class Verify extends AppCompatActivity {
    public static final String TAG = "VerifyActivity";
    public static final String VERIFY_ACCOUNT_EMAIL_TAG = "Verify_Account_Email_Tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);

        setUpVerificationForm();
    }

    public void setUpVerificationForm(){
        Intent callingIntent = getIntent();
        String userEmail = callingIntent.getStringExtra(SignUp.SIGNUP_EMAIL_TAG);
        findViewById(R.id.verifyActivityVerifyButton).setOnClickListener(view -> {
            String verificationCode = ((EditText) findViewById(R.id.verifyActivityCodeInput)).getText().toString();

            Amplify.Auth.confirmSignUp(
                    userEmail,
                    verificationCode,
                    success -> {
                        Log.i(TAG, "Verification succeeded: " + success);
                        Intent goToLoginPage = new Intent(Verify.this, LogIn.class);
                        goToLoginPage.putExtra(VERIFY_ACCOUNT_EMAIL_TAG, userEmail);
                        startActivity(goToLoginPage);
                    },
                    failure -> {
                        Log.i(TAG, "Verification failed with username: " + userEmail + " with this message: " + failure);
                        runOnUiThread(() -> {
                            Toast.makeText(Verify.this, "Verify account failed", Toast.LENGTH_SHORT).show();
                        });
                    }
            );
        });
    }
}