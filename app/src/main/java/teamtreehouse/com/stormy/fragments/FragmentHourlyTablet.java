package teamtreehouse.com.stormy.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import teamtreehouse.com.stormy.adapters.AdapterBase;
import teamtreehouse.com.stormy.adapters.HourAdapterPortrait;
import teamtreehouse.com.stormy.fragments.FragmentRecycler.FragmentRecyclerBase;
import teamtreehouse.com.stormy.model.HourData;

/**
 * Created by Javi on 11/18/2016.
 */

public class FragmentHourlyTablet extends FragmentRecyclerBase<HourData> {

    public static FragmentHourlyTablet newInstance(HourData[] data) {

        FragmentHourlyTablet fragment = new FragmentHourlyTablet();

        Bundle bundle = new Bundle();
        bundle.putParcelableArray(FORECAST_DATA, data);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    protected AdapterBase getAdapter() {

        return new HourAdapterPortrait(mData, "");
    }

    @Override
    public String getTitle() {

        return "Hourly";
    }

    @Override
    protected RecyclerView.LayoutManager createLayoutManager(int orientation) {

        return new LinearLayoutManager(getActivity());
    }
}
