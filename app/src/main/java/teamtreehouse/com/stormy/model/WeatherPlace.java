package teamtreehouse.com.stormy.model;

import android.location.Address;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

import javier.com.stormy.network.Coordinates;

/**
 * Created by Javi on 11/13/2016.
 */
public class WeatherPlace implements Parcelable {

    public static final String WEATHER_PLACE_JSON = "WEATHER_PLACE_JSON";

    private Coordinates mCoordinates;
    private Address mAddress;

    public WeatherPlace() {}

    protected WeatherPlace(Parcel in) {
        mCoordinates = in.readParcelable(Coordinates.class.getClassLoader());
        mAddress = in.readParcelable(Address.class.getClassLoader());
    }

    public static final Creator<WeatherPlace> CREATOR = new Creator<WeatherPlace>() {
        @Override
        public WeatherPlace createFromParcel(Parcel in) {
            return new WeatherPlace(in);
        }

        @Override
        public WeatherPlace[] newArray(int size) {
            return new WeatherPlace[size];
        }
    };

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

    public String getCityFullName() {

        return mAddress.getLocality() + ", " + mAddress.getAdminArea();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(mCoordinates, i);
        parcel.writeParcelable(mAddress, i);
    }

    public boolean hasCoordinates() {

        return mCoordinates != null;
    }
}
