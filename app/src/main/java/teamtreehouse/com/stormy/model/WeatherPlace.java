package teamtreehouse.com.stormy.model;

import com.google.android.gms.maps.model.LatLng;

import javier.com.stormy.network.Coordinates;

/**
 * Created by Javi on 11/13/2016.
 */
public class WeatherPlace {

    private Coordinates mCoordinates;

    public void setCoordinates(LatLng coordinates) {

        mCoordinates = new Coordinates();
        mCoordinates.setLatitude(coordinates.latitude);
        mCoordinates.setLongitude(coordinates.longitude);
    }

    public double getLatitude() {

        return mCoordinates.getLatitude();
    }

    public double getLongitude() {

        return mCoordinates.getLongitude();
    }
}
