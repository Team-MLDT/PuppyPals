package com.mldt.puppypals.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.mldt.puppypals.R;

public class EventDetails extends AppCompatActivity {
    public static final String EVENT_DESCRIPTION_TAG = "eventDescription";
    public static final String EVENT_DATE_TIME_TAG = "eventDateTime";
    public static final String EVENT_LOCATION_TAG = "eventLocation";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        Intent intent = getIntent();
        String eventDesc = intent.getStringExtra("eventDescription");
        String eventDateTime = intent.getStringExtra("eventDateTime");

        TextView eventDescTV = findViewById(R.id.eventDetailActivityEventTitleTV);
        TextView eventDateTimeTV = findViewById(R.id.eventDetailActivityEventDateTV);

        eventDescTV.setText(eventDesc);
        eventDateTimeTV.setText(eventDateTime);

    }
}