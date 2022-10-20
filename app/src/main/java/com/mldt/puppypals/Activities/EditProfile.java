package com.mldt.puppypals.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.core.Amplify;
import com.mldt.puppypals.R;

public class EditProfile extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        setUpAddDogButton();
    }

    public void setUpAddDogButton() {
        Button addDogButton = findViewById(R.id.editProfileActivityAddPetButton);
        addDogButton.setOnClickListener(view -> {
            Intent goToAddDogActivity = new Intent(EditProfile.this, AddDog.class);
            startActivity(goToAddDogActivity);
        });
    }
}