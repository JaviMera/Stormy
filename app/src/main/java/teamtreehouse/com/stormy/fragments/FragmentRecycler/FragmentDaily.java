package teamtreehouse.com.stormy.fragments.FragmentRecycler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import teamtreehouse.com.stormy.R;
import teamtreehouse.com.stormy.adapters.AdapterBase;
import teamtreehouse.com.stormy.adapters.DayViewHolder;
import teamtreehouse.com.stormy.adapters.FragmentDayAdapter;
import teamtreehouse.com.stormy.model.DayData;
import teamtreehouse.com.stormy.model.Forecast;

/**
 * Created by Javi on 11/14/2016.
 */
public class FragmentDaily extends FragmentRecyclerBase<DayData> {

    private String mTimezone;

    @BindView(R.id.forecastRecycler) RecyclerView mRecyclerView;

    public static FragmentDaily newInstance(DayData[] data, String timezone) {

        FragmentDaily fragment = new FragmentDaily();

        Bundle bundle = new Bundle();
        bundle.putParcelableArray(FORECAST_DATA, data);
        bundle.putString(Forecast.FORECAST_TIMEZONE, timezone);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public String getTitle() {

        return "7 Days";
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        mTimezone = getArguments().getString(Forecast.FORECAST_TIMEZONE);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected AdapterBase getAdapter() {

        return new FragmentDayAdapter(mData, DayViewHolder.class, mTimezone);
    }
}
