package teamtreehouse.com.stormy.fragments.FragmentCurrent;

import android.os.Bundle;

import teamtreehouse.com.stormy.R;
import teamtreehouse.com.stormy.fragments.FragmentCurrentBase;
import teamtreehouse.com.stormy.model.Current;
import teamtreehouse.com.stormy.model.Forecast;

/**
 * Created by Javi on 11/18/2016.
 */

public class FragmentCurrentTablet extends FragmentCurrentBase{

    public static FragmentCurrentTablet newInstance(Current forecastCurrent, String timezone) {

        FragmentCurrentTablet fragment = new FragmentCurrentTablet();

        Bundle bundle = new Bundle();
        bundle.putParcelable(FORECAST_CURRENT, forecastCurrent);
        bundle.putString(Forecast.FORECAST_TIMEZONE, timezone);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    protected int getLayoutId() {

        return R.layout.fragment_current_tablet;
    }
}
