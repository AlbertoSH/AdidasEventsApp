package com.github.albertosh.adidaseventsapp.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler;
import com.github.albertosh.adidasevents.sdk.api.ServiceError;
import com.github.albertosh.adidaseventsapp.R;
import com.github.albertosh.adidaseventsapp.di.main.DaggerMainComponent;
import com.github.albertosh.adidaseventsapp.di.main.MainComponent;
import com.github.albertosh.adidaseventsapp.model.AEvent;
import com.github.albertosh.adidaseventsapp.ui.base.BaseLceViewStateController;
import com.github.albertosh.adidaseventsapp.ui.detail.EventDetailController;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;

import java.util.List;

import butterknife.BindView;

public class MainController
        extends BaseLceViewStateController<RecyclerView, Pair<List<AEvent>, AEvent>, MainView, IMainPresenter, MainComponent>
        implements MainView, EventViewHolder.OnEventSelected {

    private static final String KEY_SELECTED_INDEX = "MainController.selectedIndex";

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.detail_container)
    ViewGroup detailContainer;

    private EventsAdapter adapter;
    private int selectedIndex;
    private boolean twoPaneView;

    @Override
    protected int getLayout() {
        return R.layout.controller_master_detail_list;
    }

    @Override
    protected int getContentViewId() {
        return R.id.recycler_view;
    }

    @Override
    protected int getErrorViewId() {
        return R.id.txtError;
    }

    @Override
    protected int getLoadingViewId() {
        return R.id.prgLoading;
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
    protected void onViewBound(@NonNull View view) {
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        twoPaneView = (detailContainer != null);
        adapter = new EventsAdapter(twoPaneView, this, component.customProperties());
        recyclerView.setAdapter(adapter);
    }

    @NonNull
    @Override
    protected MainComponent createComponent() {
        return DaggerMainComponent.builder()
                .appComponent(getApplicationComponent())
                .build();
    }

    @Override
    protected void updateUIElements(Pair<List<AEvent>, AEvent> data) {
        adapter.setData(data.first);
        adapter.notifyDataSetChanged();
        if (twoPaneView && data.second != null) {
            showEvent(data.second);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(KEY_SELECTED_INDEX, selectedIndex);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        selectedIndex = savedInstanceState.getInt(KEY_SELECTED_INDEX);
    }

    @Override
    public void onEventSelected(int adapterPosition, AEvent event) {
        selectedIndex = adapterPosition;
        showEvent(event);
    }

    private void showEvent(AEvent event) {
        EventDetailController controller = EventDetailController.getInstance(event);

        if (twoPaneView) {
            getChildRouter(detailContainer, null).setRoot(RouterTransaction.with(controller));
        } else {
            getRouter().pushController(RouterTransaction.with(controller)
                    .pushChangeHandler(new HorizontalChangeHandler())
                    .popChangeHandler(new HorizontalChangeHandler()));
        }
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadEvents(twoPaneView);
    }


    @NonNull
    @Override
    public LceViewState<Pair<List<AEvent>, AEvent>, MainView> createViewState() {
        return new MainViewState();
    }
}