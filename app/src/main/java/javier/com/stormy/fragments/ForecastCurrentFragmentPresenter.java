package javier.com.stormy.fragments;

import android.view.View;

import javier.com.stormy.fragments.ForecastCurrentFragmentView;

/**
 * Created by Javi on 11/11/2016.
 */

public class ForecastCurrentFragmentPresenter {

    private ForecastCurrentFragmentView mView;

    public ForecastCurrentFragmentPresenter(ForecastCurrentFragmentView view) {

        mView = view;
    }

    public void setTemperatureTextView(double temperature) {

        mView.setTemperatureTextView(temperature);
    }

    public void setTimeTextView(String time) {

        mView.setTimeTextView(time);
    }

    public void setHumidityTextView(double humidity) {

        mView.setHumidity(humidity);
    }

    public void setPrecipitationTextView(int precip) {

        mView.setPrecipitationTextView(precip);
    }

    public void setSummaryTextView(String summary) {

        mView.setSummaryTextView(summary);
    }

    public void setIconImageView(int iconId) {

        mView.setIconImageView(iconId);
    }

    public void setLocationTextView(String timezone) {

        mView.setLocationTextView(timezone);
    }
}
