package teamtreehouse.com.stormy.adapters;

import teamtreehouse.com.stormy.R;
import teamtreehouse.com.stormy.adapters.viewholders.HourViewHolderLandscape;
import teamtreehouse.com.stormy.model.HourData;

/**
 * Created by Javi on 11/18/2016.
 */

// Recycler Adapter in landscape mode for Hour Items
public class HourAdapterLandscape extends AdapterBase<HourData, HourViewHolderLandscape> {

    public HourAdapterLandscape(HourData[] hourData, String timezone) {

        super(hourData, HourViewHolderLandscape.class, timezone);
    }

    @Override
    protected int getLayoutId() {

        return R.layout.weather_data_item_landscape;
    }
}
