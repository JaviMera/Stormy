package teamtreehouse.com.stormy.fragments.current;

import android.os.Bundle;

import teamtreehouse.com.stormy.R;
import teamtreehouse.com.stormy.model.Current;
import teamtreehouse.com.stormy.model.Forecast;

public class FragmentCurrentPhone extends FragmentCurrentBase
{
    public static FragmentCurrentPhone newInstance(Current forecastCurrent, String timezone) {

        FragmentCurrentPhone fragment = new FragmentCurrentPhone();

        Bundle bundle = new Bundle();
        bundle.putParcelable(FORECAST_CURRENT, forecastCurrent);
        bundle.putString(Forecast.FORECAST_TIMEZONE, timezone);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    protected int getLayoutId() {

        return R.layout.fragment_current_phone;
    }
}
