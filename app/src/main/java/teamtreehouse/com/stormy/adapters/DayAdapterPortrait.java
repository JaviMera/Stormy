package teamtreehouse.com.stormy.adapters;

import teamtreehouse.com.stormy.R;
import teamtreehouse.com.stormy.model.DayData;

/**
 * Created by Javi on 11/15/2016.
 */

public class DayAdapterPortrait extends AdapterBase<DayData, DayViewHolderPortrait> {

    public DayAdapterPortrait(DayData[] data, String timezone) {
        super(data, DayViewHolderPortrait.class, timezone);
    }

    @Override
    protected int getLayoutId() {

        return R.layout.day_item_portrait;
    }
}
