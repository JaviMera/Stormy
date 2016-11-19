package teamtreehouse.com.stormy.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Javi on 11/18/2016.
 */

public abstract class WeatherData implements Parcelable {

    @SerializedName("humidity")
    private double mHumidity;

    @SerializedName("windSpeed")
    private double mWindSpeed;

    @SerializedName("windBearing")
    private int mWindBearing;

    @SerializedName("time")
    private long mTime;

    @SerializedName("summary")
    private String mSummary;

    @SerializedName("icon")
    private String mIcon;

    WeatherData(Parcel in) {
        mHumidity = in.readDouble();
        mWindSpeed = in.readDouble();
        mWindBearing = in.readInt();
        mTime = in.readLong();
        mSummary = in.readString();
        mIcon = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(mHumidity);
        parcel.writeDouble(mWindSpeed);
        parcel.writeInt(mWindBearing);
        parcel.writeLong(mTime);
        parcel.writeString(mSummary);
        parcel.writeString(mIcon);
    }

    public int getIconId() {

        return Forecast.getIconId(mIcon);
    }

    public long getTime() {
        return mTime;
    }

    public double getHumidity() {
        return mHumidity;
    }

    public int getWindBearing() {
        return mWindBearing;
    }

    public double getWindSpeed() {
        return mWindSpeed;
    }

    public String getSummary() {
        return mSummary;
    }

    public String getIcon() {
        return mIcon;
    }
}
