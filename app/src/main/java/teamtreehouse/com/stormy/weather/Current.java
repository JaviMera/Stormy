package teamtreehouse.com.stormy.weather;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by benjakuben on 12/8/14.
 */
public class Current implements Parcelable {

    @SerializedName("icon")
    private String mIcon;

    @SerializedName("time")
    private long mTime;

    @SerializedName("temperature")
    private double mTemperature;

    @SerializedName("humidity")
    private double mHumidity;

    @SerializedName("precipPropability")
    private double mPrecipChance;

    @SerializedName("summary")
    private String mSummary;

    protected Current(Parcel in) {
        mIcon = in.readString();
        mTime = in.readLong();
        mTemperature = in.readDouble();
        mHumidity = in.readDouble();
        mPrecipChance = in.readDouble();
        mSummary = in.readString();
    }

    public static final Creator<Current> CREATOR = new Creator<Current>() {
        @Override
        public Current createFromParcel(Parcel in) {
            return new Current(in);
        }

        @Override
        public Current[] newArray(int size) {
            return new Current[size];
        }
    };

    public int getIconId() {
        return Forecast.getIconId(mIcon);
    }

    public int getTemperature() {
        return (int)Math.round(mTemperature);
    }

    public double getHumidity() {
        return mHumidity;
    }

    public int getPrecipChance() {
        double precipPercentage = mPrecipChance * 100;
        return (int)Math.round(precipPercentage);
    }

    public String getSummary() {
        return mSummary;
    }

    public String getFormattedTime(String timezone) {
        SimpleDateFormat formatter = new SimpleDateFormat("h:mm a");
        formatter.setTimeZone(TimeZone.getTimeZone(timezone));
        Date dateTime = new Date(mTime * 1000);
        String timeString = formatter.format(dateTime);

        return timeString;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mIcon);
        parcel.writeLong(mTime);
        parcel.writeDouble(mTemperature);
        parcel.writeDouble(mHumidity);
        parcel.writeDouble(mPrecipChance);
        parcel.writeString(mSummary);
    }
}
