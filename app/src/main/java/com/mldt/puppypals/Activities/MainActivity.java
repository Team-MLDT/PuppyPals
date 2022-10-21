package com.mldt.puppypals.Activities;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.core.Amplify;

import com.amplifyframework.datastore.generated.model.Event;
import com.amplifyframework.datastore.generated.model.User;
import com.mldt.puppypals.Adapters.UpcomingEventsRecyclerViewAdapter;
import com.mldt.puppypals.LocationRequest;
import com.mldt.puppypals.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;


public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    public static final String Tag = "MainActivity";
    public static final String USER_ID_TAG = "";

    public AuthUser currentAuthUser = null;
    public String currentAuthEmail = "";
    public User currentUser = null;
    CompletableFuture<User> userFuture = null;
    private List<Event> eventList = null;
    private UpcomingEventsRecyclerViewAdapter allEventsAdapter;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eventList = new ArrayList<>();

        currentAuthUser = Amplify.Auth.getCurrentUser();
        userFuture = new CompletableFuture<>();

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        getUser();
        getEventsFromDB();
        setUpEventsRecyclerView();

        try {
            LocationRequest.getQuery();
        } catch (IOException e) {
            e.printStackTrace();
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

    public void getUser(){
        SharedPreferences.Editor preferenceEditor = preferences.edit();
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
                                preferenceEditor.putString(USER_ID_TAG, currentUser.getId());
                                preferenceEditor.apply();
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
        try {
            LocationRequest.getQuery();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setAddEventButton();
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

    private void setAddEventButton() {
        findViewById(R.id.mainActivityTestAddEventButton).setOnClickListener(view -> {
            Intent goToAddEventActivity = new Intent(MainActivity.this, AddEvent.class);
            startActivity(goToAddEventActivity);
        });
    }

    private void getEventsFromDB(){
        Amplify.API.query(
                ModelQuery.list(Event.class),
                success -> {
                    Log.i(TAG, "Read Events successfully!");
                    eventList.clear();
                    for(Event dbEvent : success.getData()){
                            eventList.add(dbEvent);
                    }
                    runOnUiThread(() -> {
                        allEventsAdapter.notifyDataSetChanged();
                    });
                },
                failure -> Log.i(TAG, "Did not read Events successfully " + failure)
        );
    }
    private void setUpEventsRecyclerView(){
        RecyclerView eventsRecyclerView = findViewById(R.id.mainActivityEventsRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        eventsRecyclerView.setLayoutManager(layoutManager);
        allEventsAdapter = new UpcomingEventsRecyclerViewAdapter(eventList, this);
        eventsRecyclerView.setAdapter(allEventsAdapter);
    }
}