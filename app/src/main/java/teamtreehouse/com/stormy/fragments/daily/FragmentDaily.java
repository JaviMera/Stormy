package teamtreehouse.com.stormy.fragments.daily;

import android.content.res.Configuration;
import android.os.Bundle;

import teamtreehouse.com.stormy.adapters.AdapterBase;
import teamtreehouse.com.stormy.adapters.DayAdapterLandscape;
import teamtreehouse.com.stormy.adapters.DayAdapterPortrait;
import teamtreehouse.com.stormy.fragments.FragmentRecyclerBase;
import teamtreehouse.com.stormy.model.DayData;
import teamtreehouse.com.stormy.model.Forecast;

/**
 * Created by Javi on 11/19/2016.
 */
// Abstract type to represent both phone and tablet daily fragment
// Even though the children types will not have unique code, their creation just serves the purpose
// to represent a daily fragment for phone, and another daily fragment for tablet.
public abstract class FragmentDaily extends FragmentRecyclerBase<DayData> {

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
