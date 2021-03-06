package teamtreehouse.com.stormy.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import teamtreehouse.com.stormy.R;

/**
 * Created by benjakuben on 2/5/15.
 */
public class Forecast implements Parcelable{

    public static final String FORECAST_JSON = "FORECAST_JSON";
    public static final String FORECAST_TIMEZONE = "FORECAST_TIMEZONE";

    @SerializedName("timezone")
    private String mTimezone;

    @SerializedName("currently")
    private Current mCurrent;

    @SerializedName("hourly")
    private Hour mHourlyForecast;

    @SerializedName("daily")
    private Day mDailyForecast;

    protected Forecast(Parcel in) {
        mTimezone = in.readString();
        mCurrent = in.readParcelable(Current.class.getClassLoader());
        mHourlyForecast = in.readParcelable(Hour.class.getClassLoader());
        mDailyForecast = in.readParcelable(Day.class.getClassLoader());
    }

    public static final Creator<Forecast> CREATOR = new Creator<Forecast>() {
        @Override
        public Forecast createFromParcel(Parcel in) {
            return new Forecast(in);
        }

        @Override
        public Forecast[] newArray(int size) {
            return new Forecast[size];
        }
    };

    public Current getCurrent() {
        return mCurrent;
    }

    public HourData[] getHourlyForecast() {
        return mHourlyForecast.getData();
    }

    public DayData[] getDailyForecast() {
        return mDailyForecast.getData();
    }

    public static int getIconId(String iconString) {
        // clear-day, clear-night, rain, snow, sleet, wind, fog, cloudy, partly-cloudy-day, or partly-cloudy-night.
        int iconId = R.drawable.clear_day;

        if (iconString.equals("clear-day")) {
            iconId = R.drawable.clear_day;
        }
        else if (iconString.equals("clear-night")) {
            iconId = R.drawable.clear_night;
        }
        else if (iconString.equals("rain")) {
            iconId = R.drawable.rain;
        }
        else if (iconString.equals("snow")) {
            iconId = R.drawable.snow;
        }
        else if (iconString.equals("sleet")) {
            iconId = R.drawable.sleet;
        }
        else if (iconString.equals("wind")) {
            iconId = R.drawable.wind;
        }
        else if (iconString.equals("fog")) {
            iconId = R.drawable.fog;
        }
        else if (iconString.equals("cloudy")) {
            iconId = R.drawable.cloudy;
        }
        else if (iconString.equals("partly-cloudy-day")) {
            iconId = R.drawable.partly_cloudy;
        }
        else if (iconString.equals("partly-cloudy-night")) {
            iconId = R.drawable.cloudy_night;
        }

        return iconId;

    }

    public String getTimezone() {

        return mTimezone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mTimezone);
        parcel.writeParcelable(mCurrent, i);
        parcel.writeParcelable(mHourlyForecast, i);
        parcel.writeParcelable(mDailyForecast, i);
    }
}
