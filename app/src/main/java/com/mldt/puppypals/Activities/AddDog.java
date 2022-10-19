package com.mldt.puppypals.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Dog;
import com.amplifyframework.datastore.generated.model.User;
import com.mldt.puppypals.R;

public class AddDog extends AppCompatActivity {
    public static final String Tag = "AddDog";
    public AuthUser currentAuthUser = null;
    public User currentDBUser = null;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dog);
        currentAuthUser = Amplify.Auth.getCurrentUser();
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        String userEmail = currentAuthUser.getUserId();

        setUpSubmitButton();

    }

    private void setUpSubmitButton(){
        Button saveNewTaskButton = findViewById(R.id.addDogActivitySaveBtn);
        saveNewTaskButton.setOnClickListener(view -> {

            String dogName = ((EditText) findViewById(R.id.addDogActivityDogNameET)).getText().toString();
            String dogBreed = ((EditText) findViewById(R.id.addDogActivityDogBreedET)).getText().toString();
            String dogBio = ((EditText) findViewById(R.id.addDogActivityDogBioETM)).getText().toString();
//            User dogOwner =

            Dog newDog = Dog.builder()
                    .dogName(dogName)
                    .dogBreed(dogBreed)
                    .dogBio(dogBio)
//                    .owner(dogOwner)
                    .build();

            Amplify.API.mutate(
                    ModelMutation.create(newDog),
                    successResponse -> Log.i(Tag, "Dog added!"),
                    failureResponse -> Log.i(Tag, "AddTaskActivity: failed with this response: " + failureResponse)
            );

            Intent goToMainActivity = new Intent(AddDog.this, MainActivity.class);
            startActivity(goToMainActivity);
        });
    }

}