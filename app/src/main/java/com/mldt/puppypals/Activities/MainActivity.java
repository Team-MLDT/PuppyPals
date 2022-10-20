package com.mldt.puppypals.Activities;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.DebugDog;
import com.amplifyframework.datastore.generated.model.Dog;
import com.amplifyframework.datastore.generated.model.Event;
import com.mldt.puppypals.R;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{
    public static final String Tag = "MainActivity";

    public AuthUser currentUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

<<<<<<< Updated upstream
        Amplify.Auth.fetchAuthSession(
                result -> Log.i("AmplifyQuickstart", result.toString()),
                error -> Log.e("AmplifyQuickstart", error.toString())
        );
        currentUser = Amplify.Auth.getCurrentUser();

//        setUpAddDogButton();

        DebugDog testDog = DebugDog.builder()
                .dogName("new dog")
                .build();

        Amplify.API.mutate(
                ModelMutation.create(testDog),
                successResponse -> Log.i(Tag, "DebugDog added!"),
                failureResponse -> Log.i(Tag, "DebugDog not added: " + failureResponse)
        );

//        Dog testDog = Dog.builder()
//                .dogName("dog")
//                .build();
//
//        Amplify.API.mutate(
//                ModelMutation.create(testDog),
//                successResponse -> Log.i(Tag, "Dog added!"),
//                failureResponse -> Log.i(Tag, "Dog not added" + failureResponse)
//        );

        Event testEvent = Event.builder()
                .eventDescription("Picnic at Golden Gardens Dog Park")
                .lat("47.690801")
                .lon("-122.400331")
                .eventDate("10/22/22")
                .eventTime("3:00 PM")
                .build();

        Amplify.API.mutate(
                ModelMutation.create(testEvent),
                successResponse -> Log.i(Tag, "Event added!"),
                failureResponse -> Log.i(Tag, "AddTaskActivity: failed with this response: " + failureResponse)
        );
=======
        if(Amplify.Auth.getCurrentUser() != null) {
            Amplify.Auth.fetchAuthSession(
                    result -> Log.i("AmplifyQuickstart", result.toString()),
                    error -> Log.e("AmplifyQuickstart", error.toString())
            );
            currentAuthUser = Amplify.Auth.getCurrentUser();
            currentAuthEmail = currentAuthUser.getUsername();

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

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "Found user", Toast.LENGTH_SHORT).show();
                        }
                    });
                    },
                    failureResponse -> Log.i(Tag, "Did not read Users successfully")
            );
        }


>>>>>>> Stashed changes
    }

    public void showPopup(View v){
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        if(currentUser == null) {
            inflater.inflate(R.menu.dropdown_logged_out, popup.getMenu());
        } else {
            inflater.inflate(R.menu.dropdown_logged_in, popup.getMenu());
        }
        popup.setOnMenuItemClickListener(this::onMenuItemClick);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mainActivityDropDownSignUp:
                goToSignUpActivity();
                return true;
            case R.id.mainActivityDropDownLogin:
                goToLoginActivity();
                return true;
            case R.id.mainActivityDropDownSettings:
                goToProfileActivity();
                return true;
            case R.id.mainActivityDropDownAbout:
                goToAboutActivity();
                return true;
            case R.id.mainActivityDropDownLogOut:
                Amplify.Auth.signOut(
                        () -> Log.i(TAG, "Signed out successfully"),
                        error -> Log.e(TAG, error.toString())
                );
                Intent goToLoginActivity = new Intent(MainActivity.this, LogIn.class);
                startActivity(goToLoginActivity);
                return true;
            default:
        }
        return false;
    }

    public void goToSignUpActivity(){
        Intent goToSignUp = new Intent(MainActivity.this, SignUp.class);
        startActivity(goToSignUp);
    }
    public void goToLoginActivity(){
        Intent goToLogIn = new Intent(MainActivity.this, LogIn.class);
        startActivity(goToLogIn);
    }
    public void goToAboutActivity(){
        Intent goToAbout = new Intent(MainActivity.this, AboutPage.class);
        startActivity(goToAbout);
    }
    public void goToProfileActivity(){
        Intent goToProfile = new Intent(MainActivity.this, OwnProfileSettings.class);
        startActivity(goToProfile);
    }

<<<<<<< Updated upstream
//  for testing add dog functionality
//    public void setUpAddDogButton() {
//        Button addDogButton = findViewById(R.id.testAddDogButton);
//        addDogButton.setOnClickListener(view -> {
//            Intent goToAddDog = new Intent(MainActivity.this, AddDog.class);
//            startActivity(goToAddDog);
//        });
//    }
=======

>>>>>>> Stashed changes
}