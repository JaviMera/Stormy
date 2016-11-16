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
public class FragmentHourly extends FragmentRecyclerBase{

    public static final String FORECAST_DATA = "hour_data";

    private HourData[] mHourData;

    public static FragmentHourly newInstance(HourData[] data) {

        FragmentHourly fragment = new FragmentHourly();

        Bundle bundle = new Bundle();
        bundle.putParcelableArray(FragmentHourly.FORECAST_DATA, data);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public String getTitle() {

        return "Hourly";
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHourData = (HourData[]) getArguments().getParcelableArray(FragmentHourly.FORECAST_DATA);
    }


    @Override
    protected int getLayoutId() {

        return R.layout.fragment_forecast_hourly;
    }

    @Override
    protected AdapterBase getAdapter() {

        return new FragmentHourAdapter(getActivity(), mHourData, "");
    }
}
