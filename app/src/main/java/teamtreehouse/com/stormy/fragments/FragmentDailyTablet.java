package teamtreehouse.com.stormy.fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;

import teamtreehouse.com.stormy.adapters.AdapterBase;
import teamtreehouse.com.stormy.adapters.DayAdapterLandscape;
import teamtreehouse.com.stormy.adapters.DayAdapterPortrait;
import teamtreehouse.com.stormy.fragments.FragmentRecycler.FragmentRecyclerBase;
import teamtreehouse.com.stormy.model.DayData;
import teamtreehouse.com.stormy.model.Forecast;

/**
 * Created by Javi on 11/18/2016.
 */
public class FragmentDailyTablet extends FragmentRecyclerBase<DayData> {

    private String mTimezone;

    public static FragmentDailyTablet newInstance(DayData[] data, String timezone) {

        FragmentDailyTablet fragment = new FragmentDailyTablet();

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

        switch(getOrientation()) {

            case Configuration.ORIENTATION_PORTRAIT:
                return new DayAdapterPortrait(mData, mTimezone);

            case Configuration.ORIENTATION_LANDSCAPE:
                return new DayAdapterLandscape(mData, mTimezone);
        }

        return null;
    }
}