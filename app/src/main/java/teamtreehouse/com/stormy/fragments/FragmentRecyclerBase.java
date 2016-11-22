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
import teamtreehouse.com.stormy.adapters.AdapterBase;
import teamtreehouse.com.stormy.fragments.hourly.FragmentHourlyPhone;
import teamtreehouse.com.stormy.model.Forecast;

/**
 * Created by Javi on 11/16/2016.
 */

public abstract class FragmentRecyclerBase<T> extends FragmentTabBase
        implements FragmentRecyclerView {

    private FragmentRecyclerPresenter mPresenter;
    protected static final String FORECAST_DATA = "forecast_data";

    protected T[] mData;
    protected String mTimezone;
    protected FragmentActivity mFragmentActivity;
    protected abstract AdapterBase getAdapter();

    @BindView(R.id.forecastRecycler) RecyclerView mRecycler;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mFragmentActivity = getActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mData = (T[]) getArguments().getParcelableArray(FragmentHourlyPhone.FORECAST_DATA);
        mTimezone = getArguments().getString(Forecast.FORECAST_TIMEZONE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_forecast_recycler, container, false);

        ButterKnife.bind(this, view);

        mPresenter = new FragmentRecyclerPresenter(this);

        AdapterBase adapter = getAdapter();
        mPresenter.setRecyclerAdapter(adapter);

        int orientation = getOrientation();
        RecyclerView.LayoutManager layoutManager = createLayoutManager(orientation);
        mPresenter.setRecyclerManager(layoutManager);

        mPresenter.setRecyclerSize(true);

        return view;
    }

    @Override
    public void setRecyclerAdapter(AdapterBase adapter) {

        mRecycler.setAdapter(adapter);
    }


    @Override
    public void setRecyclerManager(RecyclerView.LayoutManager manager) {

        mRecycler.setLayoutManager(manager);
    }

    @Override
    public void setRecyclerSize(boolean isFixed) {

        mRecycler.setHasFixedSize(isFixed);
    }

    protected int getOrientation() {

        return getResources()
                .getConfiguration()
                .orientation;
    }

    protected RecyclerView.LayoutManager createLayoutManager(int orientation) {

        switch(orientation) {

            case Configuration.ORIENTATION_PORTRAIT:
                return new LinearLayoutManager(getActivity());

            case Configuration.ORIENTATION_LANDSCAPE:
                return new GridLayoutManager(mFragmentActivity,
                    1,
                    LinearLayoutManager.HORIZONTAL,
                    false);
        }

        return null;
    }
}
