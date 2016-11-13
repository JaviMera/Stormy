package teamtreehouse.com.stormy.model;

import android.location.Address;

import com.google.android.gms.maps.model.LatLng;

import javier.com.stormy.network.Coordinates;

/**
 * Created by Javi on 11/13/2016.
 */
public class WeatherPlace {

    private Coordinates mCoordinates;
    private Address mAddress;

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

    public void setLocality(Address address) {

        mAddress = new Address(address.getLocale());
        mAddress.setLocality(address.getLocality());
        mAddress.setAdminArea(address.getAdminArea());
    }

    public String getFullName() {

        return mAddress.getLocality() + ", " + mAddress.getAdminArea();
    }
}
