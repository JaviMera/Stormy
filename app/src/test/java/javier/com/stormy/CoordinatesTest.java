package javier.com.stormy;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import javier.com.stormy.network.Coordinates;

/**
 * Created by Javi on 11/13/2016.
 */

public class CoordinatesTest {

    private Coordinates mCoordinates;
    private double mExpectedLatitude = -100.0;
    private double mExpectedLongitude = -9999.99999;

    @Before
    public void setUp() throws Exception {

        mCoordinates = new Coordinates();
    }

    @Test
    public void getLatitude() throws Exception {

        // Arrange
        mCoordinates.setLatitude(mExpectedLatitude);
        mCoordinates.setLongitude(mExpectedLongitude);

        // Act
        double actualLatitude = mCoordinates.getLatitude();
        double actualLongitude = mCoordinates.getLongitude();

        // Assert
        Assert.assertEquals(mExpectedLatitude, actualLatitude);
        Assert.assertEquals(mExpectedLongitude, actualLongitude);
    }
}
