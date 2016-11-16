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
import teamtreehouse.com.stormy.adapters.FragmentHourAdapter;
import teamtreehouse.com.stormy.fragments.ForecastFragmentBase;
import teamtreehouse.com.stormy.model.HourData;

/**
 * Created by Javi on 11/14/2016.
 */
public class FragmentHourly extends ForecastFragmentBase implements FragmentHourlyView{

    public static final String FORECAST_DATA = "hourl_data";

    @BindView(R.id.hourlyRecyclerView) RecyclerView mRecyclerView;

    private HourData[] mHourData;
    private FragmentHourlyPresenter mPresenter;

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

        mPresenter = new FragmentHourlyPresenter(this);
        mPresenter.setRecyclerAdapter(mHourData);

        int orientation = getOrientation();
        mPresenter.setRecyclerLayout(orientation);

        mPresenter.setRecyclerFixedSize(true);

        return view;
    }


    @Override
    public void setRecyclerAdapter(HourData[] data) {

        FragmentHourAdapter adapter = new FragmentHourAdapter(getActivity(), data);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void setRecyclerLayout(int orientation) {

        RecyclerView.LayoutManager layoutManager = createLayoutManager(orientation);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void setRecyclerFixedSize(boolean isFixed) {

        mRecyclerView.setHasFixedSize(isFixed);
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
