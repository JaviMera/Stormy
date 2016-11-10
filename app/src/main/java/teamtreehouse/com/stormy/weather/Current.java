package teamtreehouse.com.stormy.weather;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by benjakuben on 12/8/14.
 */
public class Current {

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
}
