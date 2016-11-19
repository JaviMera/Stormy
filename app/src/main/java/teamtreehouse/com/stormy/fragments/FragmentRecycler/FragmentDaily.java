package teamtreehouse.com.stormy.fragments.FragmentRecycler;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import teamtreehouse.com.stormy.R;
import teamtreehouse.com.stormy.adapters.AdapterBase;
import teamtreehouse.com.stormy.adapters.DayAdapterLandscape;
import teamtreehouse.com.stormy.adapters.DayAdapterPortrait;
import teamtreehouse.com.stormy.fragments.FragmentDailyTablet;
import teamtreehouse.com.stormy.model.DayData;
import teamtreehouse.com.stormy.model.Forecast;

/**
 * Created by Javi on 11/19/2016.
 */

public abstract class FragmentDaily extends FragmentRecyclerBase<DayData>{

    private String mTimezone;

    @BindView(R.id.forecastRecycler)
    RecyclerView mRecyclerView;

    public static FragmentDaily newInstance(Class<?> fType, DayData[] data, String timezone) {

        FragmentDaily fragment = null;

        if(fType.equals(FragmentDailyPhone.class)) {

            fragment = new FragmentDailyPhone();
        }
        else if(fType.equals(FragmentDailyTablet.class)){

            fragment = new FragmentDailyTablet();
        }

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
