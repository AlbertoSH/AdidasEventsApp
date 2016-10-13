package com.github.albertosh.adidaseventsapp.utils;

import android.os.Parcel;
import android.support.annotation.Nullable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import java8.util.Optional;

public class ParcelableUtils {

    public static <T extends Serializable> void writeSerializable(Optional<T> value, Parcel dest) {
        dest.writeInt(value.map(v -> 1).orElse(0));
        value.ifPresent(v -> writeSerializable(v, dest));
    }

    public static <T extends Serializable> void writeSerializable(T value, Parcel dest) {
        dest.writeSerializable(value);
    }

    @Nullable
    public static <T extends Serializable> T readSerializable(Parcel in) {
        return (T) readSerializable(in, null);
    }

    public static <T extends Serializable> T readSerializable(Parcel in, T defaultValueIfNotPresent) {
        boolean isPresent = in.readInt() == 1;
        if (isPresent) {
            return (T) in.readSerializable();
        } else {
            return defaultValueIfNotPresent;
        }
    }


    public static void writeString(Optional<String> value, Parcel dest) {
        dest.writeInt(value.map(v -> 1).orElse(0));
        value.ifPresent(v -> writeString(v, dest));
    }

    public static void writeString(String value, Parcel dest) {
        dest.writeString(value);
    }

    @Nullable
    public static String readString(Parcel in) {
        return readString(in, null);
    }

    public static String readString(Parcel in, String defaultValueIfNotPresent) {
        boolean isPresent = in.readInt() == 1;
        if (isPresent) {
            return in.readString();
        } else {
            return defaultValueIfNotPresent;
        }
    }


    public static void writeBoolean(Optional<Boolean> value, Parcel dest) {
        dest.writeInt(value.map(v -> 1).orElse(0));
        value.ifPresent(v -> writeBoolean(v, dest));
    }

    public static void writeBoolean(Boolean value, Parcel dest) {
        dest.writeInt(value ? 1 : 0);
    }

    @Nullable
    public static Boolean readBoolean(Parcel in) {
        return readBoolean(in, null);
    }

    public static Boolean readBoolean(Parcel in, Boolean defaultValueIfNotPresent) {
        boolean isPresent = in.readInt() == 1;
        if (isPresent) {
            return in.readInt() == 1;
        } else {
            return defaultValueIfNotPresent;
        }
    }


    public static <T> void writeCollection(Collection<T> collection, Parcel dest) {
        dest.writeList(new ArrayList<>(collection));
    }

    public static <T> Collection<T> readCollection(Parcel in, Class<T> klass) {
        List<T> value = new ArrayList<>();
        in.readList(value, klass.getClassLoader());
        return value;
    }


    public static void writeInteger(Integer value, Parcel dest) {
        dest.writeInt(value);
    }

    public static Integer readInteger(Parcel in) {
        return in.readInt();
    }
}
