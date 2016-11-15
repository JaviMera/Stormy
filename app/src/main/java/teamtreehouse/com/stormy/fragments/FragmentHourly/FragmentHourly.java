package teamtreehouse.com.stormy.fragments.FragmentHourly;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import teamtreehouse.com.stormy.R;
import teamtreehouse.com.stormy.adapters.HourAdapter;
import teamtreehouse.com.stormy.fragments.ForecastFragmentBase;
import teamtreehouse.com.stormy.weather.HourData;

/**
 * Created by Javi on 11/14/2016.
 */
public class FragmentHourly extends ForecastFragmentBase {

    public static final String FORECAST_DATA = "hourl_data";

    @BindView(R.id.hourlyRecyclerView) RecyclerView mRecyclerView;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_forecast_hourly, container, false);

        ButterKnife.bind(this, view);

        HourAdapter adapter = new HourAdapter(getActivity(), mHourData);
        mRecyclerView.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager;
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

           layoutManager = new LinearLayoutManager(getActivity());
        }
        else {

            layoutManager = new GridLayoutManager(getActivity(), 1, LinearLayoutManager.HORIZONTAL, false);
        }

        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);

        return view;
    }
}
