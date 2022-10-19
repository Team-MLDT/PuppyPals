package com.mldt.puppypals.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.widget.Button;


import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Dog;
import com.amplifyframework.datastore.generated.model.Event;
import com.amplifyframework.datastore.generated.model.User;
import com.mldt.puppypals.Adapters.MyPetsRecyclerViewAdapter;
import com.mldt.puppypals.Adapters.UpcomingEventsRecyclerViewAdapter;
import com.mldt.puppypals.R;

import java.util.ArrayList;
import java.util.List;

public class OwnProfileSettings extends AppCompatActivity {
    public static final String TAG = "OwnProfileSettingsActivity";

    List<Event> eventList = null;
    List<Dog> dogList = null;
    UpcomingEventsRecyclerViewAdapter eventAdapter;
    MyPetsRecyclerViewAdapter petAdapter;
    User currentUser;
    //currentUser currently not assigned - should be set to the current session user's email address
    //Set in shared preferences

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_own_profile_settings);

        eventList = new ArrayList<>();
        dogList = new ArrayList<>();

        setUpEditProfileButton();
        getEventsFromDB();
        setUpEventsRecyclerView();
        getDogsFromDB();
        setUpPetsRecyclerView();
    }

    private void getEventsFromDB(){
        Amplify.API.query(
                ModelQuery.list(Event.class),
                success -> {
                    Log.i(TAG, "Read Events successfully!");
                    eventList.clear();
                    for(Event dbEvent : success.getData()){
                        if(dbEvent.getHost().equals(currentUser)){
                            eventList.add(dbEvent);
                        }
                    }
                    runOnUiThread(() -> {
                        eventAdapter.notifyDataSetChanged();
                    });
                },
                failure -> Log.i(TAG, "Did not read Events successfully " + failure)
        );
    }

    private void getDogsFromDB(){
        Amplify.API.query(
                ModelQuery.list(Dog.class),
                success -> {
                    Log.i(TAG, "Read Dogs successfully");
                    dogList.clear();
                    for(Dog dbDog : success.getData()){
                        if(dbDog.getOwner().equals(currentUser)){
                            dogList.add(dbDog);
                        }
                    }
                    runOnUiThread(() -> {
                        petAdapter.notifyDataSetChanged();
                    });
                },
                failure -> Log.i(TAG, "Did not read Dogs successfully " + failure)
        );
    }

    private void setUpEventsRecyclerView(){
        RecyclerView eventsRecyclerView = findViewById(R.id.ownProfileActivityEventsRV);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        eventsRecyclerView.setLayoutManager(layoutManager);
        eventAdapter = new UpcomingEventsRecyclerViewAdapter(eventList, this);
        eventsRecyclerView.setAdapter(eventAdapter);
    }

    private void setUpPetsRecyclerView(){
        RecyclerView petsRecyclerView = findViewById(R.id.ownProfileActivityPetsRV);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        petsRecyclerView.setLayoutManager(layoutManager);
        petAdapter = new MyPetsRecyclerViewAdapter(dogList, this);
        petsRecyclerView.setAdapter(petAdapter);
    }

    public void setUpEditProfileButton() {
        Button editButton = findViewById(R.id.ownProfileActivityEditButton);
        editButton.setOnClickListener(view -> {
            Intent goToEditProfile = new Intent(OwnProfileSettings.this, EditProfile.class);
            startActivity(goToEditProfile);
        });
    }
}