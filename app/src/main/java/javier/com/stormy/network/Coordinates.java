package javier.com.stormy.network;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Javi on 11/13/2016.
 */

public class Coordinates implements Parcelable{

    private double mLatitude;
    private double mLongitude;

    public Coordinates(){}

    protected Coordinates(Parcel in) {
        mLatitude = in.readDouble();
        mLongitude = in.readDouble();
    }

    public static final Creator<Coordinates> CREATOR = new Creator<Coordinates>() {
        @Override
        public Coordinates createFromParcel(Parcel in) {
            return new Coordinates(in);
        }

        @Override
        public Coordinates[] newArray(int size) {
            return new Coordinates[size];
        }
    };

    public double getLatitude() {
        return mLatitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public void setLatitude(double latitude) {
        mLatitude = latitude;
    }

    public void setLongitude(double longitude) {
        mLongitude = longitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(mLatitude);
        parcel.writeDouble(mLongitude);
    }
}
