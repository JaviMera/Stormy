package teamtreehouse.com.stormy.adapters.viewholders;

import android.view.View;

import java.util.Locale;

import teamtreehouse.com.stormy.model.HourData;

/**
 * Created by Javi on 11/21/2016.
 */

// View holder in landscape mode, for hour items
public class HourViewHolderLandscape extends WeatherDataViewHolderLandscape<HourData> {

    public HourViewHolderLandscape(View itemView) {
        super(itemView);
    }

    @Override
    protected String getTemperatureTitle(HourData data, String timezone) {

        return data.getHour(timezone);
    }

    @Override
    protected String getTemperatureValue(HourData data) {

        return String.format(Locale.ENGLISH, "%.0f", data.getTemperature());
    }
}
