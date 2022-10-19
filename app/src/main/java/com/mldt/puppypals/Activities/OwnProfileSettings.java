package com.mldt.puppypals.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.mldt.puppypals.R;

public class OwnProfileSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_own_profile_settings);

        setUpEditProfileButton();
    }

    public void setUpEditProfileButton() {
        Button editButton = findViewById(R.id.ownProfileActivityEditButton);
        editButton.setOnClickListener(view -> {
            Intent goToEditProfile = new Intent(OwnProfileSettings.this, EditProfile.class);
            startActivity(goToEditProfile);
        });
    }
}