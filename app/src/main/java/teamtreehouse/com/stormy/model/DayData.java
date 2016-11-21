package teamtreehouse.com.stormy.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Javi on 11/10/2016.
 */

public class DayData extends WeatherData  {

    @SerializedName("temperatureMax")
    private double mTemperatureMax;

    @SerializedName("temperatureMin")
    private double mTemperatureMin;

    DayData(Parcel in) {
        super(in);

        mTemperatureMax = in.readDouble();
        mTemperatureMin = in.readDouble();
    }

    public static final Creator<DayData> CREATOR = new Creator<DayData>() {
        @Override
        public DayData createFromParcel(Parcel in) {

            return new DayData(in);
        }

        @Override
        public DayData[] newArray(int size) {
            return new DayData[size];
        }
    };

    public double getTemperatureMax() {

        return mTemperatureMax;
    }

    public double getTemperatureMin() {

        return mTemperatureMin;
    }

    public String getDayOfTheWeek(String timezone) {

        SimpleDateFormat formatter = new SimpleDateFormat("EEEE");
        formatter.setTimeZone(TimeZone.getTimeZone(timezone));
        Date dateTime = new Date(getTime() * 1000);
        String timeString = formatter.format(dateTime);

        return timeString;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        super.writeToParcel(dest, flags);

        dest.writeDouble(mTemperatureMax);
        dest.writeDouble(mTemperatureMin);
    }
}
