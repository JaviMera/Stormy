package teamtreehouse.com.stormy.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import teamtreehouse.com.stormy.R;
import teamtreehouse.com.stormy.fragments.FragmentCurrent.FragmentCurrentTablet;
import teamtreehouse.com.stormy.fragments.FragmentRecycler.FragmentDaily;
import teamtreehouse.com.stormy.fragments.FragmentRecycler.FragmentHourly;

/**
 * Created by Javi on 11/19/2016.
 */

public class FragmentForecastTablet extends FragmentForecastBase {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main_tablet, container, false);

        FragmentManager fragmentManager = mActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.currentFragmentContainer, FragmentCurrentTablet.newInstance(
            mForecast.getCurrent(),
            mForecast.getTimezone())
        );

        fragmentTransaction.replace(R.id.hourFragmentContainer, FragmentHourly.newInstance(
            FragmentHourlyTablet.class,
            mForecast.getHourlyForecast())
        );

        fragmentTransaction.replace(R.id.dayFragmentContainer, FragmentDaily.newInstance(
            FragmentDailyTablet.class,
            mForecast.getDailyForecast(),
            mForecast.getTimezone())
        );

        fragmentTransaction.commit();

        return view;
    }
}
