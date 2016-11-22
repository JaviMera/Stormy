package javier.com.stormy;

import junit.framework.Assert;
import org.junit.Test;

import teamtreehouse.com.stormy.clients.ForecastClient;

/**
 * Created by Javi on 11/10/2016.
 */
public class ForecastClientTest {

    private ForecastClient mUrl;

    @Test
    public void testToString() throws Exception {

        // Arrange
        double latitude = 37.8267;
        double longitude = -122.4233;
        String expectedUrl = "https://api.darksky.net/forecast/36c50081595d2616e1939f48407ff830/37.8267,-122.4233";

        mUrl = new ForecastClient.Builder()
                .withLatitude(latitude)
                .withLongitude(longitude)
                .create();

        // Act
        String actualUrl = mUrl.toString();

        // Assert
        Assert.assertEquals(expectedUrl, actualUrl);
    }
}