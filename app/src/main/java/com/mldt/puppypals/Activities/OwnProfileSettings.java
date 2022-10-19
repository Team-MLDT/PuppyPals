package com.mldt.puppypals.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Event;
import com.amplifyframework.datastore.generated.model.User;
import com.mldt.puppypals.Adapters.UpcomingEventsRecyclerViewAdapter;
import com.mldt.puppypals.R;

import java.util.ArrayList;
import java.util.List;

public class OwnProfileSettings extends AppCompatActivity {
    public static final String TAG = "OwnProfileSettingsActivity";

    List<Event> eventList = null;
    UpcomingEventsRecyclerViewAdapter adapter;
    User currentUser;
    //currentUser currently not assigned - should be set to the current session user's email address
    //Set in shared preferences

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_own_profile_settings);

        eventList = new ArrayList<>();
        getEventsFromDB();
        setUpEventsRecyclerView();
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
                        adapter.notifyDataSetChanged();
                    });
                },
                failure -> Log.i(TAG, "Did not read Events successfully " + failure)
        );
    }

    private void setUpEventsRecyclerView(){
        RecyclerView eventsRecyclerView = findViewById(R.id.ownProfileActivityEventsRV);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        eventsRecyclerView.setLayoutManager(layoutManager);
        adapter = new UpcomingEventsRecyclerViewAdapter(eventList, this);
        eventsRecyclerView.setAdapter(adapter);
    }
}