package teamtreehouse.com.stormy.adapters;

import android.view.View;
import android.widget.TextView;

import java.util.Locale;

import teamtreehouse.com.stormy.R;
import teamtreehouse.com.stormy.model.DayData;
import teamtreehouse.com.stormy.model.HourData;

/**
 * Created by Javi on 11/18/2016.
 */

public class DayViewHolderLandscape extends WeatherDataViewHolderLandscape<DayData> {

    public DayViewHolderLandscape(View itemView) {
        super(itemView);
    }

    @Override
    protected String getTemperatureTitle(DayData data, String timezone) {

        return data.getDayOfTheWeek(timezone);
    }

    @Override
    protected String getTemperatureValue(DayData data) {

        return String.format(Locale.ENGLISH, "%.0f / %.0f", data.getTemperatureMin(), data.getTemperatureMax());
    }
}
