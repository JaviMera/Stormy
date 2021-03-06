package teamtreehouse.com.stormy.fragments.hourly;

import android.content.res.Configuration;
import android.os.Bundle;

import teamtreehouse.com.stormy.adapters.AdapterBase;
import teamtreehouse.com.stormy.adapters.HourAdapterLandscape;
import teamtreehouse.com.stormy.adapters.HourAdapterPortrait;
import teamtreehouse.com.stormy.fragments.FragmentRecyclerBase;
import teamtreehouse.com.stormy.model.Forecast;
import teamtreehouse.com.stormy.model.HourData;

/**
 * Created by Javi on 11/19/2016.
 */

// Abstract type to represent both phone and tablet hourly fragment
// Even though the children types will not have unique code, their creation just serves the purpose
// to represent an hourly fragment for phone, and another hourly fragment for tablet.
public abstract class FragmentHourly extends FragmentRecyclerBase<HourData> {

    public static FragmentHourly newInstance(Class<?> fType, HourData[] data, String timezone) {

        FragmentHourly fragment = null;

        if(fType.equals(FragmentHourlyPhone.class)) {

            fragment = new FragmentHourlyPhone();
        }
        else if(fType.equals(FragmentHourlyTablet.class)) {

            fragment = new FragmentHourlyTablet();
        }

        Bundle bundle = new Bundle();
        bundle.putParcelableArray(FORECAST_DATA, data);
        bundle.putString(Forecast.FORECAST_TIMEZONE, timezone);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public String getTitle() {

        return "Hourly";
    }

    @Override
    protected AdapterBase getAdapter() {

        switch(getOrientation()) {

            case Configuration.ORIENTATION_PORTRAIT:
                return new HourAdapterPortrait(mData, mTimezone);

            case Configuration.ORIENTATION_LANDSCAPE:
                return new HourAdapterLandscape(mData, mTimezone);
        }

        return null;
    }
}
