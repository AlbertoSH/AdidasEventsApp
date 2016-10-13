package com.github.albertosh.adidaseventsapp.ui.main;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.albertosh.adidaseventsapp.R;
import com.github.albertosh.adidaseventsapp.custom.CustomProperties;
import com.github.albertosh.adidaseventsapp.model.AEvent;

import java.util.ArrayList;
import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventViewHolder> implements EventViewHolder.OnEventSelected {

    private final CustomProperties customProperties;
    private final List<AEvent> events;
    private final EventViewHolder.OnEventSelected onEventSelected;
    private final boolean twoPaneView;
    private Integer currentSelectedPosition;

    public EventsAdapter(boolean twoPaneView, EventViewHolder.OnEventSelected onEventSelected, CustomProperties customProperties) {
        this.events = new ArrayList();
        this.twoPaneView = twoPaneView;
        this.customProperties = customProperties;
        this.onEventSelected = onEventSelected;
        currentSelectedPosition = null;
        setHasStableIds(true);
    }

    @Override
    public long getItemId(int position) {
        return this.events.get(position).getId().hashCode();
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_event, parent, false);
        return new EventViewHolder(itemView, this, customProperties);
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        AEvent event = events.get(position);
        holder.bind(event, twoPaneView,
                currentSelectedPosition != null
                        ? currentSelectedPosition == position
                        : false);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }


    @Override
    public void onEventSelected(int adapterPosition, AEvent event) {
        notifyItemChanged(adapterPosition);
        if (onEventSelected != null)
            onEventSelected.onEventSelected(adapterPosition, event);
    }

    public void setData(List<? extends AEvent> data) {
        this.events.clear();
        this.events.addAll(data);
    }
}
