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
public class FragmentDaily extends FragmentRecyclerBase {

    private DayData[] mData;
    private String mTimezone;

    public static final String FORECAST = "daily_forecast";

    @BindView(R.id.forecastRecycler) RecyclerView mRecyclerView;

    public static FragmentDaily newInstance(DayData[] data, String timezone) {

        FragmentDaily fragment = new FragmentDaily();

        Bundle bundle = new Bundle();
        bundle.putParcelableArray(FORECAST, data);
        bundle.putString(Forecast.FORECAST_TIMEZONE, timezone);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public String getTitle() {

        return "Daily";
    }

    @Override
    protected int getLayoutId() {

        return R.layout.fragment_forecast_daily;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mData = (DayData[]) getArguments().getParcelableArray(FragmentDaily.FORECAST);
        mTimezone = getArguments().getString(Forecast.FORECAST_TIMEZONE);
    }

    @Override
    protected AdapterBase getAdapter() {

        return new FragmentDayAdapter(mData, DayViewHolder.class, mTimezone);
    }
}
