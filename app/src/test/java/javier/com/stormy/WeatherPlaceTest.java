package javier.com.stormy;

import com.google.android.gms.maps.model.LatLng;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import teamtreehouse.com.stormy.model.WeatherPlace;

/**
 * Created by Javi on 11/13/2016.
 */

public class WeatherPlaceTest {

    private WeatherPlace mPlace;
    private double mExpectedLatitude = -86.9990;
    private double mExpectedLongitude = -91.000;

    @Before
    public void setUp() throws Exception {

        mPlace = new WeatherPlace();
    }

    @Test
    public void getLatLng() throws Exception {

        // Arrange
        mPlace.setCoordinates(new LatLng(mExpectedLatitude, mExpectedLongitude));

        // Act
        double actualLatitude = mPlace.getLatitude();
        double actualLongitude = mPlace.getLongitude();

        // Assert
        Assert.assertEquals(mExpectedLatitude, actualLatitude);
        Assert.assertEquals(mExpectedLongitude, actualLongitude);
    }
}
