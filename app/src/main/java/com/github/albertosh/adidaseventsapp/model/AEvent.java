package com.github.albertosh.adidaseventsapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.github.albertosh.adidasevents.sdk.models.Event;
import com.github.albertosh.adidaseventsapp.utils.ParcelableUtils;

import org.joda.time.LocalDate;

public class AEvent
        extends Event
        implements Parcelable {

    public AEvent(Event from) {
        this(new Event.Builder().fromPrototype(from));
    }

    public AEvent(Event.Builder from) {
        super(from);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        ParcelableUtils.writeString(getTitle(), dest);
        ParcelableUtils.writeString(getDescription(), dest);
        ParcelableUtils.writeSerializable(getDate(), dest);
        ParcelableUtils.writeString(getImageUrl(), dest);
        ParcelableUtils.writeString(getImageId(), dest);
        ParcelableUtils.writeString(getId(), dest);
    }


    private static AEvent from(Parcel in) {
        return new AEvent(
                (Builder) new Builder()
                .title(ParcelableUtils.readString(in))
                .description(ParcelableUtils.readString(in))
                .date((LocalDate) ParcelableUtils.readSerializable(in))
                .imageUrl(ParcelableUtils.readString(in))
                .imageId(ParcelableUtils.readString(in))
                .id(ParcelableUtils.readString(in))
        );
    }

    public static final Creator<AEvent> CREATOR = new Creator<AEvent>() {
        @Override
        public AEvent createFromParcel(Parcel source) {
            return AEvent.from(source);
        }

        @Override
        public AEvent[] newArray(int size) {
            return new AEvent[size];
        }
    };
}
