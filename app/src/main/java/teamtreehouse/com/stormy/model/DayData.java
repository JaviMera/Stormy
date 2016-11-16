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

public class DayData implements Parcelable {

    @SerializedName("time")
    private long mTime;

    @SerializedName("summary")
    private String mSummary;

    @SerializedName("temperatureMax")
    private double mTemperatureMax;

    @SerializedName("temperatureMin")
    private double mTemperatureMin;

    @SerializedName("icon")
    private String mIcon;

    protected DayData(Parcel in) {
        mTime = in.readLong();
        mSummary = in.readString();
        mTemperatureMax = in.readDouble();
        mTemperatureMin = in.readDouble();
        mIcon = in.readString();
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

    public long getTime() {
        return mTime;
    }

    public void setTime(long time) {
        mTime = time;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }

    public int getTemperatureMax() {
        return (int)Math.round(mTemperatureMax);
    }

    public void setTemperatureMax(double temperatureMax) {
        mTemperatureMax = temperatureMax;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public int getIconId() {
        return Forecast.getIconId(mIcon);
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
        dest.writeLong(mTime);
        dest.writeString(mSummary);
        dest.writeDouble(mTemperatureMax);
        dest.writeDouble(mTemperatureMin);
        dest.writeString(mIcon);
    }
}
