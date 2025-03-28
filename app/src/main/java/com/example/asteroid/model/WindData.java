package com.example.asteroid.model;

import android.os.Parcel;
import android.os.Parcelable;

public class WindData implements Parcelable {
    private float compassDegrees;
    private int count;

    public WindData(float compassDegrees, int count) {
        this.compassDegrees = compassDegrees;
        this.count = count;
    }

    protected WindData(Parcel in) {
        compassDegrees = in.readFloat();
        count = in.readInt();
    }

    public static final Creator<WindData> CREATOR = new Creator<WindData>() {
        @Override
        public WindData createFromParcel(Parcel in) {
            return new WindData(in);
        }

        @Override
        public WindData[] newArray(int size) {
            return new WindData[size];
        }
    };

    // ✅ GETTERS NECESARIOS
    public float getCompassDegrees() {
        return compassDegrees;
    }

    public int getCount() {
        return count;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(compassDegrees);
        parcel.writeInt(count);
    }
}
