package teamtreehouse.com.stormy.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import teamtreehouse.com.stormy.R;

/**
 * Created by Javi on 11/14/2016.
 */
public class FragmentDaily extends ForecastFragmentBase {

    public static FragmentDaily newInstance() {

        FragmentDaily fragment = new FragmentDaily();
        return fragment;
    }

    @Override
    public String getTitle() {

        return "Daily";
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_forecast_daily, container, false);

        ButterKnife.bind(this, view);

        return view;
    }
}
