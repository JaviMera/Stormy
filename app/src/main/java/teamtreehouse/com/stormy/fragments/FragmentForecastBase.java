package teamtreehouse.com.stormy.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import teamtreehouse.com.stormy.fragments.FragmentViewPager.FragmentForecastPhone;
import teamtreehouse.com.stormy.model.Forecast;
import teamtreehouse.com.stormy.ui.MainActivity.MainActivity;

/**
 * Created by Javi on 11/20/2016.
 */

public abstract class FragmentForecastBase extends Fragment{

    protected FragmentActivity mActivity;
    protected Forecast mForecast;

    public static FragmentForecastBase newInstance(Class<?> fType, Forecast forecast) {

        FragmentForecastBase fragment = null;

        if(fType.equals(FragmentForecastPhone.class)) {

            fragment = new FragmentForecastPhone();
        }
        else if (fType.equals(FragmentForecastTablet.class)) {

            fragment = new FragmentForecastTablet();
        }

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
        mForecast = getArguments().getParcelable(MainActivity.FORECAST);
    }
}
