package teamtreehouse.com.stormy.adapters;

import teamtreehouse.com.stormy.R;
import teamtreehouse.com.stormy.adapters.viewholders.DayViewHolderLandscape;
import teamtreehouse.com.stormy.model.DayData;

/**
 * Created by Javi on 11/18/2016.
 */

public class DayAdapterLandscape  extends AdapterBase<DayData, DayViewHolderLandscape> {

    public DayAdapterLandscape(DayData[] data, String timezone) {
        super(data, DayViewHolderLandscape.class, timezone);
    }

    @Override
    protected int getLayoutId() {

        return R.layout.weather_data_item_landscape;
    }
}