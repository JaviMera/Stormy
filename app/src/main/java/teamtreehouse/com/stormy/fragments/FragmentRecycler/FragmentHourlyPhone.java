package teamtreehouse.com.stormy.fragments.FragmentRecycler;

import android.content.res.Configuration;
import android.os.Bundle;

import teamtreehouse.com.stormy.adapters.AdapterBase;
import teamtreehouse.com.stormy.adapters.HourAdapterLandscape;
import teamtreehouse.com.stormy.adapters.HourAdapterPortrait;
import teamtreehouse.com.stormy.model.HourData;

/**
 * Created by Javi on 11/14/2016.
 */
public class FragmentHourlyPhone extends FragmentRecyclerBase<HourData>{

    public static FragmentHourlyPhone newInstance(HourData[] data) {

        FragmentHourlyPhone fragment = new FragmentHourlyPhone();

        Bundle bundle = new Bundle();
        bundle.putParcelableArray(FORECAST_DATA, data);
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
                return new HourAdapterPortrait(mData, "");

            case Configuration.ORIENTATION_LANDSCAPE:
                return new HourAdapterLandscape(mData, "");
        }

        return null;
    }
}
