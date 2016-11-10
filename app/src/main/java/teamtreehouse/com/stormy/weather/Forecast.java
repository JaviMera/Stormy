package teamtreehouse.com.stormy.weather;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import teamtreehouse.com.stormy.R;

/**
 * Created by benjakuben on 2/5/15.
 */
public class Forecast {

    @SerializedName("currently")
    private Current mCurrent;

    @SerializedName("timezone")
    private String mTimezone;

    @SerializedName("hourly")
    private Hour mHourlyForecast;

    @SerializedName("daily")
    private Day mDailyForecast;

    public Current getCurrent() {
        return mCurrent;
    }

    public String getFormattedTime(String dateFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        formatter.setTimeZone(TimeZone.getTimeZone(mTimezone));
        Date dateTime = new Date(mCurrent.getTime() * 1000);
        String timeString = formatter.format(dateTime);

        return timeString;
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
}
