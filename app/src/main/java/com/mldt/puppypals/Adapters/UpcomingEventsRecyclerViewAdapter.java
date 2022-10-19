package com.mldt.puppypals.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.amplifyframework.datastore.generated.model.Event;
import com.mldt.puppypals.R;

import java.util.List;

public class UpcomingEventsRecyclerViewAdapter extends RecyclerView.Adapter<UpcomingEventsRecyclerViewAdapter.UpcomingEventsViewHolder> {

    List<Event> eventList;
    Context callingActivity;

    public UpcomingEventsRecyclerViewAdapter(List<Event> eventList, Context callingActivity){
        this.eventList = eventList;
        this.callingActivity = callingActivity;
    }

    @NonNull
    @Override
    public UpcomingEventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View upcomingEventsFragment = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_upcoming_events, parent, false);
        return new UpcomingEventsViewHolder(upcomingEventsFragment);
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingEventsRecyclerViewAdapter.UpcomingEventsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
