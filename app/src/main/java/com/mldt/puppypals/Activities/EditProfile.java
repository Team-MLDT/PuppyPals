package com.mldt.puppypals.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;

import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.core.Amplify;
import com.mldt.puppypals.R;

public class EditProfile extends AppCompatActivity {
    public static final String USER_NAME_TAG = "";
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        setUpAddDogButton();
        setUpUpdateButton();
    }

    public void setUpAddDogButton() {
        Button addDogButton = findViewById(R.id.editProfileActivityAddPetButton);
        addDogButton.setOnClickListener(view -> {
            Intent goToAddDogActivity = new Intent(EditProfile.this, AddDog.class);
            startActivity(goToAddDogActivity);
        });
    }

    public void setUpUpdateButton(){
        Button updateButton = findViewById(R.id.editProfileActivityUpdateButton);
        updateButton.setOnClickListener(view -> {

            SharedPreferences.Editor preferenceEditor = preferences.edit();
            String userName =  ((EditText)findViewById(R.id.editProfileActivityUsernameInput)).getText().toString();
            preferenceEditor.putString(USER_NAME_TAG, userName);
            preferenceEditor.apply();

            Intent goToProfileSettingsActivity = new Intent(EditProfile.this, OwnProfileSettings.class);
            goToProfileSettingsActivity.putExtra(USER_NAME_TAG, userName);
            startActivity(goToProfileSettingsActivity);
        });
    }
}