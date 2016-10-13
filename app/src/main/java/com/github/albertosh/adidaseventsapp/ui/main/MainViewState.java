package com.github.albertosh.adidaseventsapp.ui.main;

import android.os.Parcel;
import android.util.Pair;

import com.github.albertosh.adidaseventsapp.model.AEvent;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.AbsParcelableLceViewState;

import java.util.List;

public class MainViewState extends AbsParcelableLceViewState<Pair<List<AEvent>, AEvent>, MainView> {

    public static final Creator<MainViewState> CREATOR =
            new Creator<MainViewState>() {
                @Override public MainViewState createFromParcel(Parcel source) {
                    return new MainViewState(source);
                }

                @Override public MainViewState[] newArray(int size) {
                    return new MainViewState[size];
                }
            };

    public MainViewState() {
    }

    protected MainViewState(Parcel source) {
        readFromParcel(source);
    }

    @Override public void writeToParcel(Parcel dest, int flags) {
        // Content
        dest.writeList(loadedData.first);
        dest.writeInt(loadedData.second != null ? 1 : 0);
        if (loadedData.second != null)
            dest.writeParcelable(loadedData.second, 0);

        super.writeToParcel(dest, flags);
    }

    @Override protected void readFromParcel(Parcel source) {

        List<AEvent> events = source.readArrayList(getClassLoader());
        AEvent selectedEvent = null;
        if (source.readInt() > 0)
            selectedEvent = source.readParcelable(getClassLoader());

        loadedData = Pair.create(events, selectedEvent);
        super.readFromParcel(source);
    }

    /**
     * The class loader used for deserializing the list of parcelable items
     */
    protected ClassLoader getClassLoader() {
        return AEvent.class.getClassLoader();
    }
}
