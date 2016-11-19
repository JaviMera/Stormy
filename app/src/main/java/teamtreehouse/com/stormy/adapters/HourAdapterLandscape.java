package teamtreehouse.com.stormy.adapters;

import android.content.Context;

import teamtreehouse.com.stormy.R;
import teamtreehouse.com.stormy.model.HourData;

/**
 * Created by Javi on 11/18/2016.
 */

public class HourAdapterLandscape extends AdapterBase<HourData, HourViewHolderLandscape> {

    public HourAdapterLandscape(HourData[] hourData, String timezone) {

        super(hourData, HourViewHolderLandscape.class, timezone);
    }

    @Override
    protected int getLayoutId() {

        return R.layout.hour_item_landscape;
    }
}
