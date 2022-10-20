package com.mldt.puppypals.Activities;

import static java.lang.Double.parseDouble;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mldt.puppypals.R;

public class EventDetails extends AppCompatActivity implements OnMapReadyCallback {
    public static final String EVENT_DESCRIPTION_TAG = "eventDescription";
    public static final String EVENT_DATE_TIME_TAG = "eventDateTime";
    public static final String EVENT_LAT_TAG = "eventLatitude";
    public static final String EVENT_LON_TAG = "eventLongitude";

    public Double eventLat = 0.0;
    public Double eventLon = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        Intent intent = getIntent();
        String eventDesc = intent.getStringExtra("eventDescription");
        String eventDateTime = intent.getStringExtra("eventDateTime");
        eventLat = parseDouble(intent.getStringExtra("eventLatitude"));
        eventLon = parseDouble(intent.getStringExtra("eventLongitude"));

//        Fragment testGetFrag = getFragmentManager().findFragmentById(R.id.fmap);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        TextView eventDescTV = findViewById(R.id.eventDetailActivityEventTitleTV);
        TextView eventDateTimeTV = findViewById(R.id.eventDetailActivityEventDateTV);

        eventDescTV.setText(eventDesc);
        eventDateTimeTV.setText(eventDateTime);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng coords = new LatLng(eventLat,eventLon);
        googleMap.addMarker(new MarkerOptions()
                .position(coords)
                .title("Event"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(coords));
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(15));
    }
}