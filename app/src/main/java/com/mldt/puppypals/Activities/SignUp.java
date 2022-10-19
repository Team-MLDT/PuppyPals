package com.mldt.puppypals.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.User;
import com.mldt.puppypals.R;

public class SignUp extends AppCompatActivity {

    public static final String TAG = "SignUpActivity";
    public static final String SIGNUP_EMAIL_TAG = "Signup_Email_Tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setUpSignUpForm();
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

            User newUser = User.builder()
                    .username(userEmail)
                    .userEmail(userEmail)
                    .build();

            Amplify.API.mutate(
                    ModelMutation.create(newUser),
                    successResponse -> Log.i(TAG, "User added!"),
                    failureResponse -> Log.i(TAG, "Failed to add user with this response: " + failureResponse)
            );
        });
    }
}