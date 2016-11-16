package teamtreehouse.com.stormy.adapters;

import teamtreehouse.com.stormy.R;
import teamtreehouse.com.stormy.model.DayData;

/**
 * Created by Javi on 11/15/2016.
 */

public class FragmentDayAdapter extends AdapterBase<DayData, DayViewHolder> {

    public FragmentDayAdapter(DayData[] data, Class<DayViewHolder> viewHolderType, String timezone) {
        super(data, viewHolderType, timezone);
    }

    @Override
    protected int getLayoutId() {

        return R.layout.day_recycler_item;
    }
}
