package teamtreehouse.com.stormy.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import teamtreehouse.com.stormy.R;
import teamtreehouse.com.stormy.fragments.current.FragmentCurrentTablet;
import teamtreehouse.com.stormy.fragments.daily.FragmentDaily;
import teamtreehouse.com.stormy.fragments.daily.FragmentDailyTablet;
import teamtreehouse.com.stormy.fragments.hourly.FragmentHourly;
import teamtreehouse.com.stormy.fragments.hourly.FragmentHourlyTablet;

/**
 * Created by Javi on 11/19/2016.
 */

// Main Activity's fragment for tablet devices
public class FragmentForecastTablet extends FragmentForecastBase {

    public static final String FRAGMENT_CURRENT_TAG = "FRAGMENT_CURRENT";
    public static final String FRAGMENT_HOURLY_TAG = "FRAGMENT_HOURLY";
    public static final String FRAGMENT_DAILY_TAG = "FRAGMENT_DAILY";

    private FragmentManager mFragmentManager;

    @BindView(R.id.rootLayout) LinearLayout mRootLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main_tablet, container, false);

        ButterKnife.bind(this, view);

        mFragmentManager = mActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.currentFragmentContainer,
            FragmentCurrentTablet.newInstance(
                FragmentCurrentTablet.class,
                mForecast.getCurrent(),
                mForecast.getTimezone()),
                FRAGMENT_CURRENT_TAG
        );

        fragmentTransaction.replace(R.id.hourFragmentContainer, FragmentHourly.newInstance(
            FragmentHourlyTablet.class,
            mForecast.getHourlyForecast(),
            mForecast.getTimezone()),
            FRAGMENT_HOURLY_TAG
        );

        fragmentTransaction.replace(R.id.dayFragmentContainer, FragmentDaily.newInstance(
                FragmentDailyTablet.class,
                mForecast.getDailyForecast(),
                mForecast.getTimezone()),
                FRAGMENT_DAILY_TAG
        );

        fragmentTransaction.commit();
        setBackground(mRootLayout);

        return view;
    }
}
