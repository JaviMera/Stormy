package javier.com.stormy.network;

/**
 * Created by Javi on 11/13/2016.
 */

public class Coordinates {

    private double mLatitude;
    private double mLongitude;

    public Coordinates(){}

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
}
