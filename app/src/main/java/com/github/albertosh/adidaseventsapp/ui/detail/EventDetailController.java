package com.github.albertosh.adidaseventsapp.ui.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.albertosh.adidasevents.sdk.api.ServiceError;
import com.github.albertosh.adidaseventsapp.BuildConfig;
import com.github.albertosh.adidaseventsapp.R;
import com.github.albertosh.adidaseventsapp.di.detail.DaggerEventDetailComponent;
import com.github.albertosh.adidaseventsapp.di.detail.EventDetailComponent;
import com.github.albertosh.adidaseventsapp.model.AEvent;
import com.github.albertosh.adidaseventsapp.ui.base.BaseLceViewStateController;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.ParcelableDataLceViewState;
import com.squareup.picasso.Picasso;

import javax.annotation.Nonnull;

import butterknife.BindView;

public class EventDetailController
        extends BaseLceViewStateController<View, AEvent, EventDetailView, IEventDetailPresenter, EventDetailComponent>
        implements EventDetailView {

    private final static String KEY_EVENT_ID = "EventDetailController.eventId";
    private final static String KEY_EVENT = "EventDetailController.event";

    public static EventDetailController getInstance(@Nonnull AEvent event) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_EVENT, event);
        bundle.putString(KEY_EVENT_ID, event.getId());
        return new EventDetailController(bundle);
    }

    public static EventDetailController getInstance(@Nonnull String eventId) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_EVENT_ID, eventId);
        return new EventDetailController(bundle);
    }

    @BindView(R.id.imgImage)
    ImageView imgImage;
    @BindView(R.id.txtDetailTitle)
    TextView txtTitle;
    @BindView(R.id.txtDetailDate)
    TextView txtDate;
    @BindView(R.id.txtDescription)
    TextView txtDescription;

    @Deprecated
    /**
     * @deprecated Use {{@link EventDetailController#getInstance(AEvent)}}
     * or {{@link EventDetailController#getInstance(String)}} instead
     */
    public EventDetailController() {
    }

    @Deprecated
    /**
     * @deprecated Use {{@link EventDetailController#getInstance(AEvent)}}
     * or {{@link EventDetailController#getInstance(String)}} instead
     */
    public EventDetailController(Bundle args) {
        super(args);
    }

    @Override
    protected int getLayout() {
        return R.layout.controller_event_detail;
    }

    @NonNull
    @Override
    protected EventDetailComponent createComponent() {
        return DaggerEventDetailComponent.builder()
                .appComponent(getApplicationComponent())
                .build();
    }

    @Override
    protected void updateUIElements(AEvent event) {
        txtTitle.setText(event.getTitle());
        txtDate.setText(DateFormat.getDateFormat(getApplicationContext()).format(event.getDate().toDate()));

        if (event.getImageUrl().isPresent()) {
            Picasso.with(getApplicationContext()).load(event.getImageUrl().get()).into(imgImage);
        } else if (event.getImageId().isPresent()) {
            String baseUrl = BuildConfig.IMAGE_RESOURCE_BASE_URL;
            String imageUrl = baseUrl + "image/" + event.getImageId().get();
            Picasso.with(getApplicationContext()).load(imageUrl).into(imgImage);
        } else {
            Picasso.with(getApplicationContext()).load(component.customProperties().getDefaultImageUrl()).into(imgImage);
        }

        event.getDescription().ifPresent(desc -> txtDescription.setText(desc));
    }

    @NonNull
    @Override
    public LceViewState<AEvent, EventDetailView> createViewState() {
        return new ParcelableDataLceViewState<>();
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        if (e == ServiceError.networkError) {
            return getActivity().getString(R.string.error_noNetwork);
        } else if (e == ServiceError.unknownError) {
            return getActivity().getString(R.string.error_unknown);
        } else {
            throw new RuntimeException("Unhandled error message: " + e);
        }
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadEvent(getArgs().getParcelable(KEY_EVENT), getArgs().getString(KEY_EVENT_ID));
    }
}
