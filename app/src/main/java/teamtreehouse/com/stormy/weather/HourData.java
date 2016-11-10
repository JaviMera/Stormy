package teamtreehouse.com.stormy.weather;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Javi on 11/10/2016.
 */
public class HourData implements Parcelable{

    @SerializedName("time")
    private long mTime;

    @SerializedName("summary")
    private String mSummary;

    @SerializedName("temperature")
    private double mTemperature;

    @SerializedName("icon")
    private String mIcon;

    protected HourData(Parcel in) {
        mTime = in.readLong();
        mSummary = in.readString();
        mTemperature = in.readDouble();
        mIcon = in.readString();
    }

    public static final Creator<HourData> CREATOR = new Creator<HourData>() {
        @Override
        public HourData createFromParcel(Parcel in) {
            return new HourData(in);
        }

        @Override
        public HourData[] newArray(int size) {
            return new HourData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mTime);
        dest.writeString(mSummary);
        dest.writeDouble(mTemperature);
        dest.writeString(mIcon);
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }

    public double getTemperature() {
        return mTemperature;
    }

    public void setTemperature(double temperature) {
        mTemperature = temperature;
    }

    public long getTime() {
        return mTime;
    }

    public void setTime(long time) {
        mTime = time;
    }

    public String getHour() {
        SimpleDateFormat formatter = new SimpleDateFormat("h a");
        Date date = new Date(getTime() * 1000);
        return formatter.format(date);
    }

    public int getIconId() {

        return Forecast.getIconId(mIcon);
    }
}
