package teamtreehouse.com.stormy.adapters;

import android.content.Context;

import teamtreehouse.com.stormy.R;
import teamtreehouse.com.stormy.model.HourData;

/**
 * Created by benjakuben on 2/10/15.
 */
public class FragmentHourAdapter extends AdapterBase<HourData, HourViewHolder> {

    public FragmentHourAdapter(Context context, HourData[] hourData) {

        super(hourData, HourViewHolder.class);
    }

    @Override
    protected int getLayoutId() {

        return R.layout.hour_recycler_item;
    }
}









