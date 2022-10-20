package com.mldt.puppypals.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.generated.model.Event;
import com.mldt.puppypals.Activities.EventDetails;
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
        TextView upcomingEventsTitleTV = holder.itemView.findViewById(R.id.upcomingEventsFragmentTitle);
        String eventDesc = eventList.get(position).getEventDescription();
        upcomingEventsTitleTV.setText(eventDesc);

        TextView upcomingEventsDateTV = holder.itemView.findViewById(R.id.upcomingEventsFragmentDate);
        String eventDateTime = eventList.get(position).getEventDate() + ", " + eventList.get(position).getEventTime();
        upcomingEventsDateTV.setText(eventDateTime);

        String eventLat = eventList.get(position).getLat();
        String eventLon = eventList.get(position).getLon();

        View eventsViewHolder = holder.itemView;
        eventsViewHolder.setOnClickListener(view -> {
            Intent goToEventDetailPage = new Intent(callingActivity, EventDetails.class);
            goToEventDetailPage.putExtra(EventDetails.EVENT_DESCRIPTION_TAG,eventDesc);
            goToEventDetailPage.putExtra(EventDetails.EVENT_DATE_TIME_TAG,eventDateTime);
            goToEventDetailPage.putExtra(EventDetails.EVENT_LAT_TAG,eventLat);
            goToEventDetailPage.putExtra(EventDetails.EVENT_LON_TAG,eventLon);
            callingActivity.startActivity(goToEventDetailPage);
        });
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public static class UpcomingEventsViewHolder extends RecyclerView.ViewHolder {
        public UpcomingEventsViewHolder(@NonNull View itemView){
            super(itemView);
        }
    }
}
