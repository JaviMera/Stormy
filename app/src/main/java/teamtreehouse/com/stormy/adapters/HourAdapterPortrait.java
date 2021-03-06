package teamtreehouse.com.stormy.adapters;

import teamtreehouse.com.stormy.R;
import teamtreehouse.com.stormy.adapters.viewholders.HourViewHolderPortrait;
import teamtreehouse.com.stormy.model.HourData;

/**
 * Created by benjakuben on 2/10/15.
 */
// Recycler Adapter in portrait mode for Hour Items
public class HourAdapterPortrait extends AdapterBase<HourData, HourViewHolderPortrait> {

    public HourAdapterPortrait(HourData[] hourData, String timezone) {

        super(hourData, HourViewHolderPortrait.class, timezone);
    }

    @Override
    protected int getLayoutId() {

        return R.layout.weather_hour_item_portrait;
    }
}









