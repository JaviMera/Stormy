package teamtreehouse.com.stormy.adapters.viewholders;

import android.view.View;

import java.util.Locale;

import teamtreehouse.com.stormy.model.DayData;

/**
 * Created by Javi on 11/18/2016.
 */

// View holder in landscape mode, for day items
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
