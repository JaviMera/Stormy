package teamtreehouse.com.stormy.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Javi on 11/10/2016.
 */
public class HourData extends WeatherData {

    @SerializedName("temperature")
    private double mTemperature;

    protected HourData(Parcel in) {

        super(in);
        mTemperature = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        super.writeToParcel(dest, flags);
        dest.writeDouble(mTemperature);
    }

    public double getTemperature() {

        return (int)Math.round(mTemperature);
    }

    public String getHour() {
        SimpleDateFormat formatter = new SimpleDateFormat("h a");
        Date date = new Date(getTime() * 1000);
        return formatter.format(date);
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

}
