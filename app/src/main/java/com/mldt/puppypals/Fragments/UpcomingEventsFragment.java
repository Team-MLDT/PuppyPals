package com.mldt.puppypals.Fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mldt.puppypals.R;

public class UpcomingEventsFragment extends Fragment {

    public UpcomingEventsFragment() {
        // Required empty public constructor
    }

    public static UpcomingEventsFragment newInstance(String param1, String param2) {
        return new UpcomingEventsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upcoming_events, container, false);
    }
}