package teamtreehouse.com.stormy.fragments.FragmentRecycler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import teamtreehouse.com.stormy.R;
import teamtreehouse.com.stormy.adapters.AdapterBase;
import teamtreehouse.com.stormy.adapters.FragmentHourAdapter;
import teamtreehouse.com.stormy.model.HourData;

/**
 * Created by Javi on 11/14/2016.
 */
public class FragmentHourly extends FragmentRecyclerBase<HourData>{

    public static FragmentHourly newInstance(HourData[] data) {

        FragmentHourly fragment = new FragmentHourly();

        Bundle bundle = new Bundle();
        bundle.putParcelableArray(FORECAST_DATA, data);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public String getTitle() {

        return "Today's Forecast";
    }

    @Override
    protected int getLayoutId() {

        return R.layout.fragment_forecast_hourly;
    }

    @Override
    protected AdapterBase getAdapter() {

        return new FragmentHourAdapter(getActivity(), mData, "");
    }
}
