package javier.com.stormy.url;

import java.util.Locale;

/**
 * Created by Javi on 11/10/2016.
 */

public class ForecastUrl {

    private String mUrl;
    private String mKey;
    private String mLatitude;
    private String mLongitude;

    private ForecastUrl(Builder builder) {

        mKey = builder.mKey;
        mUrl = builder.mUrl;
        mLatitude = builder.mLatitude;
        mLongitude = builder.mLongitude;
    }

    @Override
    public String toString() {

        return String.format(Locale.ENGLISH, "%s%s/%s,%s", mUrl, mKey, mLatitude, mLongitude);
    }

    public static class Builder {

        private final String mKeyValue = "36c50081595d2616e1939f48407ff830";
        private final String mUrlValue = "https://api.darksky.net/forecast/";

        private String mKey;
        private String mUrl;
        private String mLatitude;
        private String mLongitude;

        public Builder() {

            mKey = mKeyValue;
            mUrl = mUrlValue;
        }

        public Builder withLatitude(double value) {

            mLatitude = String.valueOf(value);
            return this;
        }

        public Builder withLongitude(double value) {

            mLongitude = String.valueOf(value);
            return this;
        }

        public ForecastUrl create() {

            return new ForecastUrl(this);
        }
    }
}
