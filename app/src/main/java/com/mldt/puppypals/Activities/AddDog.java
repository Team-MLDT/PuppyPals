package com.mldt.puppypals.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Dog;
import com.amplifyframework.datastore.generated.model.Event;
import com.amplifyframework.datastore.generated.model.User;
import com.mldt.puppypals.R;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class AddDog extends AppCompatActivity {
    public static final String Tag = "AddDog";
    public AuthUser currentAuthUser = null;
    public User currentUser = null;
    CompletableFuture<User> userFuture = null;

    SharedPreferences preferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dog);
        currentAuthUser = Amplify.Auth.getCurrentUser();
        userFuture = new CompletableFuture<>();

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        if(Amplify.Auth.getCurrentUser() != null) {
            Amplify.Auth.fetchAuthSession(
                    result -> Log.i("AmplifyQuickstart", result.toString()),
                    error -> Log.e("AmplifyQuickstart", error.toString())
            );
            currentAuthUser = Amplify.Auth.getCurrentUser();
            String currentAuthEmail = currentAuthUser.getUsername();

            Amplify.API.query(
                    ModelQuery.list(User.class),
                    successResponse -> {
                        System.out.println(successResponse.getData());
                        for (User dbUser : successResponse.getData()) {
                            if(dbUser.getUserEmail().equals(currentAuthEmail)) {
                                currentUser = dbUser;
                                System.out.println(currentUser);
                            }
                        }
                        userFuture.complete(currentUser);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(AddDog.this, "Found user", Toast.LENGTH_SHORT).show();
                            }
                        });
                    },
                    failureResponse -> {
                        userFuture.complete(null);
                        Log.i(Tag, "Did not read Users successfully");
                    }
            );
        }

        setUpSubmitButton();
        Button selectPhoto = findViewById(R.id.addDogActivityProfilePicBtn);
        selectPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 3);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && data != null){
            Uri selectedImage = data.getData();
            ImageView imageView = findViewById(R.id.editProfileActivityUserImageIV2);
            imageView.setImageURI(selectedImage);
        }
    }
    //TODO: make this work
//    private void setUpSaveButton(){
//
//    }

    private void setUpSubmitButton(){
        Button saveNewTaskButton = findViewById(R.id.addDogActivitySaveBtn);
        saveNewTaskButton.setOnClickListener(view -> {

            String dogName = ((EditText) findViewById(R.id.addDogActivityDogNameET)).getText().toString();
            String dogBreed = ((EditText) findViewById(R.id.addDogActivityDogBreedET)).getText().toString();
            String dogBio = ((EditText) findViewById(R.id.addDogActivityDogBioETM)).getText().toString();
            User dogOwner = currentUser;

            Dog newDog = Dog.builder()
                    .dogName(dogName)
                    .dogBreed(dogBreed)
                    .dogBio(dogBio)
                    .owner(dogOwner)
                    .build();

            Amplify.API.mutate(
                    ModelMutation.create(newDog),
                    successResponse -> Log.i(Tag, "Dog added!"),
                    failureResponse -> Log.i(Tag, "AddTaskActivity: failed with this response: " + failureResponse)
            );

            Intent goToOwnProfileSettings = new Intent(AddDog.this, OwnProfileSettings.class);
            startActivity(goToOwnProfileSettings);
        });
    }

}