package com.mldt.puppypals.Activities;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.PopupMenu;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.core.Amplify;

import com.amplifyframework.datastore.generated.model.User;
import com.mldt.puppypals.R;

import java.util.concurrent.CompletableFuture;


public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    public static final String Tag = "MainActivity";
    public static final String USER_ID_TAG = "";

    public AuthUser currentAuthUser = null;
    public String currentAuthEmail = "";
    public User currentUser = null;
    CompletableFuture<User> userFuture = null;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        currentAuthUser = Amplify.Auth.getCurrentUser();
        userFuture = new CompletableFuture<>();

        preferences = PreferenceManager.getDefaultSharedPreferences(this);


        if (Amplify.Auth.getCurrentUser() != null) {
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
                            if (dbUser.getUserEmail().equals(currentAuthEmail)) {
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


    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        if (currentAuthUser == null) {
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

    public void goToSignUpActivity() {
        Intent goToSignUp = new Intent(MainActivity.this, SignUp.class);
        startActivity(goToSignUp);
    }

    public void goToLoginActivity() {
        Intent goToLogIn = new Intent(MainActivity.this, LogIn.class);
        startActivity(goToLogIn);
    }

    public void goToAboutActivity() {
        Intent goToAbout = new Intent(MainActivity.this, AboutPage.class);
        startActivity(goToAbout);
    }

    public void goToProfileActivity() {
        Intent goToProfile = new Intent(MainActivity.this, OwnProfileSettings.class);
        startActivity(goToProfile);
    }
}