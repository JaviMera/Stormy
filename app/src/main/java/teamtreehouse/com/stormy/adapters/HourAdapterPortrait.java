package teamtreehouse.com.stormy.adapters;

import android.content.Context;

import teamtreehouse.com.stormy.R;
import teamtreehouse.com.stormy.model.HourData;

/**
 * Created by benjakuben on 2/10/15.
 */
public class HourAdapterPortrait extends AdapterBase<HourData, HourViewHolderPortrait> {

    public HourAdapterPortrait(HourData[] hourData, String timezone) {

        super(hourData, HourViewHolderPortrait.class, timezone);
    }

    @Override
    protected int getLayoutId() {

        return R.layout.hour_item_portrait;
    }
}









