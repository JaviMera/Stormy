package javier.com.stormy.url;

import java.util.Locale;

/**
 * Created by Javi on 11/10/2016.
 */

public class ForecastClient {

    private String mUrl;
    private String mKey;
    private String mLatitude;
    private String mLongitude;

    private ForecastClient(Builder builder) {

        mKey = builder.mKey;
        mUrl = builder.mEndPoint;
        mLatitude = builder.mLatitude;
        mLongitude = builder.mLongitude;
    }

    @Override
    public String toString() {

        return String.format(Locale.ENGLISH, "%s%s/%s,%s", mUrl, mKey, mLatitude, mLongitude);
    }

    public static class Builder {

        private final String API_KEY = "36c50081595d2616e1939f48407ff830";
        private final String END_POINT = "https://api.darksky.net/forecast/";

        private String mKey;
        private String mEndPoint;
        private String mLatitude;
        private String mLongitude;

        public Builder() {

            mEndPoint = END_POINT;
            mKey = API_KEY;
        }

        public Builder withLatitude(double value) {

            mLatitude = String.valueOf(value);
            return this;
        }

        public Builder withLongitude(double value) {

            mLongitude = String.valueOf(value);
            return this;
        }

        public ForecastClient create() {

            return new ForecastClient(this);
        }
    }
}
