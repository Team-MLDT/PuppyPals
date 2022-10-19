package com.mldt.puppypals.Activities;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.DebugDog;

import com.amplifyframework.datastore.generated.model.Event;
import com.amplifyframework.datastore.generated.model.User;
import com.mldt.puppypals.R;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{
    public static final String Tag = "MainActivity";

    public AuthUser currentUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Amplify.Auth.fetchAuthSession(
                result -> Log.i("AmplifyQuickstart", result.toString()),
                error -> Log.e("AmplifyQuickstart", error.toString())
        );
        currentUser = Amplify.Auth.getCurrentUser();

//        setUpAddDogButton();

        DebugDog testDog = DebugDog.builder()
                .dogName("new dog 6")
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
//
        User testUser1 = User.builder()
                        .username("mandy1").userEmail("mnmason86@gmail.com").build();

        Amplify.API.mutate(
                ModelMutation.create(testUser1),
                successResponse -> Log.i(Tag, "User added!"),
                failureResponse -> Log.i(Tag, "Failed with this response: " + failureResponse)
        );
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

//  for testing add dog functionality
//    public void setUpAddDogButton() {
//        Button addDogButton = findViewById(R.id.testAddDogButton);
//        addDogButton.setOnClickListener(view -> {
//            Intent goToAddDog = new Intent(MainActivity.this, AddDog.class);
//            startActivity(goToAddDog);
//        });
//    }
}