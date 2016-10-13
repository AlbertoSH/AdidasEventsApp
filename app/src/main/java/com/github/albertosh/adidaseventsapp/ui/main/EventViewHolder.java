package com.github.albertosh.adidaseventsapp.ui.main;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.albertosh.adidaseventsapp.BuildConfig;
import com.github.albertosh.adidaseventsapp.R;
import com.github.albertosh.adidaseventsapp.custom.CustomProperties;
import com.github.albertosh.adidaseventsapp.model.AEvent;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventViewHolder extends RecyclerView.ViewHolder {

    public interface OnEventSelected {
        public void onEventSelected(int adapterPosition, AEvent event);
    }

    @BindView(R.id.imgImage)
    ImageView imgImage;
    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.txtDate)
    TextView txtDate;

    private AEvent event;
    private final OnEventSelected listener;
    private final CustomProperties customProperties;

    public EventViewHolder(View itemView, OnEventSelected onEventSelected, CustomProperties customProperties) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.listener = onEventSelected;
        this.customProperties = customProperties;
    }

    public void bind(AEvent event, boolean twoPaneView, boolean isSelected) {
        this.event = event;
        final Context context = itemView.getContext().getApplicationContext();

        txtTitle.setText(event.getTitle());
        txtDate.setText(DateFormat.getDateFormat(context).format(event.getDate().toDate()));

        if (event.getImageUrl().isPresent()) {
            Picasso.with(context).load(event.getImageUrl().get()).into(imgImage);
        } else if (event.getImageId().isPresent()) {
            String baseUrl = BuildConfig.IMAGE_RESOURCE_BASE_URL;
            String imageUrl = baseUrl + "image/" + event.getImageId().get();
            Picasso.with(context).load(imageUrl).into(imgImage);
        } else {
            Picasso.with(context).load(customProperties.getDefaultImageUrl()).into(imgImage);
        }

        if (twoPaneView && isSelected) {
            itemView.setBackgroundColor(
                    Color.parseColor(customProperties.getSelectedEventBackgroundColor())
            );
        } else {
            itemView.setBackgroundColor(
                    Color.parseColor(customProperties.getUnselectedEventBackgroundColor())
            );
        }

        customProperties.applyCustomProperties("imgImage", imgImage);
        customProperties.applyCustomProperties("txtTitle", txtTitle);
        customProperties.applyCustomProperties("txtDate", txtDate);
    }

    @OnClick(R.id.row_root)
    void onRowClick() {
        if (listener != null)
            listener.onEventSelected(getAdapterPosition(), event);
    }
}