package teamtreehouse.com.stormy.fragments;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import teamtreehouse.com.stormy.R;
import teamtreehouse.com.stormy.adapters.DayViewHolder;
import teamtreehouse.com.stormy.adapters.FragmentDayAdapter;
import teamtreehouse.com.stormy.model.DayData;
import teamtreehouse.com.stormy.model.Forecast;

/**
 * Created by Javi on 11/14/2016.
 */
public class FragmentDaily extends ForecastFragmentBase {

    private FragmentActivity mFragmentActivity;
    private DayData[] mData;
    private String mTimezone;

    public static final String FORECAST = "daily_forecast";

    @BindView(R.id.dailyRecyclerView) RecyclerView mRecyclerView;

    public static FragmentDaily newInstance(DayData[] data, String timezone) {

        FragmentDaily fragment = new FragmentDaily();

        Bundle bundle = new Bundle();
        bundle.putParcelableArray(FORECAST, data);
        bundle.putString(Forecast.FORECAST_TIMEZONE, timezone);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mFragmentActivity = getActivity();
    }

    @Override
    public String getTitle() {

        return "Daily";
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mData = (DayData[]) getArguments().getParcelableArray(FragmentDaily.FORECAST);
        mTimezone = getArguments().getString(Forecast.FORECAST_TIMEZONE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_forecast_daily, container, false);

        ButterKnife.bind(this, view);

        FragmentDayAdapter adapter = new FragmentDayAdapter(mData, DayViewHolder.class, mTimezone);
        mRecyclerView.setAdapter(adapter);

        int orientation = getOrientation();
        RecyclerView.LayoutManager layoutManager = createLayoutManager(orientation);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);
        return view;
    }

    private int getOrientation() {

        return getResources()
                .getConfiguration()
                .orientation;
    }

    private RecyclerView.LayoutManager createLayoutManager(int orientation) {

        switch(orientation) {

            case Configuration.ORIENTATION_PORTRAIT:
                return new LinearLayoutManager(getActivity());

            case Configuration.ORIENTATION_LANDSCAPE:
                return new GridLayoutManager(getActivity(), 1, LinearLayoutManager.HORIZONTAL, false);
        }

        return null;
    }
}
