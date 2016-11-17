package teamtreehouse.com.stormy.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import teamtreehouse.com.stormy.R;
import teamtreehouse.com.stormy.fragments.FragmentCurrent.FragmentCurrent;
import teamtreehouse.com.stormy.fragments.FragmentRecycler.FragmentDaily;
import teamtreehouse.com.stormy.fragments.FragmentRecycler.FragmentHourly;
import teamtreehouse.com.stormy.fragments.FragmentViewPager.FragmentPager;
import teamtreehouse.com.stormy.model.Forecast;
import teamtreehouse.com.stormy.ui.MainActivity.MainActivity;

/**
 * Created by Javi on 11/17/2016.
 */

public class FragmentForecastTablet extends Fragment {

    private FragmentActivity mActivity;
    private Forecast mForecast;

    public static FragmentForecastTablet newInstance(Forecast forecast) {

        FragmentForecastTablet fragment = new FragmentForecastTablet();

        Bundle bundle = new Bundle();
        bundle.putParcelable(MainActivity.FORECAST, forecast);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mActivity = getActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
        mForecast = getArguments().getParcelable(MainActivity.FORECAST);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main_tablet_layout, container, false);

        FragmentManager fragmentManager = mActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.currentFragmentContainer, FragmentCurrent.newInstance(mForecast.getCurrent(), mForecast.getTimezone()));
        fragmentTransaction.replace(R.id.hourFragmentContainer, FragmentHourly.newInstance(mForecast.getHourlyForecast()));
        fragmentTransaction.replace(R.id.dayFragmentContainer, FragmentDaily.newInstance(mForecast.getDailyForecast(), mForecast.getTimezone()));

        fragmentTransaction.commit();

        return view;
    }
}
